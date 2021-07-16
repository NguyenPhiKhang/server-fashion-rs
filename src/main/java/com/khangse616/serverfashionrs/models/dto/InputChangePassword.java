package com.khangse616.serverfashionrs.models.dto;

public class InputChangePassword {
    private String newPassword;
    private String oldPassword;

    public InputChangePassword() {
    }

    public InputChangePassword(String newPassword, String oldPassword) {
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
