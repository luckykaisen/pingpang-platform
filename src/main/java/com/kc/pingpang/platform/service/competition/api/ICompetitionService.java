package com.kc.pingpang.platform.service.competition.api;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.freamwork.db.filter.SearchResult;

public interface ICompetitionService {

    SearchResult<Competition> searchCompetitionByFilter(CompetitionFilter filter);
}
