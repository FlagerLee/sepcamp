package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Project;
import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.services.TeamService;
import com.seclass.sepcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    public TeamService teamService;
    @Autowired
    private UserService userService;

    @PostMapping("getTeamById")
    public Team GetTeamById(@RequestBody Team team){
        Team result = teamService.GetTeamById(team.getTeam_id());
        return result;
    }

    @PostMapping("getAllTeam")
    public String GetAllTeams(){
        return teamService.GetAllTeams().toString();
    }

    //test interface
    @PostMapping("AddOneTeam")
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

    @PostMapping("ChangeTeamInformation")
    public Response ChangeTeamInformation(Team team) {
        System.out.println(team);
        return teamService.UpdateTeamInformation(team);
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

    }
    @PostMapping("GetTeamMember")
    public UserList GetTeamMember(@RequestBody Team team) {

        List<User> list = teamService.GetTeamMember(team.getTeam_id());
        UserList userlist = new UserList();
        userlist.ids = new ArrayList<>();
        userlist.names = new ArrayList<>();
        System.out.println(list.get(0).toString());
        for(int i = 0;i<list.size();i++){
            userlist.ids.add(list.get(i).getUser_id());
            userlist.names.add(list.get(i).getName());
        }
        return userlist;
    }

    @PostMapping("DropOutOfLine")
    public Response DropOutOfLine(@RequestBody User user)
    {
        return teamService.DropOutOfLine(user.getUser_id());

    }

    static class TeamInfo {
        private String team_name;
        private String team_leader;
        private String introduction;
        private int team_members;
        private int team_id;
        private int team_leader_id;

        public void setTeam_name(String project_name) {
            this.team_name = project_name;
        }

        public void setTeam_leader(String team_leader) {
            this.team_leader = team_leader;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public void setTeam_members(int team_members) {
            this.team_members = team_members;
        }

        public void setTeam_id(int team_id) {
            this.team_id = team_id;
        }

        public int getTeam_leader_id() {
            return team_leader_id;
        }

        public void setTeam_leader_id(int team_leader_id) {
            this.team_leader_id = team_leader_id;
        }

        public int getTeam_members() {
            return team_members;
        }
    }

    @GetMapping("GetAllTeamInfo")
    public List<TeamInfo> getAllTeamInfo() {
        List<Team> teams = teamService.GetAllTeams();
        Map<Integer, TeamInfo> teamInfoMap = new HashMap<>();
        List<TeamInfo> teamInfoList = new ArrayList<>();
        for(Team team: teams) {
            TeamInfo teamInfo = new TeamInfo();
            teamInfo.setTeam_name(team.getTeam_name());
            teamInfo.setTeam_leader_id(team.getLeader());
            teamInfo.setTeam_id(team.getTeam_id());
            teamInfo.setIntroduction(team.getIntroduction());
            teamInfo.setTeam_members(1);
            teamInfoMap.put(team.getTeam_id(), teamInfo);
        }
        List<User> teamed_users = userService.getTeamedUser();
        for(User user: teamed_users) {
            int team_id = user.getTeam_id();
            TeamInfo teamInfo = teamInfoMap.get(team_id);
            if(teamInfo.getTeam_leader_id() == user.getUser_id()) {
                teamInfo.setTeam_leader(user.getUsername());
            }
            else {
                teamInfo.setTeam_members(teamInfo.getTeam_members());
            }
        }
        for(Map.Entry<Integer, TeamInfo> entry: teamInfoMap.entrySet()) {
            teamInfoList.add(entry.getValue());
        }
        return teamInfoList;
    }
}
