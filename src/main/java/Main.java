import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        File myFile = new File("src/main/resources/test.json");
        String json = jsonFileToString(myFile);
        ArrayList<Token> tokens = tokenizer(json);

        // Parser
        int i = 0;
        while (i < tokens.size()) {
            Token token = tokens.get(i++);
            System.out.println(token);
        }
    }

    public static boolean isNumber(char c) {
        try {
            Double.parseDouble( Character.toString(c) );
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String jsonFileToString(File jsonFile) {
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

    public static ArrayList<Token> tokenizer(String json) {
        ArrayList<Token> tokens = new ArrayList<>();
        Pointer pointer = new Pointer(json);

        while (pointer.hasNext()) {
            pointer.nextChar();

            if (pointer.c == '"') {
                StringBuilder value = new StringBuilder();
                pointer.nextChar();
                while (pointer.c != '"') {
                    value.append(pointer.c);
                    pointer.nextChar();
                }
                tokens.add( new JsonString(value.toString()) );
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
                tokens.add( new JsonNumber(value.toString()) );
            }
            else if (pointer.c == '{') {
                tokens.add(new OpenBrace());
            }
            else if (pointer.c == '}') {
                tokens.add(new CloseBrace());
            }
            else if (pointer.c == '[') {
                tokens.add(new OpenBracket());
            }
            else if (pointer.c == ']') {
                tokens.add(new CloseBracket());
            }
            else if (pointer.c == ',') {
                tokens.add(new Comma());
            }
            else if (pointer.c == ':') {
                tokens.add(new Colon());
            }
        }

        return tokens;
    }
}
