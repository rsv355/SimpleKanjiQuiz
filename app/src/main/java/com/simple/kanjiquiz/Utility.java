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

    public static int generateRandomForAI(){
        Random rand = new Random();
        int randomInt = rand.nextInt(6);
        return randomInt;
    }

    public static int generateRandomForQuestion(int maxValue){
        Random rand = new Random();
        int randomInt = rand.nextInt(maxValue);
        return randomInt;
    }

    public static int generateRandomForTableName(){
        Random rand = new Random();
        int randomInt = rand.nextInt(3);
        return randomInt;
    }
}
