package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

/**
 * @Description
 * @Author: Zhiyong Wang
 * @Date: 2024/2/1 15:20
 */
public interface ShoppingCartService {

    /**
     * 添加购物车
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

}
