package com.khc.practice.tobi.designpattern.factorymethodpattern.consumer;

import com.khc.practice.tobi.designpattern.factorymethodpattern.product.CommonConnection;

public class AConnection implements CommonConnection {

	// 각 회사에서 사용하는 db에 맞도록 커넥션 객체를 제공받은 인터페이스를 기반으로 만든다.


	public AConnection() {
		System.out.println("Using AConnection");
	}
	@Override
	public void prepareStatement() {

	}

	@Override
	public void close() {

	}
}
