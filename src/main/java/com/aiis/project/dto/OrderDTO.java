package com.aiis.project.dto;

import com.aiis.project.model.Article;
import com.aiis.project.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;

    private String orderName;

    private LocalDate orderDate;

    private double orderPrice;

    private String orderDescription;

    private String orderStatus;

    private String userUsername;

    private List<ArticleDTO> articles;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.orderName = order.getOrderName();
        this.orderDate = order.getOrderDate();
        this.orderPrice = order.getOrderPrice();
        this.orderDescription = order.getOrderDescription();
        this.orderStatus = order.getOrderStatus().getStatusFlag();
        this.userUsername = order.getUser().getUsername();
        this.articles = order.getArticles().stream()
                .map(ArticleDTO::new)
                .collect(Collectors.toList());
    }
}
