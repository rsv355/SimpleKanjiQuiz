package com.simple.kanjiquiz;

import java.util.Random;

public class Utility {

	public static String mSelectTest;
	public static int mOptionAorB;

	public static int generateRandomQuestion() {
		Random r = new Random();
		int i1 = r.nextInt(117 - 0) + 0;
		return i1;
	}
}
