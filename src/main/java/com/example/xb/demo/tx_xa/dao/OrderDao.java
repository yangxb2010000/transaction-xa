package com.example.xb.demo.tx_xa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class OrderDao {
    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public int CreateOrder(int userId, int productId, int count, float amount) {
        String sql = "insert into `order` (`user_id`, `product_id`, `count`, `amount`) values (?, ?, ?, ?)";

        KeyHolder keyHolder1 = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            //设置返回的主键字段名
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ps.setInt(3, count);
            ps.setFloat(4, amount);
            return ps;
        }, keyHolder1);

        return keyHolder1.getKey().intValue();
    }
}
