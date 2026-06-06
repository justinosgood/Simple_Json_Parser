package com.justin.simplejson;

import java.util.ArrayList;
import java.util.List;

public final class JsonArray implements JsonElement {
    final List<JsonElement> elements = new ArrayList<>();

    @Override
    public String toString() {
        return elements.toString();
    }
}
