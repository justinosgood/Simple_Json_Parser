public record Token(TokenType type, String value) {
    public static final Token OPEN_BRACE = new Token(TokenType.OPEN_BRACE, "{");
    public static final Token CLOSE_BRACE = new Token(TokenType.CLOSE_BRACE, "}");
    public static final Token OPEN_BRACKET = new Token(TokenType.OPEN_BRACKET, "[");
    public static final Token CLOSE_BRACKET = new Token(TokenType.CLOSE_BRACKET, "]");
    public static final Token COMMA = new Token(TokenType.COMMA, ",");
    public static final Token COLON = new Token(TokenType.COLON, ":");
}
