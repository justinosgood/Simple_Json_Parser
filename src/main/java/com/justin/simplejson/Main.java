package com.justin.simplejson;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File myFile = new File("src/main/resources/test.json");
        JsonElement jsonElement = SimpleJson.fromJson(myFile);
    }
}
