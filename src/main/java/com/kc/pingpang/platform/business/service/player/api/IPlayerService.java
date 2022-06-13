package com.kc.pingpang.platform.business.service.player.api;

import com.kc.pingpang.platform.data.filter.PlayerFilter;
import com.kc.pingpang.platform.data.model.Player;
import com.kc.pingpang.platform.freamwork.model.db.filter.SearchResult;

public interface IPlayerService {

    SearchResult<Player> searchPlayerByFilter(PlayerFilter filter);

    void deletePlayer(Integer id);
}
