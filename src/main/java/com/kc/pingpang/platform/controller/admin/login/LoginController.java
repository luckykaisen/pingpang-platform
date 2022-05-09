package com.kc.pingpang.platform.controller.admin.login;

import com.kc.pingpang.platform.controller.admin.login.api.GetLoginInfoResponse;
import com.kc.pingpang.platform.controller.admin.login.api.LoginAccountVO;
import com.kc.pingpang.platform.controller.admin.login.api.LoginRequest;
import com.kc.pingpang.platform.data.mapper.AdminAccountMapper;
import com.kc.pingpang.platform.data.model.AdminAccount;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.session.model.AccountCO;
import com.kc.pingpang.platform.freamwork.utils.AccountSessionUtility;
import com.kc.pingpang.platform.freamwork.utils.PasswordUtility;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController("LoginController")
@RequestMapping("/services/rs/admin/account")
public class LoginController {

    @Resource
    private AdminAccountMapper adminAccountMapper;

    @RequestMapping("/login")
    public ServiceResponse login(@RequestBody LoginRequest request, HttpSession session) {

        GetLoginInfoResponse response = new GetLoginInfoResponse();

        if (!request.validate()) {
            response.fillWithFailedCode();
            response.setResultMessage("参数错误！");

            return response;
        }

        AdminAccount account = adminAccountMapper.selectAdminAccountByLoginName(request.getLoginName());
        if (account == null) {
            response.fillWithFailedCode();
            response.setResultMessage("账号不存在！");

            return response;
        }

        if (!PasswordUtility.isEquals(request.getPassword(), account.getSalt(), account.getPassword())) {
            response.fillWithFailedCode();
            response.setResultMessage("密码错误！");

            return response;
        }

        response.setAccount(LoginAccountVO.toAccountVO(account));

        onAccountLogin(account, session);

        return response;
    }

    @RequestMapping("/logout")
    public ServiceResponse logout(HttpSession session) {

        AccountSessionUtility.setAccountCO(session, null);

        return new ServiceResponse();
    }

    @RequestMapping("/info")
    public ServiceResponse info(HttpSession session) {

        AccountCO accountCO = AccountSessionUtility.getAccountCO(session);

        GetLoginInfoResponse response = new GetLoginInfoResponse();

        if (accountCO != null && accountCO.getId() != null) {

            AdminAccount account = adminAccountMapper.selectAdminAccountById(Integer.valueOf(accountCO.getId()));

            response.setAccount(LoginAccountVO.toAccountVO(account));
        }

        return response;
    }

    private void onAccountLogin(AdminAccount account, HttpSession session) {
        // session
        AccountCO accountCO = new AccountCO();

        accountCO.setId(String.valueOf(account.getId()));
        accountCO.setName(account.getLoginName());

        AccountSessionUtility.setAccountCO(session, accountCO);
    }

}
