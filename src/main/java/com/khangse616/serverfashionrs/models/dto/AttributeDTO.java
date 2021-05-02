package com.khangse616.serverfashionrs.models.dto;

import java.util.List;

public class AttributeDTO<T> {
    private int id;
    private String name;
    private List<T> options;

    public AttributeDTO(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<T> getOptions() {
        return options;
    }

    public void setOptions(List<T> options) {
        this.options = options;
    }
}
