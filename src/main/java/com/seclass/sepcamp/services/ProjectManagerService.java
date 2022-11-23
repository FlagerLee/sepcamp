package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.ProjectManagerDao;
import com.seclass.sepcamp.daos.TeamMapper;
import com.seclass.sepcamp.models.*;
import com.seclass.sepcamp.utils.ResponseUtils;
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


    public Response CreateProjectManagerForUsers(String DescribeText, int PhaseType, String Term) {
        if(DescribeText.length() <= 0 || DescribeText.length() >= 1000){
            return new Response("描述文本长度不符合要求，应在1~1000个字符之间",false);
        }
        if(Term.length() <= 0 || Term.length() > 5){
            return new Response("学期格式不符合要求",false);
        }

        //TODO 统一term格式
        // String thisTerm = "20222";
        List<Team> teamList = teamMapper.GetTeamByTerm(Term);
        ArrayList<ProjectManager> projectManagerList = new ArrayList<ProjectManager>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
        long currentTime = System.currentTimeMillis();

        for(int i = 0 ;i < teamList.size();i++){
            ProjectManager temp = new ProjectManager(
                    df.format(currentTime),
                    teamList.get(i).getProject_id(),
                    teamList.get(i).getTeam_id(),
                    PhaseType,
                    DescribeText,
                    Term);
            projectManagerList.add(temp);
        }

        boolean createProjectManagerSuccess = projectManagerDao.CreateProjectManagerForTeams(projectManagerList) >  0;

        return ResponseUtils.ResponseMaker(createProjectManagerSuccess,"创建成功","创建失败");

    }

    public Response DeleteProjectManager(String managerId) {

        boolean deleteSuccess = projectManagerDao.DeleteOneProjectManager(managerId) > 0;
        return ResponseUtils.ResponseMaker(deleteSuccess,"删除阶段成功","删除阶段失败");
    }

    public Response UpdateProjectManager(String DescribeText,int PhaseType,String ManagerId) {
        boolean updateSuccess = projectManagerDao.UpdateProjectManagerForTeams(DescribeText,PhaseType,ManagerId) > 0 ;
        return ResponseUtils.ResponseMaker(updateSuccess,"更新阶段成功","更新阶段失败");
    }

    public Response SubmitProjectManagerByUser(String ManagerId, int ProjectId, boolean isSubmitted, String textAnswer, String fileAnswer) {

        boolean updateProjectManagerSuccess = projectManagerDao.SubmitProjectManager(
                new ProjectManager(ManagerId, ProjectId, isSubmitted, textAnswer, fileAnswer)
        ) > 0;
        return ResponseUtils.ResponseMaker(updateProjectManagerSuccess,"提交成功","提交失败");
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
