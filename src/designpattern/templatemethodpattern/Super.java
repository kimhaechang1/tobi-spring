package com.khc.practice.tobi.designpattern.templatemethodpattern;

public abstract class Super {

	// 변하지 않는 기능은 슈퍼클래스에 만들어두고, 자주 변경이 발생하는 기능은 서브클래스에서 구현할 수 있도록 확장한다.
	public void templateMethod() {
		hookMethod();
		abstractMethod();
	}

	protected void hookMethod() {
		// 선택적으로 하위 클래스에서 오버라이딩 가능하도록 만든 메소드
		System.out.println("후킹");
	}

	public abstract void abstractMethod();
	// 반드시 서브클래스에서 오버라이딩 하도록 만든 메소드
}