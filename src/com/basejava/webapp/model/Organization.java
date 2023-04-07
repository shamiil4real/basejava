package com.basejava.webapp.model;

import java.util.List;

public class Organization {

    private final Link website;
    private final String title;
    private final List<Period> periods;

    public Organization(String title, String url, List<Period> periods) {
        this.website = new Link(title, url);
        this.title = title;
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "Organization{" +
                " title='" + title + '\'' +
                ", periods=" + periods +
                '}';
    }
}
