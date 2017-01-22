package com.rohit.lambda;

//@FunctionalInterface
public interface MyLambdaInterface {
	
	abstract int calculate(int a, int b);
	
	//below method cannot be part of the abstract method of the interface as only one is allowed. 
	//serves as compiler warning
	//abstract void error();
	
	public static void main(String[] args) {
		MyLambdaInterface sum = (int a, int b) -> {  return (b + a); };
		MyLambdaInterface diff = (int a, int b) -> {  return (b - a); };
		MyLambdaInterface multiply = (int a, int b) -> {  return (b * a); };
		//here the actual calculation is done by a normal method which has no lambda awareness
		//here that method is passed as a parameter and then the rest of the anonymous class with methods is auto generated
		MyLambdaInterface max = Math::max;
		System.out.println("sum:" + sum.calculate(3, 4));
		System.out.println("diff:" + diff.calculate(3, 4));
		System.out.println("multiply:" + multiply.calculate(3, 4));
		System.out.println("max:" + max.calculate(3, 4));
	}
}
