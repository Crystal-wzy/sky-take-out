package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author: Zhiyong Wang
 * @Date: 2024/1/28 22:19
 */
@Mapper
public interface DishFlavorMapper {


    /**
     * 批量插入菜品口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);
}
