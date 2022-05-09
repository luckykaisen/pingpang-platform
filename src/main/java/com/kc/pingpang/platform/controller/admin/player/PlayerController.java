package com.kc.pingpang.platform.controller.admin.player;

import com.kc.pingpang.platform.controller.admin.player.api.*;
import com.kc.pingpang.platform.data.mapper.PlayerMapper;
import com.kc.pingpang.platform.data.model.Player;
import com.kc.pingpang.platform.freamwork.db.filter.SearchResult;
import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;
import com.kc.pingpang.platform.service.player.api.IPlayerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController("AdminPlayerController")
@RequestMapping("/services/rs/admin/player")
public class PlayerController {

    @Resource
    private IPlayerService playerService;

    @Resource
    private PlayerMapper playerMapper;

    @RequestMapping("/search")
    public ServiceResponse searchPlayer(@RequestBody SearchPlayerRequest request) {

        SearchResult<Player> result = playerService.searchPlayerByFilter(request.toFilter());

        List<PlayerVO> vos = PlayerVO.toVOs(result.getResult());

        SearchPlayerResponse response = new SearchPlayerResponse();
        response.setList(vos);
        response.setPagingResult(result.getPagingResult());

        return response;
    }

    @RequestMapping("/create")
    public ServiceResponse createPlayer(@RequestBody CreatePlayerRequest request) {

        ServiceResponse response = new ServiceResponse();

        Player player = playerMapper.selectPlayerByName(request.getName());
        if (player != null) {
            response.setResultMessage("会员已存在，请勿重复添加！");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        player = new Player();
        player.setName(request.getName());

        playerMapper.insertPlayer(player);

        return response;
    }

    @RequestMapping("/modify")
    public ServiceResponse modifyPlayer(@RequestBody ModifyPlayerRequest request) {

        ServiceResponse response = new ServiceResponse();

        Player player = playerMapper.selectPlayerByName(request.getName());
        if (player != null && !player.getId().equals(request.getId())) {
            response.setResultMessage("会员已存在，请勿重复添加！");
            response.setResultCode(ServiceResponse.CODE_FAILED);

            return response;
        }

        Player newPlayer = new Player();
        newPlayer.setId(request.getId());
        newPlayer.setName(request.getName());

        playerMapper.updatePlayer(newPlayer);

        return response;
    }

    @RequestMapping("/delete")
    public ServiceResponse deletePlayer(@RequestParam Integer id) {

        playerService.deletePlayer(id);

        return new ServiceResponse();
    }

}
