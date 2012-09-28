package com.cisco.fib;

public class FibLib {

	// Java version
	public static long fibJ(long n) {
		if(n==0) return 0;
		if(n==1) return 1;
		return fibJ(n-1)+fibJ(n-2);
	}
	
	// Native version 
	static {
		System.loadLibrary("Fib");
	}
	public static native long fibN(long n);
	
	public static void main(String[] args) {
		FibLib.fibJ(23);
	}
}
