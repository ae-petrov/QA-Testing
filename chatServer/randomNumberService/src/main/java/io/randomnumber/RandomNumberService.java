package io.randomnumber;

import io.javalin.Javalin;

import java.util.Random;

public class RandomNumberService {

    public static void main(String[] args) {
        Javalin randomApp = Javalin.create().start(7070);
        randomApp.get("/hashcode", get -> get.result(getRandomNumber()));
    }

    private static String getRandomNumber() {
        Random random = new Random();
        return String.format("%2d", random.nextInt(100));
    }

}
