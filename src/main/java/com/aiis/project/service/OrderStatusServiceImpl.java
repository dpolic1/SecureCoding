package com.aiis.project.service;


import com.aiis.project.model.OrderStatus;
import com.aiis.project.repository.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository){
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusRepository.findAll();
    }
}
