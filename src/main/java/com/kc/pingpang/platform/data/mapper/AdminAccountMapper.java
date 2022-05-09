package com.kc.pingpang.platform.data.mapper;

import com.kc.pingpang.platform.data.model.AdminAccount;

public interface AdminAccountMapper {

    AdminAccount selectAdminAccountByLoginName(String loginName);

    AdminAccount selectAdminAccountById(Integer id);
}