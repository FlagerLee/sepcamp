package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    public TeamService teamService;

    @RequestMapping("getTeamById/{teamId}")
    public String GetTeamById(@PathVariable int teamId){


        return teamService.getTeamById(teamId).toString();
    }

    @RequestMapping("getAllTeam")
    public String GetAllTeams(){


        return teamService.getAllTeams().toString();
    }
}
