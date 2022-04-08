package com.kc.pingpang.platform.controller.competition;

import com.kc.pingpang.platform.controller.competition.api.CompetitionVO;
import com.kc.pingpang.platform.controller.competition.api.SearchCompetitionRequest;
import com.kc.pingpang.platform.controller.competition.api.SearchCompetitionResponse;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.freamwork.db.filter.SearchResult;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.service.competition.api.ICompetitionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/services/rs/competition")
public class CompetitionController {

    @Resource
    private ICompetitionService competitionService;

    @RequestMapping("/search")
    public ServiceResponse searchCompetition(@RequestBody SearchCompetitionRequest request) {

        SearchResult<Competition> result = competitionService.searchCompetitionByFilter(request.toFilter());

        List<CompetitionVO> vos = CompetitionVO.toVOs(result.getResult());

        SearchCompetitionResponse response = new SearchCompetitionResponse();
        response.setList(vos);
        response.setPagingResult(result.getPagingResult());

        return response;
    }

}
