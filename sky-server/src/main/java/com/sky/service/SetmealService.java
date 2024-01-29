package com.sky.service;

import com.sky.dto.SetmealDTO;

/**
 * @Description
 * @Author: Zhiyong Wang
 * @Date: 2024/1/29 19:13
 */
public interface SetmealService {

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);
}
