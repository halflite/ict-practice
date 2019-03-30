package com.example.todo.app.config;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomaConfig implements Config  {

	private final DataSource dataSource;
	
	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public Dialect getDialect() {
		return new MysqlDialect();
	}

	@Autowired
	public DomaConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
