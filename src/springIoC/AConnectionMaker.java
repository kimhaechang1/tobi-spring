package org.example.springIoC;

public class AConnectionMaker extends ConnectionMaker{
	@Override
	public Connection getConnection() {
		return new AConnection();
	}
}
