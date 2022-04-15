package com.kc.pingpang.platform.data.mapper;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.data.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompetitionMapper {

    List<Competition> selectCompetitionByFilter(@Param("filter") CompetitionFilter filter);

    int countCompetitionByFilter(@Param("filter") CompetitionFilter filter);

    void updateCompetition(Competition competition);

    Competition selectCompetitionById(Integer id);

    void batchInsertCompetitionGroup(@Param("groups") List<CompetitionGroup> groups);

    CompetitionGroupPlayer selectCompetitionGroupPlayerByKey(@Param("playerId") Integer playerId, @Param("groupId") Integer groupId);

    void insertCompetitionGroupPlayer(CompetitionGroupPlayer groupPlayer);

    void deleteCompetitionGroupPlayer(Integer id);

    CompetitionPlayer selectCompetitionPlayerByPlayerIdAndCompetitionId(@Param("playerId") Integer playerId, @Param("competitionId") Integer competitionId);

    CompetitionPlayer selectCompetitionPlayerByPlayerNameAndCompetitionId(@Param("playerName") String playerName, @Param("competitionId") Integer competitionId);

    List<CompetitionPlayer> selectCompetitionPlayerByCompetitionId(Integer competitionId);

    void insertCompetitionPlayer(CompetitionPlayer competitionPlayer);

    CompetitionPlayer selectCompetitionPlayerById(Integer id);

    void deleteCompetitionGroupPlayerByCompetitionIdAndPlayerId(@Param("competitionId") Integer competitionId, @Param("playerId") Integer playerId);

    void deleteCompetitionPlayer(Integer id);

    List<Player> selectNotGroupPlayers(Integer competitionId);

    void deleteCompetitionGroupPlayerByGroupId(Integer groupId);

    void deleteCompetitionGroup(Integer groupId);

    CompetitionGroup selectLastCompetitionGroupByCompetitionId(Integer competitionId);

    void insertCompetitionGroup(CompetitionGroup group);

    void insertCompetition(Competition competition);

    List<Player> selectNotCompetitionPlayers(Integer competitionId);

    void deleteCompetitionPlayerByPlayerId(Integer playerId);

    void deleteCompetitionGroupPlayerByPlayerId(Integer playerId);
}