package com.kc.pingpang.platform.data.mapper;

import com.kc.pingpang.platform.data.filter.CompetitionFilter;
import com.kc.pingpang.platform.data.model.Competition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompetitionMapper {

    List<Competition> selectCompetitionByFilter(@Param("filter") CompetitionFilter filter);

    int countCompetitionByFilter(@Param("filter") CompetitionFilter filter);
}