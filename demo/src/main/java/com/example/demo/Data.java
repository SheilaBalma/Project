package com.example.demo;

public class Data {
    String key;
    String name;
    String description;
    Integer values;

    public Data(String key, String name, String description, Integer values) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValues() {
        return values;
    }

    public void setValues(Integer values) {
        this.values = values;
    }


// filtro como un query param
    // path param
    // header
}
