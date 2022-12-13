package com.seclass.sepcamp.controllers;


import com.seclass.sepcamp.models.Project;
import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.services.ProjectService;
import com.seclass.sepcamp.services.TeamService;
import com.seclass.sepcamp.services.UserService;
import com.seclass.sepcamp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;

    static class ProjectCreateInfo {
        private String group_name;
        private String project_name;
        private String term;
        private List<Integer> member_ids;
        private String introduction;

        public String getGroup_name() {
            return group_name;
        }

        public String getProject_name() {
            return project_name;
        }

        public String getTerm() {
            return term;
        }

        public List<Integer> getMember_ids() {
            return member_ids;
        }

        public String getIntroduction() {
            return introduction;
        }
    }
    @PostMapping("/create")
    public Response Create(@RequestBody ProjectCreateInfo createInfo) {
        Response createProject = projectService.CreateNewProject(new Project(createInfo.getIntroduction(),createInfo.getProject_name(),0,createInfo.getTerm()));
        if(!createProject.getSuccess()) return createProject;
        int project_id = projectService.GetProjectIdByProjectName(createInfo.project_name);
        List<Integer> team_members = createInfo.getMember_ids();
        Response createTeam = teamService.CreateTeam(new Team(createInfo.getGroup_name(), team_members.get(0), project_id, createInfo.getTerm()));
        if(!createTeam.getSuccess()) return createTeam;
        int team_id = teamService.GetTeamIdByTeamName(createInfo.getGroup_name());
        for(int member_id: team_members) {
            Response response = userService.setTeam(team_id, member_id);
            if(!response.getSuccess()) return response;
        }
        return ResponseUtils.ResponseMaker(true,"创建小组成功", "");
    }

    @PostMapping("/delete")
    public Response Delete(int ProjectId) {
        return projectService.DeleteProject(ProjectId);
    }
    @PostMapping("/changeName")
    public Response ChangeName(@RequestBody  Project project) {
        System.out.println(project.getProject_name());
        return projectService.ChangeProjectName(project);
    }
    @PostMapping("/changeIntroduction")
    public Response ChangeIntroduction(int ProjectId, String Introduction) {
        return projectService.ChangeProjectIntroduction(ProjectId,Introduction);
    }
    @PostMapping("/changeVisible")
    public Response ChangeVisible(int ProjectId, int Visible) {
        return projectService.ChangeProjectVisible(ProjectId,Visible);
    }

    @PostMapping("/getProject")
    public Project GetProjectById(@RequestBody Team team) {
        return projectService.GetProject(team.getProject_id());
    }

    @PostMapping("/getProjects")
    public List<Project> GetProjects(int Visible,String Term) {
        return projectService.GetProjects(Visible,Term);
    }

}
