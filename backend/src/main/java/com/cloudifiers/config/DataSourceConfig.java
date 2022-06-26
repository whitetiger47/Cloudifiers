package com.cloudifiers.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudifiers.model.DatabaseSecret;

@Configuration
public class DataSourceConfig {
	
	@Autowired
	private DatabaseSecret databaseSecret;

	@Bean
	public DataSource dataSource() {
		String databaseUrl = new StringBuilder()
				.append("jdbc:")
				.append(databaseSecret.getEngine())
				.append("://")
				.append(databaseSecret.getHost())
				.append(":")
				.append(databaseSecret.getPort())
				.append("/")
				.append(databaseSecret.getDatabaseName())
				.toString();
		return DataSourceBuilder.create()
				.driverClassName(databaseSecret.getDatabaseDriver())
				.url(databaseUrl)
				.username(databaseSecret.getUsername())
				.password(databaseSecret.getPassword())
				.build();
	}

}
