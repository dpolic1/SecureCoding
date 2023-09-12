package com.aiis.project.service;

import com.aiis.project.dto.ArticleDTO;
import com.aiis.project.request.ArticleRequest;

import java.util.List;

public interface ArticleService {

    List<ArticleDTO> findAll();

    List<ArticleDTO> findByCodeUnsafe(String code);

    ArticleDTO findById(Long id);

    ArticleDTO findByCode(String code);

    void create(ArticleRequest articleRequest);

    void updateById(Long id, ArticleRequest articleRequest);

    void updateByCode(String code, ArticleRequest articleRequest);

    void deleteById(Long id);

    void deleteByCode(String code);

    void unusedMethod();
}
