package com.aiis.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotBlank(message = "Order name is mandatory")
    private String orderName;

    @NotBlank(message = "Order description is mandatory")
    private String orderDescription;

    @NotNull(message = "Order must have a user")
    private Long userId;

    @NotEmpty(message = "Order must have at least one article")
    private List<String> articleCodes;

}
