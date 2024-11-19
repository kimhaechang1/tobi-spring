package com.khc.practice.tobi.designpattern.factorymethodpattern.consumer;

import com.khc.practice.tobi.designpattern.factorymethodpattern.product.CommonConnection;
import com.khc.practice.tobi.designpattern.factorymethodpattern.product.CommonDao;

public class ADao extends CommonDao {
	@Override
	protected CommonConnection getConnection() {
		// 자신들이 구현한 connection 객체를 반환하기만 하면 문제가 없다.
		// 연결로직이 여기서 구현된다.
		return new AConnection();
	}
}
