package com.kc.pingpang.platform.data.mapper;

import com.kc.pingpang.platform.data.filter.OrderFilter;
import com.kc.pingpang.platform.data.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {

    Order selectOrderByOrderNumber(@Param("orderNumber") String orderNumber);

    void insertOrder(Order order);

    int countOrderByFilter(@Param("filter") OrderFilter filter);

    Order selectWaitingPaymentOrderByPlayerNameAndCompetitionId(@Param("playerName") String playerName, @Param("competitionId") Integer competitionId);

    void updateOrder(Order order);

    List<Order> selectOvertimeWaitingPaymentOrder(Date overdueTime);
}