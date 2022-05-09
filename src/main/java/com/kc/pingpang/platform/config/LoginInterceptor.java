package com.kc.pingpang.platform.config;


import com.alibaba.fastjson.JSON;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.session.model.AccountCO;
import com.kc.pingpang.platform.freamwork.utils.AccountSessionUtility;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

public class LoginInterceptor implements HandlerInterceptor {

    private static final byte[] NOT_LOGIN_MESSAGE;

    static {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setResultCode(ServiceResponse.CODE_REQUIRE_LOGIN);
        serviceResponse.setResultMessage("未登录！");

        NOT_LOGIN_MESSAGE = JSON.toJSONString(serviceResponse).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        HttpSession session = request.getSession();

        AccountCO accountCO = AccountSessionUtility.getAccountCO(session);

        if (accountCO == null) {

            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(NOT_LOGIN_MESSAGE);

            return false;
        }

        return true;
    }

}
