package com.justin.simplejson;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File myFile = new File("src/main/resources/test.json");

        JsonType.JsonObject rootJsonObject = SimpleJson.fromJson(myFile);

        System.out.println(rootJsonObject);

        //todo: make unboxing json data accessible
    }
}
