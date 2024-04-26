package com.rohit.immutable;

import java.util.Arrays;
import java.util.List;

public class MyImmutable {

	public static void main(String[] args) {
		List<Integer> intList = Arrays.asList(1,2,3);
		
		for(Integer obj : intList) {
			obj = obj + 1;
		}
		System.out.println("The valur does not change as Integer is immutable like String and other Wrapper classes:" + intList);
	}

}
