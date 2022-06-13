package com.kc.pingpang.platform.controller.console.order;

import com.alibaba.fastjson.JSON;
import com.kc.pingpang.platform.business.adapter.HuPiJiaoAdapter;
import com.kc.pingpang.platform.business.adapter.api.OrderNotifyRequest;
import com.kc.pingpang.platform.business.adapter.api.PayResponse;
import com.kc.pingpang.platform.business.adapter.utils.HuPiJiaoUtility;
import com.kc.pingpang.platform.business.service.order.api.IOrderService;
import com.kc.pingpang.platform.business.util.SnowFlakeUtil;
import com.kc.pingpang.platform.controller.console.order.api.CreateOrderRequest;
import com.kc.pingpang.platform.controller.console.order.api.CreateOrderResponse;
import com.kc.pingpang.platform.data.filter.OrderFilter;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.mapper.OrderMapper;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.data.model.CompetitionPlayer;
import com.kc.pingpang.platform.data.model.Order;
import com.kc.pingpang.platform.data.model.OrderStatus;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.model.Bool;
import org.apache.commons.io.IOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@CrossOrigin
@RestController("ConsoleOrderController")
@RequestMapping("/services/rs/console/order")
public class OrderController {

    @Value("${hupijiao.pay.secret}")
    private String secret;

    @Resource
    private HuPiJiaoAdapter huPiJiaoAdapter;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private IOrderService orderService;

    @RequestMapping("/create")
    public ServiceResponse createOrder(@RequestBody CreateOrderRequest request) {

        CreateOrderResponse response = new CreateOrderResponse();

        Integer competitionId = request.getId();
        String playerName = StringUtils.trim(request.getName());

        CompetitionPlayer competitionPlayer = competitionMapper.selectCompetitionPlayerByPlayerNameAndCompetitionId(playerName, competitionId);

        if (competitionPlayer != null) {
            response.setResultMessage("您已成功报名了该场比赛，无需重复提交！");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        Competition competition = competitionMapper.selectCompetitionById(request.getId());

        OrderFilter orderFilter = new OrderFilter();
        orderFilter.setCompetitionId(competitionId);
        orderFilter.setStatuses(Arrays.asList(OrderStatus.PENDING_PAYMENT, OrderStatus.PAID));

        int count = orderMapper.countOrderByFilter(orderFilter);

        if (competition.getParticipantLimit().compareTo(count) <= 0) {
            response.setResultMessage("名额已满，谢谢参与！");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        Bool dinner = Bool.fromValue(request.getDinner());

        Order order = orderMapper.selectWaitingPaymentOrderByPlayerNameAndCompetitionId(playerName, competitionId);
        if (order != null) {
            if (dinner == order.getDinner()) {
                response.setUrl(order.getPayUrl());

                return response;
            } else {
                order.setStatus(OrderStatus.CANCEL);
                order.setCancelTime(new Date());

                orderMapper.updateOrder(order);
            }
        }

        BigDecimal dinnerPrice = competition.getDinnerPrice();
        BigDecimal signUpPrice = competition.getSignUpPrice();

        order = new Order();
        order.setOrderNumber(String.valueOf(SnowFlakeUtil.getId()));
        order.setTitle(playerName + "报名费");
        order.setPlayerName(playerName);
        order.setDinner(dinner);
        order.setGoodsUrl("https://www.sysm.ltd/#/console/competition/join/" + competitionId);
        order.setCompetitionId(competitionId);
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        if (Bool.Y == dinner) {
            order.setAmount(dinnerPrice.add(signUpPrice));
        } else {
            order.setAmount(signUpPrice);
        }

        if (order.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            PayResponse pay = huPiJiaoAdapter.pay(order.getOrderNumber(), order.getTitle(), order.getAmount(), order.getGoodsUrl());
            if (pay.isSuccess()) {
                order.setPayUrl(pay.getUrl());
                order.setQrcodeUrl(pay.getUrlQrcode());
            } else {
                response.setResultMessage("系统内部错误，请联系管理员！");
                response.setResultCode(ServiceResponse.CODE_FAILED);

                return response;
            }
        }

        orderMapper.insertOrder(order);

        response.setUrl(order.getPayUrl());

        return response;
    }

    @RequestMapping("/notify")
    public String notifyOrder(HttpServletRequest request) throws Exception {

        String content = IOUtil.toString(request.getInputStream(), "utf-8");

        System.out.println("content: " + content);

        boolean verify = HuPiJiaoUtility.verifySign(content, secret);

        if (verify) {

            OrderNotifyRequest notifyRequest = HuPiJiaoUtility.parseNotifyRequest(content);

            Order order = orderMapper.selectOrderByOrderNumber(notifyRequest.getTradeOrderId());
            order.setPayTime(new Date());
            order.setStatus(OrderStatus.PAID);

            orderService.orderNotify(order);

            System.out.println("json: " + JSON.toJSONString(notifyRequest));

            return "success";
        }

        return "fail";
    }


}
