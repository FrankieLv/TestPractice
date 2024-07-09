package com.frankie;

import java.util.List;

public interface IUserService {

    UserVO selectById(Long userId);

    void add(String username, String phone, List<String> feature);
}
