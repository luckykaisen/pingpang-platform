package com.kc.pingpang.platform.controller.console.competition;

import com.kc.pingpang.platform.controller.console.competition.api.GetCompetitionDetailResponse;
import com.kc.pingpang.platform.controller.console.competition.api.JoinCompetitionRequest;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.data.model.CompetitionPlayer;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.model.Bool;
import com.kc.pingpang.platform.business.service.competition.api.ICompetitionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController("ConsoleCompetitionController")
@RequestMapping("/services/rs/console/competition")
public class CompetitionController {

    @Resource
    private ICompetitionService competitionService;

    @Resource
    private CompetitionMapper competitionMapper;

    @RequestMapping("/join")
    public ServiceResponse joinCompetition(@RequestBody JoinCompetitionRequest request) {

        ServiceResponse response = new ServiceResponse();

        Integer competitionId = request.getId();
        String playerName = StringUtils.trim(request.getName());

        CompetitionPlayer competitionPlayer = competitionMapper.selectCompetitionPlayerByPlayerNameAndCompetitionId(playerName, competitionId);

        if (competitionPlayer != null) {
            response.setResultMessage("您已成功报名了该场比赛，无需重复提交！");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        Competition competition = competitionMapper.selectCompetitionById(request.getId());
        List<CompetitionPlayer> competitionPlayers = competitionMapper.selectCompetitionPlayerByCompetitionId(competitionId);

        if (competition.getParticipantLimit().compareTo(competitionPlayers.size()) <= 0) {
            response.setResultMessage("名额已满，谢谢参与！");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        CompetitionPlayer player = new CompetitionPlayer();
        player.setPlayerName(playerName);
        player.setDinner(Bool.fromValue(request.getDinner()));
        player.setCompetitionId(competitionId);

        competitionService.joinCompetition(player);

        return response;
    }

    @RequestMapping("/detail")
    public ServiceResponse getCompetitionDetail(@RequestParam Integer id) {

        Competition competition = competitionMapper.selectCompetitionById(id);

        return new GetCompetitionDetailResponse(competition);
    }
}
