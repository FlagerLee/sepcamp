package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.ProjectManagerDao;
import com.seclass.sepcamp.daos.TeamMapper;
import com.seclass.sepcamp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectManagerService {

    @Autowired
    ProjectManagerDao projectManagerDao;
    @Autowired
    TeamMapper teamMapper;


    public ResponseCreater CreateProjectManagerForUsers(String DescribeText, int PhaseType, String Term) {
        if(DescribeText.length() <= 0 || DescribeText.length() >= 1000){
            return new ResponseCreater("描述文本长度不符合要求，应在1~1000个字符之间",false);
        }
        if(Term.length() <= 0 || Term.length() > 5){
            return new ResponseCreater("学期格式不符合要求",false);
        }

        //TODO 统一term格式
        // String thisTerm = "20222";
        List<Team> teamList = teamMapper.GetTeamByTerm(Term);
        ArrayList<ProjectManager> projectManagerList = new ArrayList<ProjectManager>();
        for(int i = 0 ;i < teamList.size();i++){
            ProjectManager temp = new ProjectManager(
                    String.valueOf(System.currentTimeMillis()),
                    teamList.get(i).getProject_id(),
                    teamList.get(i).getTeam_id(),
                    PhaseType,
                    DescribeText,
                    Term);
            projectManagerList.add(temp);
        }

        boolean createProjectManagerSuccess = projectManagerDao.CreateProjectManagerForTeams(projectManagerList) >  0;
        if(createProjectManagerSuccess){
            return new ResponseCreater("提交成功",true);
        }else{
            return new ResponseCreater("提交失败",true);
        }
    }

    public void DeleteProjectManager(String managerId) {
        projectManagerDao.DeleteOneProjectManager(managerId);
    }

    public void UpdateProjectManager(String DescribeText,int PhaseType,String ManagerId) {
        projectManagerDao.UpdateProjectManagerForTeams(DescribeText,PhaseType,ManagerId);
    }

    public ResponseCreater SubmitProjectManagerByUser(String ManagerId,int ProjectId, boolean isSubmitted, String textAnswer, String fileAnswer) {

        boolean updateProjectManagerSuccess = projectManagerDao.SubmitProjectManager(
                new ProjectManager(ManagerId, ProjectId, isSubmitted, textAnswer, fileAnswer)
        ) > 0;

        if(updateProjectManagerSuccess){
            return new ResponseCreater("提交作业成功",true);
        }else{
            return new ResponseCreater("提交作业失败",true);
        }
    }

    public List<ProjectManager> GetOneProjectManagerList(String ManagerId) {

        return projectManagerDao.GetProjectManagerByManagerId(ManagerId);
    }

    public List<ProjectManager> GetAllProjectManagerList(String term) {
        return projectManagerDao.GetProjectManagerByTerm(term);
    }

    public ProjectManager GetOneProjectManager(String ManagerId,int ProjectId) {
        return projectManagerDao.GetProjectManagerByMP(ManagerId,ProjectId);
    }
}
