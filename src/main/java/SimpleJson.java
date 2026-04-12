import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimpleJson {
    public static void fromJson(File jsonFile) {
        String jsonString = fileToString(jsonFile);
        ArrayList<Token> tokens = tokenize(jsonString);

        tokens.forEach(System.out::println);
    }

    private static ArrayList<Token> tokenize(String json) {
        Pointer pointer = new Pointer(json);
        ArrayList<Token> tokens = new ArrayList<>();

        while (pointer.hasNext()) {
            pointer.nextChar();

            if (pointer.c == '"') {
                StringBuilder value = new StringBuilder();
                pointer.nextChar();
                while (pointer.c != '"') {
                    value.append(pointer.c);
                    pointer.nextChar();
                }
                tokens.add(new Token(TokenType.STRING, value.toString()));
            }
            else if (pointer.c == '-' || isNumber(pointer.c)) {
                StringBuilder value = new StringBuilder();
                while (pointer.c == '-'
                        || pointer.c == '+'
                        || pointer.c == 'e'
                        || pointer.c == '.'
                        || isNumber(pointer.c)
                ) {
                    value.append(pointer.c);
                    pointer.nextChar();
                }
                tokens.add(new Token(TokenType.NUMBER, value.toString()));
            }
            else if (pointer.c == '{') {
                tokens.add(Token.OPEN_BRACE);
            }
            else if (pointer.c == '}') {
                tokens.add(Token.CLOSE_BRACE);
            }
            else if (pointer.c == '[') {
                tokens.add(Token.OPEN_BRACKET);
            }
            else if (pointer.c == ']') {
                tokens.add(Token.CLOSE_BRACKET);
            }
            else if (pointer.c == ',') {
                tokens.add(Token.COMMA);
            }
            else if (pointer.c == ':') {
                tokens.add(Token.COLON);
            }
        }
        return tokens;
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

    private static boolean isNumber(char c) {
        try {
            Double.parseDouble( Character.toString(c) );
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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