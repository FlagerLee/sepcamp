package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response AddOneTeam(){
       return teamService.CreateTeam(new Team("newName",1, 1, "1"));
    }


    @PostMapping("/CreateTeam")
    public Response CreateTeam(Team team) {
        return teamService.CreateTeam(team);
    }
    @PostMapping("ChangeTeamLeader")
    public Response ChangeTeamLeader(int userId, int teamId) {
        return teamService.UpdateTeamLeaderById(userId,teamId);
    }

    @PostMapping("ChangeTeamProject")
    public Response ChangeTeamProject(int projectId, int teamId) {
        return teamService.UpdateTeamProjectById(projectId,teamId);
    }

    @PostMapping("ChangeTeamName")
    public Response ChangeTeamName(String teamName, int teamId) {
        return teamService.UpdateTeamNameById(teamName,teamId);
    }

    @PostMapping("DropOutOfLine")
    public Response DropOutOfLine(int userId)
    {
        return teamService.DropOutOfLine(userId);

    }


}
