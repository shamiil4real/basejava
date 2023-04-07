package com.basejava.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection {

    private final List<String> contents;

    public ListSection(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return contents.toString();
    }
}
