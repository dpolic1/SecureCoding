package com.aiis.project.service;

import com.aiis.project.dto.ArticleDTO;
import com.aiis.project.dto.OrderDTO;
import com.aiis.project.model.Article;
import com.aiis.project.model.Order;
import com.aiis.project.repository.ArticleRepository;
import com.aiis.project.repository.OrderRepository;
import com.aiis.project.repository.OrderStatusRepository;
import com.aiis.project.repository.UserRepository;
import com.aiis.project.request.ArticleRequest;
import com.aiis.project.request.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, ArticleRepository articleRepository, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with that ID does not exist.")
        );
        return new OrderDTO(order);
    }

    @Override
    public List<OrderDTO> findByUserId(Long id) {
        List<Order> orders = orderRepository.findByUserId(id);
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void changeStatus(Long id, String status) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            Order orderToUpdate = order.get();
            if(status.equals("Approved")){
                orderToUpdate.setOrderStatus(orderStatusRepository.getById(2L));
            }
            else if (status.equals("Rejected")){
                orderToUpdate.setOrderStatus(orderStatusRepository.getById(3L));
            }
            orderRepository.save(orderToUpdate);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with that ID does not exist.");
        }
    }

    @Override
    public void create(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderName(orderRequest.getOrderName());
        order.setOrderDate(LocalDate.now());
        order.setOrderDescription(orderRequest.getOrderDescription());
        order.setOrderStatus(orderStatusRepository.getById(1L));
        order.setUser(userRepository.getById(orderRequest.getUserId()));

        List<Article> articles = new ArrayList<>();
        for (String articleCode : orderRequest.getArticleCodes()){
            articles.add(articleRepository.findByArticleCode(articleCode)
                    .orElseThrow(
                            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that code does not exist.")
                    ));
        }
        order.setArticles(articles);
        order.setOrderPrice(articles.stream().mapToDouble(Article::getArticlePrice).sum());
        orderRepository.save(order);
    }

    @Override
    public void updateById(Long id, OrderRequest orderRequest) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            Order orderToUpdate = order.get();
            orderToUpdate.setOrderName(orderRequest.getOrderName());
            orderToUpdate.setOrderDescription(orderRequest.getOrderDescription());
            orderToUpdate.setOrderStatus(orderStatusRepository.getById(1L));
            orderToUpdate.setUser(userRepository.getById(orderRequest.getUserId()));

            List<Article> articles = new ArrayList<>();
            for (String articleCode : orderRequest.getArticleCodes()){
                articles.add(articleRepository.findByArticleCode(articleCode)
                        .orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article with that code does not exist.")
                        ));
            }
            orderToUpdate.setArticles(articles);
            orderToUpdate.setOrderPrice(articles.stream().mapToDouble(Article::getArticlePrice).sum());
            orderRepository.save(orderToUpdate);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with that ID does not exist.");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()){
            orderRepository.deleteById(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with that ID does not exist.");
        }
    }
}
