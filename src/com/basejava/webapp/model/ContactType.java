package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Phone: "),
    SKYPE("Skype: "),
    MAIL("Почта: ");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
