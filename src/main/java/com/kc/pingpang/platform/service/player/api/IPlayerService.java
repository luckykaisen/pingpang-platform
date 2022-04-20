package com.kc.pingpang.platform.service.player.api;

import com.kc.pingpang.platform.data.filter.PlayerFilter;
import com.kc.pingpang.platform.data.model.Player;
import com.kc.pingpang.platform.freamwork.db.filter.SearchResult;

public interface IPlayerService {

    SearchResult<Player> searchPlayerByFilter(PlayerFilter filter);

    void deletePlayer(Integer id);
}