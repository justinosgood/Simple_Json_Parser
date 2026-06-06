package com.justin.simplejson;

public sealed interface JsonElement permits
        JsonObject, JsonArray, JsonString, JsonNumber, JsonBoolean, JsonNull {

    // access methods to navigate JSON tree
    default JsonElement get(String key) {
        if (this instanceof JsonObject jsonObject) return jsonObject.members.get(key);
        throw new IllegalStateException(this.getClass() + " is not a JsonObject!");
    }

    default JsonElement get(int index) {
        if (this instanceof JsonArray jsonArray) return jsonArray.elements.get(index);
        throw new IllegalStateException(this.getClass() + " is not a JsonArray!");
    }


    // terminal operations to convert values
    default String asString() {
        if (this instanceof JsonString(String string)) return string;
        throw new IllegalStateException(this.getClass() + " cannot be cast to String!");
    }

    default boolean asBoolean() {
        if (this instanceof JsonBoolean(boolean bool)) return bool;
        throw new IllegalStateException(this.getClass() + " cannot be cast to Boolean!");
    }

    default long asLong() {
        if (asNumber() instanceof Long l) return l;
        throw new IllegalStateException(this.getClass() + " cannot be cast to Long!");
    }

    default double asDouble() {
        if (asNumber() instanceof Double d) return d;
        throw new IllegalStateException(this.getClass() + " cannot be cast to Double!");
    }

    private Number asNumber() {
        if (this instanceof JsonNumber(Number number)) return number;
        throw new IllegalStateException(this.getClass() + " is not a Number!");
    }


    default boolean isNull() {
        return this instanceof JsonNull;
    }
}