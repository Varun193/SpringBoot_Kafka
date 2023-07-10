package com.spring.controller;

import com.spring.dto.Order;
import com.spring.dto.OrderEvent;
import com.spring.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;

//    public OrderController(OrderProducer orderProducer) {
//        this.orderProducer = orderProducer;
//    }

    @PostMapping("/orders")
    public String publishOrder(Order order) {

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent= new OrderEvent();
        orderEvent.setMessage("Order sent");
        orderEvent.setStatus("Confirmed");
        orderEvent.setOrder(order);

        return "order sent successfully";
    }
}
