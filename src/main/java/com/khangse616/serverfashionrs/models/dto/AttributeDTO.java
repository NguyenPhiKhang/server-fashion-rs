package com.khangse616.serverfashionrs.models.dto;

import com.khangse616.serverfashionrs.models.Attribute;

import java.util.List;

public class AttributeDTO<T> {
    private int id;
    private String type;
    private String label;
    private String code;
    private List<T> options;

    public AttributeDTO(){

    }

    public AttributeDTO(int id, String type, String label, String code) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getOptions() {
        return options;
    }

    public void setOptions(List<T> options) {
        this.options = options;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
