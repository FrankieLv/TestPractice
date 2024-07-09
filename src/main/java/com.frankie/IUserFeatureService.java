package com.frankie;

import java.util.List;

public interface IUserFeatureService {
    public List<UserFeatureDO> selectByUserId(Long userId);
}
