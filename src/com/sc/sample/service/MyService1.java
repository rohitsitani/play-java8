package com.sc.sample.service;

public interface MyService1 {
	
	default void defaultMethod1(String str) {
		System.out.println("Interface1:"+str);
	}
	
	default void defaultMethodCommon(String str) {
		System.out.println("Interface1:"+str);
		return;
	}
	
	static void staticMethod() {
		System.out.println("Static Method Int 1");
	}

}
