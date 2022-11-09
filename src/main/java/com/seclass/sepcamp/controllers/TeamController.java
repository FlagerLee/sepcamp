package com.seclass.sepcamp.controllers;

import com.google.gson.Gson;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.services.TeamService;
import com.seclass.sepcamp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public boolean AddOneTeam(){
       return teamService.AddTeam(new Team("newName",1, 1, "1"));
    }


    @RequestMapping("CreateTeam")
    public void CreateTeam(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String teamName =  request.getAttribute("teamName").toString();
        int userId = (int) request.getAttribute("userId");
        int projectId = (int) request.getAttribute("projectId");
        String term =  request.getAttribute("term").toString();

        //TODO 将用户的队伍id设置为此队伍id
        boolean result = teamService.AddTeam(new Team(teamName,userId, projectId, term));
        LinkedHashMap<String,String> resultMap = new LinkedHashMap<>();
        resultMap.put("isSuccess", String.valueOf(result));


        response.getWriter().print(ResponseUtils.MapToString(resultMap));

        return ;
    }
    @RequestMapping("ChangeTeamLeader")
    public boolean ChangeTeamLeader(HttpServletRequest request) {

        int teamId =  (int)request.getAttribute("teamId");
        int userId = (int) request.getAttribute("userId");

        return teamService.UpdateTeamLeaderById(userId,teamId);
    }

    @RequestMapping("ChangeTeamProject")
    public boolean ChangeTeamProject(HttpServletRequest request) {

        int teamId =  (int)request.getAttribute("teamId");
        int projectId = (int) request.getAttribute("projectId");

        return teamService.UpdateTeamProjectById(projectId,teamId);
    }

    @RequestMapping("DropOutOfLine")
    public boolean DropOutOfLine(HttpServletRequest request)
    {

        int userId = (int) request.getAttribute("userId");

        //TODO 用户处将队伍id改为 0 或 -1
        //return teamService.UpdateTeamLeaderById(userId,teamId);

        return true;
    }


}
