package com.example.xb.demo.tx_xa.controller;

import com.example.xb.demo.tx_xa.service.CreateOrderRequest;
import com.example.xb.demo.tx_xa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request) {
        orderService.CreateOrder(request);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
