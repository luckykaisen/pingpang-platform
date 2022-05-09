package com.kc.pingpang.platform.freamwork.utils;


import com.kc.pingpang.platform.freamwork.session.model.AccountCO;

import javax.servlet.http.HttpSession;

public class AccountSessionUtility {

    private static final String ACCOUNT_CO_KEY = "ACCOUNT_CO_KEY";

    public static void setAccountCO(HttpSession session, AccountCO accountCO) {
        if (session != null) {
            session.setAttribute(ACCOUNT_CO_KEY, accountCO);
        }
    }

    public static AccountCO getAccountCO(HttpSession session) {

        if (session != null) {
            return (AccountCO)session.getAttribute(ACCOUNT_CO_KEY);
        }

        return null;
    }
}
