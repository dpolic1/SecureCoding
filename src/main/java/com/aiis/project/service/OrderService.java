package com.aiis.project.service;

import com.aiis.project.dto.OrderDTO;
import com.aiis.project.request.OrderRequest;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAll();

    OrderDTO findById(Long id);

    List<OrderDTO> findByUserId(Long id);

    void changeStatus(Long id, String status);

    void create(OrderRequest orderRequest);

    void updateById(Long id, OrderRequest orderRequest);

    void deleteById(Long id);
}
