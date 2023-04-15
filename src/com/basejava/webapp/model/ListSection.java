package com.basejava.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private List<String> contents;

    public ListSection() {
    }

    public ListSection(String... contents) {
        this(Arrays.asList(contents));
    }

    public ListSection(List<String> contents) {
        this.contents = contents;
    }

    public List<String> getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return contents != null ? contents.hashCode() : 0;
    }

    @Override
    public String toString() {
        return contents.toString();
    }
}
