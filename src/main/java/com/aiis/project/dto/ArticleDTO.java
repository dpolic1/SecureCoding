package com.aiis.project.dto;


import com.aiis.project.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private String articleCode;

    private String articleName;

    private double articlePrice;

    private String articleDescription;

    public ArticleDTO(Article article){
        this.articleCode = article.getArticleCode();
        this.articleName = article.getArticleName();
        this.articlePrice = article.getArticlePrice();
        this.articleDescription = article.getArticleDescription();
    }
}
