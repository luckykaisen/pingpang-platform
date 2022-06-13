package com.kc.pingpang.platform.business.schedule;


import com.kc.pingpang.platform.data.mapper.OrderMapper;
import com.kc.pingpang.platform.data.model.Order;
import com.kc.pingpang.platform.data.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class Scheduler {

    @Resource
    private OrderMapper orderMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 0/5 * * * ?")
    public void autoCancelOrder() {
        logger.info("autoCancelOrder start...");

        int orderCount = 0;
        try {
            Date overdueTime = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(30));

            List<Order> orders = orderMapper.selectOvertimeWaitingPaymentOrder(overdueTime);

            for (Order order : orders) {

                Order updateOrder = new Order();
                updateOrder.setId(order.getId());
                updateOrder.setStatus(OrderStatus.CANCEL);
                updateOrder.setCancelTime(new Date());

                orderMapper.updateOrder(updateOrder);
            }

            orderCount = orders.size();
        } catch (Exception e) {
            logger.error("catch an exception: " + e);
        }

        logger.info("autoCancelOrder end, process count: " + orderCount);
    }

}
