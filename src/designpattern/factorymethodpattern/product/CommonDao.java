package com.khc.practice.tobi.designpattern.factorymethodpattern.product;


public abstract class CommonDao {

	// dao와 connection 을 판매하는 회사임
	// 그런데 Connection의 경우 구매자 입장에서 DB가 다양하게 있기 때문에,
	// 구매자가 직접 커넥션 객체를 DB에 맞게 구현하도록 Connection을 인터페이스화 시킨다.
	// 그리고 해당 커넥션 객체를 사용해서 비즈니스 로직을 수행하는 add, get 메소드는 어짜피 인터페이스 타입만 맞으면 상관없다.
	// 하위 클래스에서 무슨타입으로 구현되었는지는 알빠노다.

	public final void add() {

		getConnection();
		System.out.println("call CommonDao add");
	}

	public final void get() {

		getConnection();
		System.out.println("call CommonDao get");
	}

	protected abstract CommonConnection getConnection();
}
