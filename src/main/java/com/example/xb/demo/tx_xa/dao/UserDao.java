package com.example.xb.demo.tx_xa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component()
public class UserDao {
    @Autowired
    @Qualifier("userJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void DeductBalance(int userId, float number) {
        String sql = "update user set balance = balance - ? where id = ? and balance > ?";

        int ret = jdbcTemplate.update(sql, number, userId, number);
        if (ret <= 0) {
            throw new RuntimeException(MessageFormat.format("用户: {0}不存在或者余额不足", userId));
        }
    }
}




