package com.example.xb.demo.tx_xa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class ProductDao {
    @Autowired
    @Qualifier("productJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void ReduceInventory(int productId, int count) {
        String sql = "update product set inventory = inventory - ? where id = ? and inventory > ?";

        int ret = jdbcTemplate.update(sql, count, productId, count);
        if (ret <= 0) {
            throw new RuntimeException(MessageFormat.format("商品: {0}不存在或者库存不足", productId));
        }
    }
}
