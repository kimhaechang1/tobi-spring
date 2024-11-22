package org.example.springIoC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {


	@Bean
	public UserDao userDao() {
		return new UserDao(getConnectionMaker());
	}

	@Bean
	public ConnectionMaker getConnectionMaker() {
		return new DConnectionMaker();
	}

	@Bean
	public ConnectionMaker getAConnection() {
		return new AConnectionMaker();
	}

	@Bean
	public UserDao userDaoByA() {
		return new UserDao(getAConnection());
	}
}
