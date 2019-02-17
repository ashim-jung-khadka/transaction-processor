package com.demo.induction.tp.util;

public class StartTimer {

	public static void main(String[] args) {
		TimeIt.code(StartTimer::testing);
	}

	private static void testing() {
		System.out.println("running");
	}

}

interface TimeIt {

	void run();

	static void code(TimeIt timeIt) {
		long start = System.nanoTime();

		timeIt.run();

		long end = System.nanoTime();
		System.out.println("Time: " + (end - start) / 1.0e3);
	}
}