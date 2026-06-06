package com.justin.simplejson;

import com.justin.simplejson.util.ListIterator;

import java.util.List;

final class JsonParser {
    private final ListIterator<Token> listIterator;


    JsonParser(List<Token> tokens) {
        listIterator = new ListIterator<>(tokens);
    }


    JsonElement parse() {
        return parseNextElement();
    }

    private JsonElement parseNextElement() {
        return switch (listIterator.next()) {
            case Token.Punctuator.START_OBJ ->
                    parseNextObject();

            case Token.Punctuator.START_ARR ->
                    parseNextArray();

            case Token.StringToken(String value) ->
                    new JsonString(value);

            case Token.NumberToken(String raw) ->
                    parseNextNumber(raw);

            case Token.BooleanToken(boolean value) ->
                    new JsonBoolean(value);

            case Token.Null.NULL ->
                    JsonNull.NULL;

//            case Token.Punctuator.END_OBJ -> null;
//            case Token.Punctuator.END_ARR -> null;
//            case Token.Punctuator.COMMA -> null;
//            case Token.Punctuator.COLON -> null;

            default -> throw new IllegalStateException();
        };
    }

    private JsonObject parseNextObject() {
        JsonObject jsonObject = new JsonObject();

        Token token;
        while ((token = listIterator.next()) != Token.Punctuator.END_OBJ) {
            if (token == Token.Punctuator.COMMA) token = listIterator.next();

            // parse value key
            String key = ((Token.StringToken) token).value();

            // skip COLON
            listIterator.next();

            // recursively parse value
            JsonElement value = parseNextElement();

            jsonObject.members.put(key, value);
        }

        return jsonObject;
    }

    private JsonArray parseNextArray() {
        JsonArray jsonArray = new JsonArray();

        Token token = listIterator.peek();

        if (token == Token.Punctuator.END_ARR) {
            listIterator.next();
            return jsonArray;
        }

        do {
            jsonArray.elements.add(parseNextElement());
        } while (listIterator.consumeIfEquals(Token.Punctuator.COMMA));

        // skip END_ARR
        listIterator.next();

        return jsonArray;
    }

    // todo: does not account for E or e numbers
    private JsonNumber parseNextNumber(String raw) {
        Number number;
        if (raw.contains(".")) {
            number = Double.parseDouble(raw);
        } else {
            number = Long.parseLong(raw);
        }
        return new JsonNumber(number);
    }
}
