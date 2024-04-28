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
		Integer minSum = 0;
		Integer maxSum = 0;
		for (int i = 0; i < arr.size(); i++) {
			if (i != arr.size() - 1)
				minSum = minSum + arr.get(i);
			if (i != 0) {
				maxSum = maxSum + arr.get(i);
			}
		}
		System.out.println(minSum + " " + maxSum);

	}

	public static void main(String[] args) {
		MyAlgorithms.staircase(10);
		MyAlgorithms.miniMaxSum(Arrays.asList(1,2,3,4,5));

	}

}
