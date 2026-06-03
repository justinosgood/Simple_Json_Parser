package com.justin.simplejson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface JsonElement {
    final class JsonObject implements JsonType.JsonElement {
        private final Map<String, JsonType.JsonElement> members = new HashMap<>();

        void put(String key, JsonType.JsonElement value) { members.put(key, value);}

        @Override
        public String toString() {
            return members.toString();
        }
    }

    final class JsonArray implements JsonType.JsonElement {
        private final List<JsonType.JsonElement> elements = new ArrayList<>();

        void add(JsonType.JsonElement value) { elements.add(value); }

        @Override
        public String toString() {
            return elements.toString();
        }
    }

    // JSON primitives
    record JsonString(String value)                      implements JsonType.JsonElement {}
    record JsonNumber(Number value)                      implements JsonType.JsonElement {}
    record JsonBoolean(boolean value)                    implements JsonType.JsonElement {}

    enum JsonNull implements JsonType.JsonElement { NULL }
}
