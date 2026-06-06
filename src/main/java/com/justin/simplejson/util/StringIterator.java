package com.justin.simplejson.util;

import java.util.Iterator;

public class StringIterator implements Iterator<Character> {
    private final String src;
    private final int size;
    private int pos;


    public StringIterator(String src) {
        this.src = src;
        this.size = src.length();
    }


    @Override
    public boolean hasNext() {
        return pos != size;
    }

    @Override
    public Character next() {
        return src.charAt(pos++);
    }

    // peeks one char ahead
    public Character peek() {
        return src.charAt(pos);
    }

    public Character peekPrevious() {
        return src.charAt(pos - 2);
    }

    public void skip(int amount) {
        pos += amount;
    }
}
