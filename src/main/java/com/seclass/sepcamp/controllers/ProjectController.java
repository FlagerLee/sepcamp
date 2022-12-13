package com.seclass.sepcamp.controllers;


import com.seclass.sepcamp.models.Project;
import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/create")
    public Response Create(Project project) {
        return projectService.CreateNewProject(new Project(project.getIntroduction(),project.getProject_name(),project.getVisible(),project.getTerm()));
    }

    @PostMapping("/delete")
    public Response Delete(int ProjectId) {
        return projectService.DeleteProject(ProjectId);
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
    public Project GetProjectById(@RequestBody Project project) {
        return projectService.GetProject(project.getProject_id());
    }
    @PostMapping("/getProjects")
    public List<Project> GetProjects(int Visible,String Term) {
        return projectService.GetProjects(Visible,Term);
    }



}
