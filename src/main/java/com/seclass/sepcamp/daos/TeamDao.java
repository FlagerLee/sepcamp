package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamDao {

    @Select("select * from sepcamp_team where team_id = #{teamId}")
    Team GetOneTeam(int teamId);

    @Select("SELECT * FROM SEPCAMP_TEAM WHERE TEAM_NAME = #{teamName}")
    List<Team> GetTeamByTeamname(String teamName);

    @Select("SELECT * FROM SEPCAMP_TEAM WHERE Term = #{Term}")
    List<Team> GetTeamByTerm(String Term);

    @Select("SELECT * FROM SEPCAMP_TEAM")
    List<Team> GetAllTeams();

    @Insert("insert into sepcamp_team(TEAM_NAME,LEADER,PROJECT_ID,TERM,Interests,Introduction,QQNUMBER) values(#{Team_name},#{Leader},#{Project_id},#{Term},#{Interests},#{Introduction},#{QQNumber})")
    int CreateTeam(Team team);

    @Delete("delete from sepcamp_team where team_id = #{teamId}")
    int DeleteOneTeam(int teamId);

    @Update("update sepcamp_team set TEAM_NAME =#{teamName}  where team_id = #{teamId}")
    int UpdateTeamNameById(String teamName,int teamId);

    @Update("update sepcamp_team set LEADER =#{leaderId}  where team_id = #{teamId}")
    int UpdateTeamLeaderById(int leaderId,int teamId);

    @Update("update sepcamp_team set PROJECT_ID =#{projectId}  where team_id = #{teamId}")
    int UpdateTeamProjectById(int  projectId,int teamId);

    @Update("update sepcamp_team set TERM =#{termId}  where team_id = #{teamId}")
    int UpdateTeamTermById(String termId,int teamId);

    @Update("update sepcamp_team set TEAM_NAME =#{Team_name},Interests=#{Interests},Introduction=#{Introduction},QQNumber=#{QQNumber}  where team_id = #{Team_id}")
    int UpdateTeamById(Team team);


}
