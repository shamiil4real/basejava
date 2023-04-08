package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link website;
    private final String title;
    private final List<Period> periods;

    public Organization(String title, String url, List<Period> periods) {
        this.website = new Link(title, url);
        this.title = title;
        this.periods = periods;
    }

    public Link getWebsite() {
        return website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!website.equals(that.website)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        int result = website.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (periods != null ? periods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                " title='" + title + '\'' +
                ", periods=" + periods +
                '}';
    }
}
