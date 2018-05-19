package com.common.cpp;

/**
 * java调用C++ DLL库方法
 * @author Administrator
 *
 */
public class Java2cpp {

	static{
		System.loadLibrary("javaCallcpp");
	}
	
	public native int DLL_ADD(int a, int b);
	
	public static void main(String[] args) {
		Java2cpp test = new Java2cpp();
		int sum = test.DLL_ADD(2, 3);
		System.out.println("javaCallcpp result: " + sum);
	}
	
}
