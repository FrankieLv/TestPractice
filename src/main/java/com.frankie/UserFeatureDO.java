package com.frankie;

import com.frankie.impl.UserFeatureService;

public class UserFeatureDO {

    public UserFeatureDO(Long userId, String featureValue) {
        this.userId = userId;
        this.featureValue = featureValue;
    }

    private Long id;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }

    private Long userId;
    private String featureValue;
}
