package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author: Zhiyong Wang
 * @Date: 2024/1/29 0:26
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询关联的套餐数量
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal_dish where dish_id = #{id}")
    Integer countByDishId(Long id);

    /**
     * 批量插入套餐关联的菜品数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
