package com.aiis.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleCode;

    private String articleName;

    private double articlePrice;

    private String articleDescription;

    @ManyToMany(mappedBy = "articles")
    @JsonIgnore
    private List<Order> orders;


    public Article(String code, String name, Double price, String description){
        this.articleCode = code;
        this.articleName = name;
        this.articlePrice = price;
        this.articleDescription = description;
    }
}
