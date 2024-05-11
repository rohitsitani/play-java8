package com.rohit.immutable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyImmutable {

	public static void main(String[] args) {

		immutable();
		autoboxing(Arrays.asList(12345, 123456, 123456, 123456, 123456, 1, 2, 2, 1000, 9999, 9998, 10000, 10000, 10000,
				10000));
	}

	private static void immutable() {
		List<Integer> intList = Arrays.asList(1, 2, 3);

		for (Integer obj : intList) {
			obj = obj + 1;
		}
		System.out.println(
				"The value does not change as Integer is immutable like String and other Wrapper classes:" + intList);
	}

	public static int autoboxing(List<Integer> candles) {
		// Write your code here
		Collections.sort(candles);
		System.out.println(candles);
		int count = 0;
		for (Integer candle : candles) {
			// if we use == here then it will only work with -127 to 127 autobox of int.
			if (candle.equals(candles.get(candles.size() - 1))) {
				count = count + 1;
			}
		}
		
		int count1 = 0;
		for (Integer candle : candles) {
			// if we use == here then it will only work with -127 to 127 autobox of int.
			if (candle == (candles.get(candles.size() - 1))) {
				count1 = count1 + 1;
			}
		}
		System.out.println(String.format("count with equals is %s, count with == is %s",count, count1 ));
		return count;

	}

}
