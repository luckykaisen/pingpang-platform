package com.kc.pingpang.platform.business.service.player;

import com.kc.pingpang.platform.business.service.player.api.IPlayerService;
import com.kc.pingpang.platform.data.filter.PlayerFilter;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.mapper.PlayerMapper;
import com.kc.pingpang.platform.data.model.Player;
import com.kc.pingpang.platform.freamwork.model.db.filter.PagingData;
import com.kc.pingpang.platform.freamwork.model.db.filter.PagingResult;
import com.kc.pingpang.platform.freamwork.model.db.filter.SearchResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    @Resource
    private PlayerMapper playerMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Override
    public SearchResult<Player> searchPlayerByFilter(PlayerFilter filter) {
        SearchResult<Player> result = new SearchResult<>();

        List<Player> list = playerMapper.selectPlayerByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = playerMapper.countPlayerByFilter(filter);

            PagingResult pagingResult = new PagingResult();

            pagingResult.setRecordNumber(recordNumber);
            pagingResult.setPageSize(pagingData.getPageSize());
            pagingResult.setPageNumber(pagingData.getPageNumber());

            result.setPaged(true);
            result.setPagingResult(pagingResult);
        }

        return result;
    }

    @Override
    @Transactional
    public void deletePlayer(Integer id) {
        competitionMapper.deleteCompetitionPlayerByPlayerId(id);
        competitionMapper.deleteCompetitionGroupPlayerByPlayerId(id);
        playerMapper.deletePlayer(id);
    }
}
