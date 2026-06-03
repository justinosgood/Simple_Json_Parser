package com.justin.simplejson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SimpleJson {
    public static JsonType.JsonObject fromJson(File jsonFile) {
        List<Token> tokens = new Lexer( fileToString(jsonFile) ).tokenize();
        //System.out.println(tokens);
        Parser parser = new Parser(tokens);
        //System.out.println(parser.parseNextElement());

        return (JsonType.JsonObject) parser.parseNextElement();
    }

    private static String fileToString(File jsonFile) {
        StringBuilder json = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(removeWhitespace(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json.toString();
    }

    private static String removeWhitespace(String line) {
        char[] lineCharArray = line.toCharArray();
        StringBuilder newLine = new StringBuilder();
        for (char c : lineCharArray) {
            if (c != ' ') {
                newLine.append(c);
            }
        }
        return newLine.toString();
    }
}