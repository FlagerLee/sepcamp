package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.Team;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMapper {

    @Select("select * from sepcamp_team where team_id = #{teamId}")
    Team GetOneTeam(int teamId);

    @Select("SELECT * FROM SEPCAMP_TEAM WHERE TEAM_NAME = #{teamName}")
    List<Team> GetTeamByTeamname(String teamName);

    @Select("SELECT * FROM SEPCAMP_TEAM WHERE Term = #{Term}")
    List<Team> GetTeamByTerm(String Term);

    @Select("SELECT * FROM SEPCAMP_TEAM")
    List<Team> GetAllTeams();

    @Insert("insert into sepcamp_team(TEAM_NAME,LEADER,PROJECT_ID,TERM) values(#{Team_name},#{Leader},#{Project_id},#{Term})")
    int CreateTeam(Team team);

    int DeleteOneTeam(int teamId);

    int UpdateTeamNameById(String teamName,int teamId);

    int UpdateTeamLeaderById(int leaderId,int teamId);

    int UpdateTeamProjectById(int  projectId,int teamId);

    int UpdateTeamTermById(String termId,int teamId);

    int UpdateTeamById(Team team);

}
