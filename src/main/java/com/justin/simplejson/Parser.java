package com.justin.simplejson;

import java.util.List;

final class Parser {
    private final ListIterator<Token> listIterator;


    Parser(List<Token> tokens) {
        listIterator = new ListIterator<>(tokens);
    }


    JsonType.JsonElement parseNextElement() {
        return switch (listIterator.next()) {
            case Token.Punctuator.START_OBJ ->
                    parseNextObject();

            case Token.Punctuator.START_ARR ->
                    parseNextArray();

            case Token.StringToken(String value) ->
                    new JsonType.JsonString(value);

            case Token.NumberToken(String raw) ->
                    parseNextNumber(raw);

            case Token.BooleanToken(boolean value) ->
                    new JsonType.JsonBoolean(value);

            case Token.Null.NULL ->
                    null;

//            case Token.Punctuator.END_OBJ -> null;
//            case Token.Punctuator.END_ARR -> null;
//            case Token.Punctuator.COMMA -> null;
//            case Token.Punctuator.COLON -> null;

            default -> throw new IllegalStateException();
        };
    }

    private JsonType.JsonObject parseNextObject() {
        JsonType.JsonObject jsonObject = new JsonType.JsonObject();

        Token token;
        while ((token = listIterator.next()) != Token.Punctuator.END_OBJ) {
            if (token == Token.Punctuator.COMMA) token = listIterator.next();

            // parse string key
            String key = ((Token.StringToken) token).value();

            // skip COLON
            listIterator.next();

            // recursively parse value
            JsonType.JsonElement value = parseNextElement();

            jsonObject.put(key, value);
        }

        return jsonObject;
    }

    private JsonType.JsonArray parseNextArray() {
        JsonType.JsonArray jsonArray = new JsonType.JsonArray();

        Token token = listIterator.peek();

        if (token == Token.Punctuator.END_ARR) {
            listIterator.next();
            return jsonArray;
        }

        do {
            jsonArray.add(parseNextElement());
        } while (listIterator.consumeIfEquals(Token.Punctuator.COMMA));

        // skip END_ARR
        listIterator.next();

        return jsonArray;
    }

    // todo: complete implementation
    private JsonType.JsonNumber parseNextNumber(String raw) {
        long l = Long.parseLong(raw);
        return new JsonType.JsonNumber(l);
    }
}
