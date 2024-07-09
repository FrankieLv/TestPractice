package com.frankie;

public class UserDO {
    private String userName;
    private String Phone;

    public UserDO(String userName, String phone) {
        this.userName = userName;
        Phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
