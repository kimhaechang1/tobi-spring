package com.khc.practice.tobi.designpattern.factorymethodpattern.product;

public interface CommonConnection {
	void prepareStatement();
	void close();
}