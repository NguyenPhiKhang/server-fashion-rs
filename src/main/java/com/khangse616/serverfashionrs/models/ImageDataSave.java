package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "data_images_save")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageDataSave implements Serializable {
    @Id
    @JsonIgnore
    private String id;
    @Column(name = "name")
    @JsonIgnore
    private String name;
    @Column(name = "type")
    @JsonIgnore
    private String type;
    @Lob
    @Column(name = "data")
    @JsonIgnore
    private byte[] data;

    @Column(name="link")
    private String link;

    public ImageDataSave(){}

    public ImageDataSave(String id, String name, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public ImageDataSave(String id, String name, String type, String link) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}

