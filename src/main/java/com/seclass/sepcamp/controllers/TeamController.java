package com.seclass.sepcamp.controllers;

import com.google.gson.Gson;
import com.seclass.sepcamp.models.ResponseCreater;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.services.TeamService;
import com.seclass.sepcamp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    public TeamService teamService;

    @RequestMapping("getTeamById/{teamId}")
    public String GetTeamById(@PathVariable int teamId){
        return teamService.GetTeamById(teamId).toString();
    }

    @RequestMapping("getAllTeam")
    public String GetAllTeams(){
        return teamService.GetAllTeams().toString();
    }

    //test interface
    @RequestMapping("AddOneTeam")
    public ResponseCreater AddOneTeam(){
       return teamService.AddTeam(new Team("newName",1, 1, "1"));
    }


    @PostMapping("CreateTeam")
    public ResponseCreater CreateTeam(Team team) {
        return teamService.AddTeam(team);
    }
    @RequestMapping("ChangeTeamLeader")
    public ResponseCreater ChangeTeamLeader(@PathVariable int teamId,@PathVariable int userId) {
        return teamService.UpdateTeamLeaderById(userId,teamId);
    }

    @RequestMapping("ChangeTeamProject")
    public ResponseCreater ChangeTeamProject(@PathVariable int projectId,@PathVariable int teamId) {
        return teamService.UpdateTeamProjectById(projectId,teamId);
    }

    @RequestMapping("ChangeTeamProject")
    public ResponseCreater ChangeTeamNasme(@PathVariable String teamName,@PathVariable int teamId) {
        return teamService.UpdateTeamNameById(teamName,teamId);
    }

    @RequestMapping("DropOutOfLine")
    public ResponseCreater DropOutOfLine(@PathVariable int userId)
    {
        //TODO 用户处将队伍id改为 0 或 -1
        return teamService.DropOutOfLine(userId);

    }


}
