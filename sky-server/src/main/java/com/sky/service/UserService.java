package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @Description
 * @Author: Zhiyong Wang
 * @Date: 2024/1/31 13:40
 */
public interface UserService {

    /**
     * 用户微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);

}
