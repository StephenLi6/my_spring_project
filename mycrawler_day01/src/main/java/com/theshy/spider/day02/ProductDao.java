package com.theshy.spider.day02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * 将数据保存到数据库 // 首先有一个数据库 表也建设OK 
 * 通过程序将数据写入到数据库-------jdbc\mybatis\springjdbctemplate|DataSource C3P0 DRUID
 *
 */
public class ProductDao extends JdbcTemplate /*extends JdbcDaoSupport*/ {
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	public ProductDao() {
		// 创建C3P0的datasource 1.配置 2.代码
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		// 1.url
		// 2.driver
		// 3.username&password
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setJdbcUrl("jdbc:mysql://192.168.214.100:3306/spider?characterEncoding=utf-8");
		setDataSource(dataSource);
	}

	public void saveProduct(Product producet) {
		String sql = "INSERT INTO `spider`.`jd_product`(`id`, `name`, `title`, `price`, `maidian`, `pinpai`, `xinghao`, `url`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		update(sql, producet.getId(), producet.getName(), producet.getTitle(), producet.getPrice(), producet.getMaidian(), producet.getPinpai(), producet.getXinghao(), producet.getUrl());
	}

}
