package com.kc.pingpang.platform.controller.admin.resource;

import com.kc.pingpang.platform.controller.admin.resource.api.DefaultResourceResponse;
import com.kc.pingpang.platform.controller.admin.resource.api.DefaultResourceVO;
import com.kc.pingpang.platform.data.mapper.CompetitionMapper;
import com.kc.pingpang.platform.data.model.Player;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController("AdminResourceController")
@RequestMapping("/services/rs/admin/resource")
public class ResourceController {

    @Resource
    private CompetitionMapper competitionMapper;

    @RequestMapping("/not/competition/players")
    public ServiceResponse getNotCompetitionPlayers(@RequestParam Integer competitionId) {

        List<Player> players = competitionMapper.selectNotCompetitionPlayers(competitionId);

        List<DefaultResourceVO> vos = new ArrayList<>();
        for (Player player : players) {
            DefaultResourceVO vo = new DefaultResourceVO();
            vo.setId(player.getId());
            vo.setName(player.getName());

            vos.add(vo);
        }

        DefaultResourceResponse response = new DefaultResourceResponse();
        response.setList(vos);

        return response;
    }

    @RequestMapping("/not/group/players")
    public ServiceResponse getNotGroupedPlayers(@RequestParam Integer competitionId) {

        List<Player> players = competitionMapper.selectNotGroupPlayers(competitionId);

        List<DefaultResourceVO> vos = new ArrayList<>();
        for (Player player : players) {
            DefaultResourceVO vo = new DefaultResourceVO();
            vo.setId(player.getId());
            vo.setName(player.getName());

            vos.add(vo);
        }

        DefaultResourceResponse response = new DefaultResourceResponse();
        response.setList(vos);

        return response;
    }

}
