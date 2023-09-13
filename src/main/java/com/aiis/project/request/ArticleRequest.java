package com.aiis.project.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest implements Serializable {

    @NotBlank(message = "Article code is mandatory")
    private String articleCode;

    @NotBlank(message = "Article name is mandatory")
    private String articleName;

    @NotNull(message = "Article price is mandatory")
    private double articlePrice;

    @NotBlank(message = "Article description is mandatory")
    private String articleDescription;
}
