package com.rohit.inter.service.impl;

import com.rohit.inter.service.MyService1;
import com.rohit.inter.service.MyService2;

public class MyServiceImpl extends Object implements MyService1, MyService2 {
	
	 @Override
	public void defaultMethod1(String str) {
		// TODO Auto-generated method stub
		MyService1.super.defaultMethod1(str);
	}

	@Override
	public void defaultMethodCommon(String str) {
		// TODO Auto-generated method stub
		MyService1.super.defaultMethodCommon(str);
	}

	@Override
	public String toString() {
		return "MyServiceImpl []";
	}

	@Override
	public void abstractMethod() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		MyServiceImpl myService = new MyServiceImpl();
		myService.defaultMethod1("impl");
		myService.defaultMethod2("impl");
		myService.defaultMethodCommon("impl");
		MyService1.staticMethod();
	}
	
}
