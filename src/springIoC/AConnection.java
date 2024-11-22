package org.example.springIoC;

public class AConnection implements Connection{

	public AConnection() {
		System.out.println("using AConnection");
	}

	@Override
	public void prepareStatement() {

	}

	@Override
	public void close() {

	}
}
