package com.kc.pingpang.platform.data.mapper;

import com.kc.pingpang.platform.data.model.Player;

public interface PlayerMapper {

    Player selectPlayerById(Integer id);
}
