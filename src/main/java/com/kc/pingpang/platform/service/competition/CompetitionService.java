package com.kc.pingpang.platform.service.competition;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.mapper.PlayerMapper;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.data.model.CompetitionPlayer;
import com.kc.pingpang.platform.data.model.Player;
import com.kc.pingpang.platform.freamwork.db.filter.PagingData;
import com.kc.pingpang.platform.freamwork.db.filter.PagingResult;
import com.kc.pingpang.platform.freamwork.db.filter.SearchResult;
import com.kc.pingpang.platform.service.competition.api.ICompetitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompetitionService implements ICompetitionService {

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private PlayerMapper playerMapper;

    @Override
    public SearchResult<Competition> searchCompetitionByFilter(CompetitionFilter filter) {
        SearchResult<Competition> result = new SearchResult<>();

        List<Competition> list = competitionMapper.selectCompetitionByFilter(filter);
        result.setResult(list);

        PagingData pagingData = filter.getPagingData();
        if (filter.isPaged() && pagingData != null) {
            int recordNumber = competitionMapper.countCompetitionByFilter(filter);

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
    public void joinCompetition(String playerName, Competition competition) {

        Player player = playerMapper.selectPlayerByName(playerName);

        if (player == null) {
            player = new Player();
            player.setName(playerName);

            playerMapper.insertPlayer(player);
        }

        CompetitionPlayer competitionPlayer = new CompetitionPlayer();
        competitionPlayer.setPlayerName(player.getName());
        competitionPlayer.setPlayerId(player.getId());
        competitionPlayer.setCompetitionId(competition.getId());

        competitionMapper.insertCompetitionPlayer(competitionPlayer);
    }
}
