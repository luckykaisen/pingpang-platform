package com.kc.pingpang.platform.service.competition.api;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.data.model.CompetitionPlayer;
import com.kc.pingpang.platform.freamwork.model.db.filter.SearchResult;

public interface ICompetitionService {

    SearchResult<Competition> searchCompetitionByFilter(CompetitionFilter filter);

    void joinCompetition(CompetitionPlayer player, Competition competition);

    void deleteCompetitionPlayer(CompetitionPlayer competitionPlayer);

    void deleteCompetitionGroup(Integer id);

    void addCompetitionGroup(Integer id);

    String downloadGroupRoundRobinExcel(Integer id) throws Exception;
}
