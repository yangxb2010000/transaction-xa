package com.example.xb.demo.tx_xa.service;

import com.example.xb.demo.tx_xa.dao.OrderDao;
import com.example.xb.demo.tx_xa.dao.ProductDao;
import com.example.xb.demo.tx_xa.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component()
public class OrderService {
    private UserDao userDao;
    private OrderDao orderDao;
    private ProductDao productDao;

    @Autowired
    public OrderService(UserDao userDao, OrderDao orderDao, ProductDao productDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    public OrderService() {
    }

    @Transactional
    public void CreateOrder(CreateOrderRequest request) {
        float amount = request.getCount() * 2.0f;
        orderDao.CreateOrder(request.getUserId(), request.getProductId(), request.getCount(), amount);
        userDao.DeductBalance(request.getUserId(), amount);
        productDao.ReduceInventory(request.getProductId(), request.getCount());
    }
}
