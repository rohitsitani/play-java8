package com.rohit.sample.service;

@FunctionalInterface
public interface MyService2 {
	
	abstract void abstractMethod();
	
	default void defaultMethod2(String str) {
		System.out.println("Interface2:"+str);
	}
	
	default void defaultMethodCommon(String str) {
		System.out.println("Interface2:"+str);
		return;
	}

	static void staticMethod() {
		System.out.println("Static Method int 2");
	}

}
