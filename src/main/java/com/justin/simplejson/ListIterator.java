package com.justin.simplejson;

import java.util.Iterator;
import java.util.List;

public class ListIterator<T> implements Iterator<T> {
    private final List<T> src;
    private final int size;
    private int pos;


    public ListIterator(List<T> list) {
        this.src = list;
        size = list.size();
    }


    public int getSize() {
        return size;
    }

    @Override
    public boolean hasNext() {
        return pos != size;
    }

    @Override
    public T next() {
        return src.get(pos++);
    }

    public T peek() {
        return src.get(pos);
    }

    // consume next value if it equals `expected` and return `true`, otherwise return `false`
    boolean consumeIfEquals(T expected) {
        if (peek() == expected) {
            next();
            return true;
        }
        return false;
    }
}
