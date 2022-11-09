package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public boolean AddOneTeam(){
       return teamService.AddTeam(new Team("newName",1, 1, "1"));
    }


    @RequestMapping("CreateTeam")
    public boolean CreateTeam(HttpServletRequest request) throws InterruptedException{

        String teamName =  request.getAttribute("teamName").toString();
        int userId = (int) request.getAttribute("userId");
        int projectId = (int) request.getAttribute("projectId");
        String term =  request.getAttribute("term").toString();

        //TODO 将用户的队伍id设置为此队伍id
        return teamService.AddTeam(new Team(teamName,userId, projectId, term));
    }
    @RequestMapping("ChangeTeamLeader")
    public boolean ChangeTeamLeader(HttpServletRequest request) throws InterruptedException{

        int teamId =  (int)request.getAttribute("teamId");
        int userId = (int) request.getAttribute("userId");

        return teamService.UpdateTeamLeaderById(userId,teamId);
    }

    @RequestMapping("ChangeTeamProject")
    public boolean ChangeTeamProject(HttpServletRequest request) throws InterruptedException{

        int teamId =  (int)request.getAttribute("teamId");
        int projectId = (int) request.getAttribute("projectId");

        return teamService.UpdateTeamProjectById(projectId,teamId);
    }

    @RequestMapping("DropOutOfLine")
    public boolean DropOutOfLine(HttpServletRequest request) throws InterruptedException{

        int userId = (int) request.getAttribute("userId");

        //TODO 用户处将队伍id改为 0 或 -1
        //return teamService.UpdateTeamLeaderById(userId,teamId);

        return true;
    }


}
