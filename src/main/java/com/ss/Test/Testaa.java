package com.ss.Test;

import java.util.Random;
import java.util.UUID;

public class Testaa {

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder("select");

        System.out.println(new Random().nextInt(100));

    }

    private static String getSql(String table){
        StringBuilder stringBuilder = new StringBuilder("select "+table+"? " +"from"+" ss");
        return stringBuilder.deleteCharAt(stringBuilder.indexOf("?")).toString();

    }

}
