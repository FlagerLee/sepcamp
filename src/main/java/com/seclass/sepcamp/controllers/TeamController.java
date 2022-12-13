package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    static class UserList {
        List<Integer> ids;
        List<String> names;

        public List<Integer> getIds() {
            return ids;
        }

        public void setIds(List<Integer> ids) {
            this.ids = ids;
        }

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }

        @Override
        public String toString() {
            return "UserList{" +
                    "ids=" + ids +
                    ", names=" + names +
                    '}';
        }
    }

    @PostMapping("GetTeamMember")
    public UserList GetTeamMember(int teamId) {

        List<User> list = teamService.GetTeamMember(teamId);
        UserList userlist = new UserList();
        userlist.ids = new ArrayList<>();
        userlist.names = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            userlist.ids.add(list.get(i).getUser_id());
            userlist.names.add(list.get(i).getName());
        }

        return userlist;
    }

    @PostMapping("DropOutOfLine")
    public Response DropOutOfLine(int userId)
    {
        return teamService.DropOutOfLine(userId);

    }


}
