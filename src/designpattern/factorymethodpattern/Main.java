package com.khc.practice.tobi.designpattern.factorymethodpattern;

import com.khc.practice.tobi.designpattern.factorymethodpattern.consumer.ADao;

public class Main {

	// 상위 클래스에선 하위클래스에서 구현한 메소드를 단순히 호출하고
	// 인터페이스 타입의 오브젝트를 반환받아서 사용하는 메소드

	public static void main(String[] args) {

		ADao dao = new ADao();
		dao.add();
		dao.get();
	}
}
