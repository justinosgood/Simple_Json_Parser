package com.justin.simplejson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JsonType {
    sealed interface JsonElement {}

    final class JsonObject implements JsonElement {
        private final Map<String, JsonElement> members = new HashMap<>();

        void put(String key, JsonElement value) { members.put(key, value);}

        @Override
        public String toString() {
            return members.toString();
        }
    }

    final class JsonArray implements JsonElement {
        private final List<JsonElement> elements = new ArrayList<>();

        void add(JsonElement value) { elements.add(value); }

        @Override
        public String toString() {
            return elements.toString();
        }
    }

    // JSON primitives
    record JsonString(String value)                      implements JsonElement {}
    record JsonNumber(Number value)                      implements JsonElement {}
    record JsonBoolean(boolean value)                    implements JsonElement {}

    enum JsonNull implements JsonElement { NULL }

}
