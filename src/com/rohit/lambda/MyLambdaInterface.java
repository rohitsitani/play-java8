package com.rohit.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

//@FunctionalInterface
public interface MyLambdaInterface {
	
	abstract int calculate(int a, int b);
	
	//below method cannot be part of the abstract method of the interface as only one is allowed. 
	//serves as compiler warning
	//abstract void error();
	
	public static void main(String[] args) {
		
		MyLambdaInterface sum = (int a, int b) -> {  return (b + a); };
		System.out.println("sum:" + sum.calculate(3, 4));
		
		MyLambdaInterface diff = (int a, int b) -> {  return (b - a); };
		System.out.println("diff:" + diff.calculate(3, 4));
		
		MyLambdaInterface multiply = (int a, int b) -> {  return (b * a); };
		System.out.println("multiply:" + multiply.calculate(3, 4));
		
		//using the Math class
		MyLambdaInterface maxLambda = (int a, int b) -> { return Math.max(a, b);};
		System.out.println("max:" + maxLambda.calculate(3, 4));
		
		//here the actual calculation is done by a normal method which has no lambda awareness
		//here that method is passed as a parameter and then the rest of the anonymous class with methods is auto generated
		MyLambdaInterface maxLambdaMethod = Math::max;
		System.out.println("max:" + maxLambdaMethod.calculate(3, 4));
		
		//using the general Function class of the new function package in java 
		Function<Integer, Integer> function = Math::abs;
		System.out.println("function.apply:" + function.apply(-111));
		
		//using the general Function class of the new function package in java 
		BiFunction<Integer, Integer, Integer> biFunction = Math::max;
		System.out.println("biFunction.apply:" + biFunction.apply(3, 4));
	}
}
