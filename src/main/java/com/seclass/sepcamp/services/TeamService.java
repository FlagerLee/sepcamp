package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.TeamMapper;
import com.seclass.sepcamp.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;
    public Team GetTeamById(int teamId){
       // return new Team(1,"123",1,1,"321");
        return teamMapper.GetOneTeam(teamId);
    }

    public List<Team> GetAllTeams(){
        // return new Team(1,"123",1,1,"321");
        return teamMapper.GetAllTeams();
    }
    public boolean AddTeam(Team t){

        return teamMapper.CreateTeam(t) > 0 ? true : false;
    }

    public boolean DeleteOneTeam(int teamId) {

        return teamMapper.DeleteOneTeam(teamId)> 0 ? true : false;
    }

    public boolean UpdateTeamNameById(String teamName,int teamId) {
        return teamMapper.UpdateTeamNameById(teamName,teamId)> 0 ? true : false;
    }

    public boolean UpdateTeamLeaderById(int leaderId,int teamId) {
        return teamMapper.UpdateTeamLeaderById(leaderId,teamId) > 0 ? true : false;
    }

    public boolean UpdateTeamProjectById(int  projectId,int teamId) {
        return teamMapper.UpdateTeamProjectById(projectId,teamId)> 0 ? true : false;
    }

    public boolean UpdateTeamTermById(String termId,int teamId) {
        return teamMapper.UpdateTeamTermById(termId,teamId)> 0 ? true : false;
    }

    public boolean UpdateTeamById(Team team) {
        return teamMapper.UpdateTeamById(team)> 0 ? true : false;
    }


}
