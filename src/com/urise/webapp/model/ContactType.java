package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Тел."),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKED_IN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }


}
