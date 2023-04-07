package com.basejava.webapp.model;

public class Link {

    private final String name;
    private final String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name + "(" + url + ")";
    }
}
