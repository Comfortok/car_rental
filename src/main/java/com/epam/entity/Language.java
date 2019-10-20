package com.epam.entity;

import java.util.HashMap;

public class Language extends Entity {
    private static HashMap<String, String> languageIdToLocale = new HashMap<>();
    private String name;

    static {
        languageIdToLocale.put("1", "en");
        languageIdToLocale.put("2", "ru");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static HashMap<String, String> getLanguageIdToLocale() {
        return languageIdToLocale;
    }

    public static void setLanguageIdToLocale(HashMap<String, String> languageIdToLocale) {
        Language.languageIdToLocale = languageIdToLocale;
    }
}