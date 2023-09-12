package com.aiis.project.dto;


import com.aiis.project.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusDTO {
    private String statusFlag;

    public OrderStatusDTO(OrderStatus status){
        this.statusFlag = status.getStatusFlag();
    }
}
