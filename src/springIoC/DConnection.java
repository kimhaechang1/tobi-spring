package org.example.springIoC;

public class DConnection implements Connection {

	public DConnection() {
		System.out.println("using DConnection");
	}
	@Override
	public void prepareStatement() {
		System.out.println("call DConnection.prepareStatement");
	}

	@Override
	public void close() {
		System.out.println("call DConnection.close");
	}
}
