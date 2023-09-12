package com.aiis.project.service;

import com.aiis.project.dto.ArticleDTO;
import com.aiis.project.model.Article;
import com.aiis.project.repository.ArticleRepository;
import com.aiis.project.request.ArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    private final JdbcTemplate jdbcTemplate;

    public ArticleServiceImpl(ArticleRepository articleRepository, JdbcTemplate jdbcTemplate){
        this.articleRepository = articleRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleDTO> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDTO> findByCodeUnsafe(String code) {

        String unsafeSQL = "SELECT * FROM articles WHERE article_code='" + code + "'";

        List<Article> article = jdbcTemplate.query(
                unsafeSQL,
                (rs, rowNum) -> new Article(
                        rs.getString("article_code"),
                        rs.getString("article_name"),
                        rs.getDouble("article_price"),
                        rs.getString("article_description")
                )
        );

        return article.stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDTO findById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that ID does not exist.")
        );
        return new ArticleDTO(article);
    }

    @Override
    public ArticleDTO findByCode(String code) {
        Article article = articleRepository.findByArticleCode(code)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that code does not exist.")
        );
        return new ArticleDTO(article);
    }

    @Override
    public void create(ArticleRequest articleRequest) {
        try {
            Article article = new Article();
            article.setArticleCode(articleRequest.getArticleCode());
            article.setArticleName(articleRequest.getArticleName());
            article.setArticlePrice(articleRequest.getArticlePrice());
            article.setArticleDescription(articleRequest.getArticleDescription());
            articleRepository.save(article);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Article with that code already exists.");
        }
    }

    @Override
    public void updateById(Long id, ArticleRequest articleRequest) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            Article updatedArticle = article.get();
            updatedArticle.setArticleCode(articleRequest.getArticleCode());
            updatedArticle.setArticleName(articleRequest.getArticleName());
            updatedArticle.setArticlePrice(articleRequest.getArticlePrice());
            updatedArticle.setArticleDescription(articleRequest.getArticleDescription());
            articleRepository.save(updatedArticle);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that ID does not exist.");
        }
    }

    @Override
    public void updateByCode(String code, ArticleRequest articleRequest) {
        Optional<Article> article = articleRepository.findByArticleCode(code);
        if (article.isPresent()) {
            Article updatedArticle = article.get();
            updatedArticle.setArticleCode(articleRequest.getArticleCode());
            updatedArticle.setArticleName(articleRequest.getArticleName());
            updatedArticle.setArticlePrice(articleRequest.getArticlePrice());
            updatedArticle.setArticleDescription(articleRequest.getArticleDescription());
            articleRepository.save(updatedArticle);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that code does not exist.");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            articleRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that ID does not exist.");
        }
    }

    @Override
    public void deleteByCode(String code) {
        Optional<Article> article = articleRepository.findByArticleCode(code);
        if (article.isPresent()) {
            articleRepository.deleteByArticleCode(code);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that code does not exist.");
        }
    }

    @Override
    public void unusedMethod() {
        System.out.println("This method is not used anywhere.");
    }
}
