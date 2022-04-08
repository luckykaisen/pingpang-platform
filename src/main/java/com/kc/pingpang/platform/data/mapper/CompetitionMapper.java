package com.kc.pingpang.platform.data.mapper;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.data.model.CompetitionGroup;
import com.kc.pingpang.platform.data.model.CompetitionGroupPlayer;
import com.kc.pingpang.platform.data.model.CompetitionPlayer;
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

    CompetitionPlayer selectCompetitionPlayerByPlayerNameAndCompetitionId(@Param("playerName") String playerName, @Param("competitionId") Integer competitionId);

    List<CompetitionPlayer> selectCompetitionPlayerByCompetitionId(Integer competitionId);

    void insertCompetitionPlayer(CompetitionPlayer competitionPlayer);
}