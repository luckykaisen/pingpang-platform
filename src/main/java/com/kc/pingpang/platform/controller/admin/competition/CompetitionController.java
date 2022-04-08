package com.kc.pingpang.platform.controller.admin.competition;

import com.kc.pingpang.platform.controller.admin.competition.api.*;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.data.model.CompetitionGroup;
import com.kc.pingpang.platform.data.model.CompetitionGroupPlayer;
import com.kc.pingpang.platform.data.model.Group;
import com.kc.pingpang.platform.freamwork.db.filter.SearchResult;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;
import com.kc.pingpang.platform.service.competition.api.ICompetitionService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController("AdminCompetitionController")
@RequestMapping("/services/rs/admin/competition")
public class CompetitionController {

    @Resource
    private ICompetitionService competitionService;

    @Resource
    private CompetitionMapper competitionMapper;

    @RequestMapping("/search")
    public ServiceResponse searchCompetition(@RequestBody SearchCompetitionRequest request) {

        SearchResult<Competition> result = competitionService.searchCompetitionByFilter(request.toFilter());

        List<CompetitionVO> vos = CompetitionVO.toVOs(result.getResult());

        SearchCompetitionResponse response = new SearchCompetitionResponse();
        response.setList(vos);
        response.setPagingResult(result.getPagingResult());

        return response;
    }

    @RequestMapping("/detail")
    public ServiceResponse getCompetitionDetail(@RequestParam Integer id) {

        Competition competition = competitionMapper.selectCompetitionById(id);

        GetCompetitionDetailResponse response = new GetCompetitionDetailResponse();
        response.setId(competition.getId());
        response.setName(competition.getName());
        response.setDescription(competition.getDescription());
        response.setParticipantLimit(competition.getParticipantLimit());
        response.setDate(DateTimeUtility.formatYYYYMMDD(competition.getDate()));
        response.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMM(competition.getCreateTime()));
        response.setUpdateTime(DateTimeUtility.formatYYYYMMDDHHMM(competition.getUpdateTime()));

        return response;
    }

    @RequestMapping("/modify")
    public ServiceResponse modifyCompetition(@RequestBody ModifyCompetitionRequest request) throws Exception {

        competitionMapper.updateCompetition(request.toCompetition());

        return new ServiceResponse();
    }

    @RequestMapping("/group/create")
    public ServiceResponse createCompetitionGroup(@RequestBody CreateCompetitionGroupRequest request) {

        List<CompetitionGroup> groups = new ArrayList<>();
        for (int i = 1; i <= request.getCount(); i++) {

            CompetitionGroup group = new CompetitionGroup();
            group.setCompetitionId(request.getId());
            group.setName(Group.fromId(i).getName());

            groups.add(group);
        }

        if (!CollectionUtils.isEmpty(groups)) {
            competitionMapper.batchInsertCompetitionGroup(groups);
        }

        return new ServiceResponse();
    }

    @RequestMapping("/group/player/add")
    public ServiceResponse addCompetitionGroupPlayer(@RequestBody AddCompetitionGroupPlayerRequest request) {

        ServiceResponse response = new ServiceResponse();

        CompetitionGroupPlayer groupPlayer = competitionMapper.selectCompetitionGroupPlayerByKey(request.getPlayerId(), request.getGroupId());

        if (groupPlayer != null) {
            response.setResultMessage(groupPlayer.getPlayer().getName() + "已在小组中，请刷新后重试！");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        groupPlayer = new CompetitionGroupPlayer();
        groupPlayer.setGroupId(request.getGroupId());
        groupPlayer.setPlayerId(request.getPlayerId());

        competitionMapper.insertCompetitionGroupPlayer(groupPlayer);

        return response;
    }


    @RequestMapping("/group/player/delete")
    public ServiceResponse deleteCompetitionGroupPlayer(@RequestBody Integer id) {

        competitionMapper.deleteCompetitionGroupPlayer(id);

        return new ServiceResponse();
    }

}
