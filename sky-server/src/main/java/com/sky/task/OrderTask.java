package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 定时任务类，定时处理订单状态
 * @Author: Zhiyong Wang
 * @Date: 2024/2/5 13:29
 */
@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超时订单的方法
     */
    @Scheduled(cron = "0 * * * * ?") //每分钟触发一次  0/5 * * * * ?
    public void processTimeoutOrder() {
        log.info("定时处理超时订单：{}", LocalDateTime.now());
        //查询超时订单
        LocalDateTime timeoutStamp = LocalDateTime.now().minusMinutes(15);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, timeoutStamp);
        if(ordersList != null && ordersList.size() > 0) {
            //修改并更新订单状态
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                //orderMapper.update(orders);
            }
            orderMapper.updateBatch(ordersList);
        }
    }

    /**
     * 处理一直处于派送中订单的方法
     */
    @Scheduled(cron = "0 0 1 * * ?")   //每天凌晨1点触发一次
    public void processDeliveryOrder() {
        log.info("定时处理一直处于派送中订单：{}", LocalDateTime.now());
        //查询一直处于派送中订单
        LocalDateTime timeoutStamp = LocalDateTime.now().minusHours(1);
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, timeoutStamp);
        if(ordersList != null && ordersList.size() > 0) {
            //修改并更新订单状态
            for (Orders orders : ordersList) {
                orders.setStatus(Orders.COMPLETED);
                orders.setDeliveryTime(LocalDateTime.now());
                //orderMapper.update(orders);
            }
            orderMapper.updateBatch(ordersList);
        }
    }

}
