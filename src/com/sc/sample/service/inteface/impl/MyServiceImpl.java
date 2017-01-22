package com.sc.sample.service.inteface.impl;

import com.sc.sample.service.inteface.MyService1;
import com.sc.sample.service.inteface.MyService2;

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


	public static void main(String[] args) {
		MyServiceImpl myService = new MyServiceImpl();
		myService.defaultMethod1("impl");
		myService.defaultMethod2("impl");
		myService.defaultMethodCommon("impl");
		MyService1.staticMethod();
	}


	@Override
	public String toString() {
		return "MyServiceImpl []";
	}


	@Override
	public void abstractMethod() {
		// TODO Auto-generated method stub
		
	}
	
	
}
