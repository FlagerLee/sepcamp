package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.TeamMapper;
import com.seclass.sepcamp.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;
    public Team getTeamById(int teamId){
       // return new Team(1,"123",1,1,"321");
        return teamMapper.GetOneTeam(teamId);
    }
}
