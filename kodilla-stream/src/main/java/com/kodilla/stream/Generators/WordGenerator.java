package com.kodilla.stream.Generators;

import java.util.Random;

public class WordGenerator {

    private static Random random = new Random();
//K-75
    //M-77
    public static String generateRandomWord() {
        char[] word = new char[random.nextInt(8) + 3];
        for (int i = 0; i < word.length; i++) {
            word[i] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }
    public static char generateGender(){
        return (char) ('K'+random.nextInt(2));
    }
}
