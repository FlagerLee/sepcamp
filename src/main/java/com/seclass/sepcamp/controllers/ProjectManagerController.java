package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.ProjectManager;
import com.seclass.sepcamp.models.Response;
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

    @PostMapping("/create")
    public Response CreateProjectManager(String DescribeText, int PhaseType, String Term) {
        return projectManagerService.CreateProjectManagerForUsers(DescribeText,PhaseType,Term);
    }

    @PostMapping("/delete")
    public Response DeleteProjectManager(String ManagerId) {
         return projectManagerService.DeleteProjectManager(ManagerId);
    }

    @PostMapping("/update")
    public Response UpdateProjectManager(String DescribeText,int PhaseType,String ManagerId) {
        return projectManagerService.UpdateProjectManager(DescribeText,PhaseType,ManagerId);
    }
    @PostMapping("/submit")
    public Response SubmitProjectManager(String ManagerId, int ProjectId, boolean IsSubmitted, String TextAnswer, String FileAnswer) {
        return projectManagerService.SubmitProjectManagerByUser(ManagerId,ProjectId,IsSubmitted,TextAnswer,FileAnswer);
    }
    @PostMapping("/getOneList")
    public List<ProjectManager> GetOneProjectManagerList(String ManagerId) {
        return projectManagerService.GetOneProjectManagerList(ManagerId);
    }
    @PostMapping("/getAllList")
    public List<ProjectManager> GetAllProjectManagerList(String term) {
        return projectManagerService.GetAllProjectManagerList(term);
    }
    @PostMapping("/getOne")
    public ProjectManager GetOneProjectManager(String ManagerId,int ProjectId) {
        return projectManagerService.GetOneProjectManager(ManagerId,ProjectId);
    }





}
