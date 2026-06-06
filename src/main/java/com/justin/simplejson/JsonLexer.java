package com.justin.simplejson;

import com.justin.simplejson.util.StringIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.justin.simplejson.Token.*;
import static com.justin.simplejson.Token.Punctuator.*;

class JsonLexer {
    // chars that make up a number in JSON
    private static final Set<Character> NUMBER_CHARS = Set.of('-', '+', 'e', 'E', '.');

    private final StringIterator stringIterator;


    JsonLexer(String jsonString) {
        stringIterator = new StringIterator(jsonString);
    }


    List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        char item;
        while (stringIterator.hasNext()) {
            item = stringIterator.next();

            if (item == '"') {

                StringBuilder string = new StringBuilder();
                while (true) {
                    item = stringIterator.next();
                    if (item == '"' && stringIterator.peekPrevious() != '\\') break;

                    if (item != '\\') {
                        string.append(item);
                    }
                }
                tokens.add( new StringToken(string.toString()) );

            } else if (Character.isDigit(item) || item == '-') {

                StringBuilder number = new StringBuilder();

                while (true) {
                    number.append(item);

                    char peeked = stringIterator.peek();
                    if (!Character.isDigit(peeked)
                            && peeked != '-'
                            && peeked != '+'
                            && peeked != 'e'
                            && peeked != 'E'
                            && peeked != '.'

                    ) break;

                    item = stringIterator.next();
                }
                tokens.add( new NumberToken(number.toString()) );
            } else if (item == 't') {

                stringIterator.skip(3);
                tokens.add( new BooleanToken(true) );

            } else if (item == 'f') {

                stringIterator.skip(4);
                tokens.add( new BooleanToken(false) );

            } else if (item == 'n') {

                stringIterator.skip(3);
                tokens.add( Null.NULL );

            } else {
                switch (item) {
                    case '{' -> tokens.add( START_OBJ );
                    case '}' -> tokens.add( END_OBJ );
                    case '[' -> tokens.add( START_ARR );
                    case ']' -> tokens.add( END_ARR );
                    case ',' -> tokens.add( COMMA );
                    case ':' -> tokens.add( COLON );
                }
            }
        }

        return tokens;
    }
}
