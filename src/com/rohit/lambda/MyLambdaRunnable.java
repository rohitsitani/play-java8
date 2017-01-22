package com.rohit.lambda;

public class MyLambdaRunnable implements Runnable {
	
	@Override
	public void run() {
		System.out.println(this.getClass().getSimpleName() + ":" + Thread.currentThread().toString());
	}
	
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(this.getClass().getSimpleName() + ":" + Thread.currentThread().toString());
			}
		};
		
		Runnable lambda = () -> System.out.println(Thread.class.getSimpleName() + ":" + Thread.currentThread().toString());
		
		//old way: runnable
		new Thread(new MyLambdaRunnable()).start();
		//still old way: anonymous class
		new Thread(runnable).start();
		//new: lambda in return type
		new Thread(lambda).start();
		//new: lambda declared inline
		new Thread(() -> System.out.println(Thread.class.getSimpleName() + ":" + Thread.currentThread().toString())).start();
	}

}
