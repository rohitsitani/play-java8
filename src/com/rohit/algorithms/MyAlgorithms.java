package com.rohit.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyAlgorithms {

	public static void staircase(int n) {
		// Write your code here
		for (int i = 0; i < n; i++) {
			String str = "#";
			String space = "";
			for (int j = i; j < n - 1; j++) {
				space = " " + space;
			}
			for (int j = 0; j < i; j++) {
				str = "#" + str;
			}
			str = space + str;
			System.out.println(str);
		}
	}

	public static void miniMaxSum(List<Integer> arr) {
		// Write your code here
		Collections.sort(arr);
		Long minSum = 0L;
		Long maxSum = 0L;
		for (int i = 0; i < arr.size(); i++) {
			if (i != arr.size() - 1)
				minSum = minSum + arr.get(i);
			if (i != 0) {
				maxSum = maxSum + arr.get(i);
			}
		}
		System.out.println(minSum + " " + maxSum);

	}
	
	
	public static int birthdayCakeCandles(List<Integer> candles) {
	    // Write your code here
	   Collections.sort(candles);
	   System.out.println(candles);
	    int count = 0;
	    for (Integer candle : candles) {
	    	//if we use == here then it will only work with -127 to 127 autobox of int. 
	        if(candle.equals(candles.get(candles.size()-1))) {
	            count = count +1;
	        }
	    }
	    return count;

	    }


	public static void main(String[] args) {
		MyAlgorithms.staircase(10);
		MyAlgorithms.miniMaxSum(Arrays.asList(1,2,3,4,5));
		int count = MyAlgorithms.birthdayCakeCandles(Arrays.asList(12345, 123456, 123456, 123456, 123456, 1,2,2, 1000, 9999, 9998, 10000, 10000, 10000, 10000));
		System.out.println(count);
	}

}
