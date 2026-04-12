sealed interface Token {}

record JsonString(String value) implements Token {}
record JsonNumber(String value) implements Token {}
record JsonBoolean(boolean value) implements Token {}
record JsonNull() implements Token {}

record OpenBrace() implements Token {}
record CloseBrace() implements Token {}
record OpenBracket() implements Token {}
record CloseBracket() implements Token {}
record Comma() implements Token {}
record Colon() implements Token {}