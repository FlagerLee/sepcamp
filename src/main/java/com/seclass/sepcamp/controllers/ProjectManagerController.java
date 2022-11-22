package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.ProjectManager;
import com.seclass.sepcamp.models.ResponseCreater;
import com.seclass.sepcamp.services.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projectmanager")
public class ProjectManagerController {

    @Autowired
    ProjectManagerService projectManagerService;

    @PostMapping("/CreateProjectManager")
    public ResponseCreater CreateProjectManager(String DescribeText, int PhaseType, String Term) {
        return projectManagerService.CreateProjectManagerForUsers(DescribeText,PhaseType,Term);
    }

    @PostMapping("/DeleteProjectManager")
    public void DeleteProjectManager(String ManagerId) {
        projectManagerService.DeleteProjectManager(ManagerId);
    }

    @PostMapping("/UpdateProjectManager")
    public void UpdateProjectManager(String DescribeText,int PhaseType,String ManagerId) {
        projectManagerService.UpdateProjectManager(DescribeText,PhaseType,ManagerId);
    }
    @PostMapping("/SubmitProjectManager")
    public ResponseCreater SubmitProjectManager(String ManagerId,int ProjectId,boolean IsSubmitted,String TextAnswer,String FileAnswer) {
        return projectManagerService.SubmitProjectManagerByUser(ManagerId,ProjectId,IsSubmitted,TextAnswer,FileAnswer);
    }
    @PostMapping("/GetOneProjectManagerList")
    public List<ProjectManager> GetOneProjectManagerList(String ManagerId) {
        return projectManagerService.GetOneProjectManagerList(ManagerId);
    }
    @PostMapping("/GetAllProjectManagerList")
    public List<ProjectManager> GetAllProjectManagerList(String term) {
        return projectManagerService.GetAllProjectManagerList(term);
    }
    @PostMapping("/GetOneProjectManager")
    public ProjectManager GetOneProjectManager(String ManagerId,int ProjectId) {
        return projectManagerService.GetOneProjectManager(ManagerId,ProjectId);
    }





}
