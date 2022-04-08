package com.kc.pingpang.platform.controller.admin.competition.api;

import com.kc.pingpang.platform.data.model.Competition;
import com.kc.pingpang.platform.freamwork.utils.DateTimeUtility;

import java.util.ArrayList;
import java.util.List;

public class CompetitionVO {

    private Integer id;
    private String name;
    private Integer participantLimit;
    private String date;
    private String createTime;

    public static List<CompetitionVO> toVOs(List<Competition> competitions) {

        List<CompetitionVO> vos = new ArrayList<>();
        for (Competition competition : competitions) {
            vos.add(toVO(competition));
        }

        return vos;
    }

    public static CompetitionVO toVO(Competition competition) {

        CompetitionVO vo = new CompetitionVO();
        vo.setId(competition.getId());
        vo.setName(competition.getName());
        vo.setParticipantLimit(competition.getParticipantLimit());
        vo.setDate(DateTimeUtility.formatYYYYMMDD(competition.getDate()));
        vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMM(competition.getCreateTime()));

        return vo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
