package com.khangse616.serverfashionrs.models.dto;

import org.springframework.web.multipart.MultipartFile;

public class InputUserUpdateDTO {
    private int id;
    private String name;
    private String birthday;
    private String email;
    private String sex;
    private String phoneNumber;
    private MultipartFile image;

    public InputUserUpdateDTO() {
    }

    public InputUserUpdateDTO(int id, String name, String birthday, String email, String sex, String phoneNumber, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.image = image;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
