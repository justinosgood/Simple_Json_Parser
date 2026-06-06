package com.justin.simplejson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SimpleJson {
    public static JsonElement fromJson(File jsonFile) {
        return fromJson(fileToString(jsonFile));
    }

    public static JsonElement fromJson(String jsonString) {
        JsonLexer jsonLexer = new JsonLexer(removeWhitespace(jsonString));
        return fromJson(jsonLexer.tokenize());
    }

    static JsonElement fromJson(List<Token> tokens) {
        JsonParser jsonParser = new JsonParser(tokens);
        return jsonParser.parse();
    }

    public static String fileToString(File jsonFile) {
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

    public static String removeWhitespace(String line) {
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