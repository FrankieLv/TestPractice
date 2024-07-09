package com.frankie;

import java.util.List;

public class UserVO {
    private String userName;
    private String Phone;
    List<String> featureValues;

    public List<String> getFeatureValues() {
        return featureValues;
    }

    public void setFeatureValues(List<String> featureValues) {
        this.featureValues = featureValues;
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
