package com.example.xb.demo.tx_xa.datasource;

import org.enhydra.jdbc.standard.StandardXADataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
public class DataSourceConfig {
    @Bean(name = "userDataSource")
    @Primary
    public DataSource userDataSource() {
        StandardXADataSource ds = new StandardXADataSource();
        try {
            ds.setDriverName("com.mysql.jdbc.Driver");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ds.setUrl("jdbc:mysql://localhost:3306/tx_user");
        ds.setUser("root");
        ds.setPassword("123456");

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(ds);
        atomikosDataSourceBean.setUniqueResourceName("DBResource_user");
        atomikosDataSourceBean.setPoolSize(5);
        return atomikosDataSourceBean;
    }

    @Bean(name = "orderDataSource")
    public DataSource orderDataSource() {
        StandardXADataSource ds = new StandardXADataSource();
        try {
            ds.setDriverName("com.mysql.jdbc.Driver");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ds.setUrl("jdbc:mysql://localhost:3306/tx_order");
        ds.setUser("root");
        ds.setPassword("123456");

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(ds);
        atomikosDataSourceBean.setUniqueResourceName("DBResource_order");
        atomikosDataSourceBean.setPoolSize(5);
        return atomikosDataSourceBean;
    }

    @Bean(name = "productDataSource")
    public DataSource productDataSource() {
        StandardXADataSource ds = new StandardXADataSource();
        try {
            ds.setDriverName("com.mysql.jdbc.Driver");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ds.setUrl("jdbc:mysql://localhost:3306/tx_product");
        ds.setUser("root");
        ds.setPassword("123456");

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(ds);
        atomikosDataSourceBean.setUniqueResourceName("DBResource_product");
        atomikosDataSourceBean.setPoolSize(5);
        return atomikosDataSourceBean;
    }

    /*
     * 使用这个来做总事务 后面的数据源就不用设置事务了
     * */
    @Bean(name = "transactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }

    @Bean(name = "userJdbcTemplate")
    @Primary
    public JdbcTemplate userJdbcTemplate(@Qualifier("userDataSource") DataSource db1DataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(db1DataSource);

        return jdbcTemplate;
    }

    @Bean(name = "orderJdbcTemplate")
    public JdbcTemplate orderJdbcTemplate(@Qualifier("orderDataSource") DataSource db2DataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(db2DataSource);

        return jdbcTemplate;
    }

    @Bean(name = "productJdbcTemplate")
    public JdbcTemplate productJdbcTemplate(@Qualifier("productDataSource") DataSource db3DataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(db3DataSource);

        return jdbcTemplate;
    }
}

