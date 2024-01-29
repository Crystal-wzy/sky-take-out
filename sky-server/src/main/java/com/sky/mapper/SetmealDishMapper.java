package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
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
     * @param dishId
     * @return
     */
    @Select("select count(id) from setmeal_dish where dish_id = #{dishId}")
    Integer countByDishId(Long dishId);

    /**
     * 批量插入套餐关联的菜品数据
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据菜品id删除对应的口味数据
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);

    /**
     * 根据套餐id集合批量删除套餐关联的菜品数据
     * @param setmealIds
     */
    void deleteBySetmealIds(List<Long> setmealIds);

    /**
     * 根据套餐id查询关联的菜品数据
     * @param setmealId
     * @return
     */
    @Select("select * from setmeal_dish  where setmeal_id = #{setmealId}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
