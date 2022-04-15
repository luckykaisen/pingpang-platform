package com.kc.pingpang.platform.data.mapper;

import com.kc.pingpang.platform.data.filter.PlayerFilter;
import com.kc.pingpang.platform.data.model.Player;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayerMapper {

    Player selectPlayerById(Integer id);

    Player selectPlayerByName(String name);

    void insertPlayer(Player player);

    List<Player> selectPlayerByFilter(@Param("filter") PlayerFilter filter);

    int countPlayerByFilter(@Param("filter") PlayerFilter filter);

    void updatePlayer(Player player);

    void deletePlayer(Integer id);
}
