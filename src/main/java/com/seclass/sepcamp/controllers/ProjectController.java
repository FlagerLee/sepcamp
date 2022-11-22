package com.seclass.sepcamp.controllers;


import com.seclass.sepcamp.models.Project;
import com.seclass.sepcamp.models.ResponseCreater;
import com.seclass.sepcamp.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/Create")
    public ResponseCreater Create(String Introduction, int Visible, String Term) {
        return projectService.CreateNewProject(new Project(Introduction,Visible,Term));
    }

    @PostMapping("/Delete")
    public ResponseCreater Delete(int ProjectId) {
        return projectService.DeleteProject(ProjectId);
    }
    @PostMapping("/ChangeIntroduction")
    public ResponseCreater ChangeIntroduction( int ProjectId,String Introduction) {
        return projectService.ChangeProjectIntroduction(ProjectId,Introduction);
    }
    @PostMapping("/ChangeVisible")
    public ResponseCreater ChangeVisible(int ProjectId, int Visible) {
        return projectService.ChangeProjectVisible(ProjectId,Visible);
    }
    @PostMapping("/GetProject")
    public Project GetProjectById(int ProjectId) {
        return projectService.GetProject(ProjectId);
    }
    @PostMapping("/GetProjects")
    public List<Project> GetProjects(int Visible,String Term) {
        return projectService.GetProjects(Visible,Term);
    }



}
