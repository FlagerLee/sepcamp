package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.Team;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    Team GetOneTeam(int teamId);

    @Select("SELECT * FROM SEPCAMP_TEAM WHERE TEAM_NAME = #{teamName}")
    List<Team> GetTeamByTeamname(String teamName);

    List<Team> GetAllTeams();

    int CreateTeam(Team team);

    int DeleteOneTeam(int teamId);

    int UpdateTeamNameById(String teamName,int teamId);

    int UpdateTeamLeaderById(int leaderId,int teamId);

    int UpdateTeamProjectById(int  projectId,int teamId);

    int UpdateTeamTermById(String termId,int teamId);

    int UpdateTeamById(Team team);

}
