package com.example.sportotosuperlig.api_rest;

public enum URL {
    SERVER1("https://run.mocky.io/v3/");

    private String url;

    URL(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
