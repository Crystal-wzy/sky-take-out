package com.sky.service;

import com.sky.dto.DishDTO;

/**
 * @Description
 * @Author: Zhiyong Wang
 * @Date: 2024/1/28 22:01
 */
public interface DishService {

    /**
     * 新增菜品和对应的口味数据
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

}
