package com.lin.springbootvue_loginexample.demo.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;


/**
 * 数据库连接池配置
 */
@Configuration
public class DataSourceConfig {
    /**
     * druid初始化
     * @return
     * @throws SQLException
     */
    @Primary //默认数据源
    @Bean(name = "dataSource", destroyMethod = "close")
    public DruidDataSource Construction() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo_one?serverTimezone=GMT%2B8&amp" +
                "\n");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //配置最大连接
        dataSource.setMaxActive(20);
        //配置初始连接
        dataSource.setInitialSize(1);
        //配置最小连接
        dataSource.setMinIdle(1);
        //连接等待超时时间
        dataSource.setMaxWait(60000);
        //间隔多久进行检测,关闭空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //一个连接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(300000);
        //用来检测是否有效的sql
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        //打开PSCache,并指定每个连接的PSCache大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(20);
        //配置sql监控的filter
        dataSource.setFilters("stat, wall, log4j");
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("druid datasource init fail");
        }
        return dataSource;
    }

    /**
     * druid监控
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "qinxue");
        reg.addInitParameter("loginPassword", "111220");
        return reg;
    }

    /**
     * druid监控过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }


}
