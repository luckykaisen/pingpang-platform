package com.kc.pingpang.platform.controller.admin.competition;

import com.kc.pingpang.platform.controller.admin.competition.api.*;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.mapper.PlayerMapper;
import com.kc.pingpang.platform.data.model.*;
import com.kc.pingpang.platform.freamwork.model.Bool;
import com.kc.pingpang.platform.freamwork.model.db.filter.SearchResult;
import com.kc.pingpang.platform.freamwork.http.api.api.DownloadServiceResponse;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;
import com.kc.pingpang.platform.business.service.business.excel.group.cycle.GroupCycleExcel;
import com.kc.pingpang.platform.business.service.competition.api.ICompetitionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController("AdminCompetitionController")
@RequestMapping("/services/rs/admin/competition")
public class CompetitionController {

    @Value("${system.storage.path}")
    private String storagePath;

    @Value("${server.host.url}")
    private String hostUrl;

    @Resource
    private ICompetitionService competitionService;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private PlayerMapper playerMapper;

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
        response.setSignUpOptionIds(competition.getSignUpOptionList().stream().map(CompetitionOption::getId).collect(Collectors.toList()));
        response.setPlayers(CompetitionPlayerVO.toVOs(competition.getCompetitionPlayers()));
        response.setGroups(CompetitionGroupVO.toVOs(competition.getGroups()));
        response.setSignUpPrice(competition.getSignUpPrice().toString());
        response.setDinnerPrice(competition.getDinnerPrice().toString());

        return response;
    }

    @RequestMapping("/create")
    public ServiceResponse createCompetition(@RequestBody CreateCompetitionRequest request) throws Exception {

        competitionMapper.insertCompetition(request.toCompetition());

        return new ServiceResponse();
    }

    @RequestMapping("/modify")
    public ServiceResponse modifyCompetition(@RequestBody ModifyCompetitionRequest request) throws Exception {

        competitionMapper.updateCompetition(request.toCompetition());

        return new ServiceResponse();
    }

    @RequestMapping("/player/join")
    public ServiceResponse joinCompetitionPlayer(@RequestBody JoinCompetitionRequest request) {

        ServiceResponse response = new ServiceResponse();

        Integer competitionId = request.getId();
        Integer playerId = request.getPlayerId();

        CompetitionPlayer competitionPlayer = competitionMapper.selectCompetitionPlayerByPlayerIdAndCompetitionId(playerId, competitionId);

        if (competitionPlayer != null) {
            response.setResultMessage("?????????????????????????????????????????????????????????");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        Competition competition = competitionMapper.selectCompetitionById(request.getId());
        Player player = playerMapper.selectPlayerById(playerId);

        competitionPlayer = new CompetitionPlayer();
        competitionPlayer.setPlayerName(player.getName());
        competitionPlayer.setPlayerId(player.getId());
        competitionPlayer.setCompetitionId(competition.getId());
        competitionPlayer.setDinner(Bool.fromValue(request.getDinner()));

        competitionMapper.insertCompetitionPlayer(competitionPlayer);

        return response;
    }

    @RequestMapping("/player/delete")
    public ServiceResponse deleteCompetitionPlayer(@RequestParam Integer id) {

        CompetitionPlayer competitionPlayer = competitionMapper.selectCompetitionPlayerById(id);

        competitionService.deleteCompetitionPlayer(competitionPlayer);

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

    @RequestMapping("/group/add")
    public ServiceResponse addCompetitionGroup(@RequestParam Integer id) {

        competitionService.addCompetitionGroup(id);

        return new ServiceResponse();
    }

    @RequestMapping("/group/modify")
    public ServiceResponse modifyCompetitionGroup(@RequestBody ModifyCompetitionGroupRequest request) {

        CompetitionGroup group = new CompetitionGroup();
        group.setId(request.getId());
        group.setName(request.getName());

        competitionMapper.updateCompetitionGroup(group);

        return new ServiceResponse();
    }

    @RequestMapping("/group/delete")
    public ServiceResponse deleteCompetitionGroup(@RequestParam Integer id) {

        competitionService.deleteCompetitionGroup(id);

        return new ServiceResponse();
    }

    @RequestMapping("/group/player/add")
    public ServiceResponse addCompetitionGroupPlayer(@RequestBody AddCompetitionGroupPlayerRequest request) {

        ServiceResponse response = new ServiceResponse();

        CompetitionGroupPlayer groupPlayer = competitionMapper.selectCompetitionGroupPlayerByKey(request.getPlayerId(), request.getGroupId());

        if (groupPlayer != null) {
            response.setResultMessage(groupPlayer.getPlayer().getName() + "???????????????????????????????????????");
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
    public ServiceResponse deleteCompetitionGroupPlayer(@RequestParam Integer id) {

        competitionMapper.deleteCompetitionGroupPlayer(id);

        return new ServiceResponse();
    }

    @RequestMapping("/group/against/excel/download")
    public ServiceResponse downloadGroupRoundRobinExcel(@RequestBody DownloadGroupAgainstExcelRequest request) throws Exception {

        String path = null;

        GroupAgainstType type = GroupAgainstType.fromId(request.getTypeId());
        if (GroupAgainstType.TYPE_1 == type) {
            path = competitionService.downloadGroupRoundRobinExcel(request.getId());
        } else if (GroupAgainstType.TYPE_2 == type) {
            Competition competition = competitionMapper.selectCompetitionById(request.getId());

            path = new GroupCycleExcel(storagePath, competition.getGroups()).generate();
        }

        return new DownloadServiceResponse(hostUrl + path);
    }
}
