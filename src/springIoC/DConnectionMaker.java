package org.example.springIoC;

public class DConnectionMaker extends ConnectionMaker {
	@Override
	public Connection getConnection() {
		return new DConnection();
	}
}
