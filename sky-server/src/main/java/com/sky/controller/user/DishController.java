package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "C端菜品浏览接口")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        List<DishVO> list = null;
        //构造Redis中的key，规则：dish_categoryId
        String key = "dish_" + categoryId;
        //查询Redis中是否存在菜品数据
        list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if(list != null && list.size() > 0) {
            //如果存在，直接返回，无需查询数据库
            log.info("使用Redis缓存数据");
            return Result.success(list);
        }
        //如果不存在，查询数据库
        log.info("根据分类id查询菜品：{}", categoryId);
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);//查询起售中的菜品
        list = dishService.listWithFlavor(dish);
        //将查询的数据放入Redis中
        redisTemplate.opsForValue().set(key, list, 24, TimeUnit.HOURS);
        return Result.success(list);
    }

}
