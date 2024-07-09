package com.frankie.impl;

import com.frankie.UserDO;
import com.frankie.UserFeatureDO;
import com.frankie.UserServiceMapper;
import com.frankie.UserVO;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService{

    private UserFeatureService userFeatureService;

    private UserServiceMapper userServiceMapper;

    public UserVO selectById(Long userId) throws InvocationTargetException, IllegalAccessException {
        UserDO existedEntity = getById(userId);
        if (existedEntity == null){
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setUserName(existedEntity.getUserName());
        userVO.setPhone(existedEntity.getPhone());

        List<UserFeatureDO> features = userFeatureService.selectByUserId(userId);
        if (features == null){
            return userVO;
        }

        List<String> featureValues = features.stream().map(UserFeatureDO::getFeatureValue).collect(Collectors.toList());
        userVO.setFeatureValues(featureValues);
        return userVO;

    }

    UserDO getById(Long userId){
        return userServiceMapper.getById(userId);
    }

    public int modifyUser(UserVO userVO){
        return 123;
    }

    public List<UserFeatureDO> getUserFeatures(Long userId){
        return userFeatureService.selectByUserId(userId);
    }
}