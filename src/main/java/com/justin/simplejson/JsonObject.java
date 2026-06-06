package com.justin.simplejson;

import java.util.HashMap;
import java.util.Map;

public final class JsonObject implements JsonElement {
    final Map<String, JsonElement> members = new HashMap<>();

    @Override
    public String toString() {
        return members.toString();
    }
}
