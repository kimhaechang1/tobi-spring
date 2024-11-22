package org.example.springIoC;

public class UserDao {

	private ConnectionMaker connectionMaker;

	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public void add() {
		connectionMaker.getConnection();
		System.out.println("call add");
	}

	public void get() {
		connectionMaker.getConnection();
		System.out.println("call get");
	}
}
