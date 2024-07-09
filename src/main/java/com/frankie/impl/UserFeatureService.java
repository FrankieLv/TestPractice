package com.frankie.impl;

import com.frankie.UserFeatureDO;

import java.util.ArrayList;
import java.util.List;

public class UserFeatureService {
    public List<UserFeatureDO> selectByUserId(Long userId){
        List<UserFeatureDO> userFeatureDOList =  new ArrayList<UserFeatureDO>();
        UserFeatureDO userFeature = new UserFeatureDO(userId, "feature");
        userFeatureDOList.add(userFeature);
        return userFeatureDOList;
    }
}
