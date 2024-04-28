package com.rohit.algorithms;

public class MyAlgorithms {
	
    public static void staircase(int n) {
    // Write your code here
    for(int i = 0; i<n; i++) {
        String str = "#";
        String space = "";
        for(int j=i; j<n-1; j++) {
            space = " "+space;
        }
        for(int j=0; j<i; j++) {
            str = "#"+str;
        }
        str=space+str;
        System.out.println(str);
    }
    }

	public static void main(String[] args) {
		MyAlgorithms algorithms = new MyAlgorithms();
		algorithms.staircase(10);
	}

}
