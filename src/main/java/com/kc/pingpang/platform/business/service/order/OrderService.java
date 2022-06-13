package com.kc.pingpang.platform.business.service.order;

import com.kc.pingpang.platform.business.service.competition.api.ICompetitionService;
import com.kc.pingpang.platform.business.service.order.api.IOrderService;
import com.kc.pingpang.platform.data.mapper.OrderMapper;
import com.kc.pingpang.platform.data.model.CompetitionPlayer;
import com.kc.pingpang.platform.data.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService implements IOrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ICompetitionService competitionService;

    @Override
    public void orderNotify(Order order) {

        orderMapper.updateOrder(order);

        CompetitionPlayer player = new CompetitionPlayer();
        player.setPlayerName(order.getPlayerName());
        player.setDinner(order.getDinner());
        player.setCompetitionId(order.getCompetitionId());

        competitionService.joinCompetition(player);
    }
}
