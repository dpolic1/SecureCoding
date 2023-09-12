package com.aiis.project.controller;

import com.aiis.project.dto.OrderDTO;
import com.aiis.project.request.OrderRequest;
import com.aiis.project.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping()
    public List<OrderDTO> getAllOrders(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findOrderById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @GetMapping("/user/{id}")
    public List<OrderDTO> findOrderByUserId(@PathVariable Long id){
        return orderService.findByUserId(id);
    }

    @PostMapping()
    public void createOrder(@RequestBody @Valid OrderRequest orderRequest){
        orderService.create(orderRequest);
    }

    @PutMapping("/{id}")
    public void updateOrderById(@PathVariable Long id, @RequestBody @Valid OrderRequest orderRequest){
        orderService.updateById(id, orderRequest);
    }

    @PutMapping("/{id}/approve")
    public void approveOrderById(@PathVariable Long id){
        orderService.changeStatus(id, "Approved");
    }

    @PutMapping("/{id}/deny")
    public void denyOrderById(@PathVariable Long id){
        orderService.changeStatus(id, "Denied");
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id){
        orderService.deleteById(id);
    }
}
