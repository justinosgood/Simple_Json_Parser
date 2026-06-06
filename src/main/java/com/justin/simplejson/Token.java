package com.justin.simplejson;

sealed interface Token permits Token.Punctuator, Token.Null, Token.StringToken, Token.NumberToken, Token.BooleanToken {
    record StringToken(String  value)  implements Token {}
    record NumberToken(String  raw)    implements Token {}
    record BooleanToken(boolean value) implements Token {}

    enum Null                  implements Token { NULL }

    enum Punctuator            implements Token {
        START_OBJ, // {
        END_OBJ,   // }
        START_ARR, // [
        END_ARR,   // ]
        COMMA,     // ,
        COLON      // :
    }
}

//record Token(TokenType type, String value) {
//    static Token createString(String value) {
//        return new Token(TokenType.STRING, value);
//    }
//    static Token createNumber(String value) {
//        return new Token(TokenType.NUMBER, value);
//    }
//}
//
//enum TokenType {
//    STRING,
//    NUMBER,
//    BOOLEAN,
//    START_OBJECT,
//    END_OBJECT,
//    START_ARRAY,
//    END_ARRAY,
//    COMMA,
//    COLON
//}
//final class Tokens {
//    private Tokens() {} // prevent instantiation
//
//    static final Token START_OBJECT = new Token(TokenType.START_OBJECT, "{");
//    static final Token END_OBJECT = new Token(TokenType.END_OBJECT, "}");
//    static final Token START_ARRAY = new Token(TokenType.START_ARRAY, "[");
//    static final Token END_ARRAY = new Token(TokenType.END_ARRAY, "]");
//    static final Token COMMA = new Token(TokenType.COMMA, ",");
//    static final Token COLON = new Token(TokenType.COLON, ":");
//}