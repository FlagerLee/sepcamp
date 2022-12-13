package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.ProjectDao;
import com.seclass.sepcamp.models.Project;
import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    @Autowired
    private ProjectDao projectDao;


    public Response CreateNewProject(Project project){

        if(project.getIntroduction().length() > 1000 || project.getIntroduction().length() <= 0){
            return new Response("项目描述长度不符合要求，请限制在1 ~ 1000个字符",false);
        }else  if(project.getTerm().length() > 5 || project.getTerm().length() <= 0 ){
            return new Response("项目学期标识长度不符合要求，请限制在1 ~ 5个字符",false);
        }
        //TODO 可见的几个级别枚举值

        boolean result = projectDao.CreateOneProject(project) > 0;

        return ResponseUtils.ResponseMaker(result,"创建项目成功","创建项目失败");
    }



    public Response DeleteProject(int ProjectId){
        boolean result = projectDao.DeleteOneProject(ProjectId) > 0;
        return ResponseUtils.ResponseMaker(result,"删除项目成功","删除项目失败");
    }

    public Response ChangeProjectIntroduction(int ProjectId, String Introduction) {

        if (Introduction.length() > 1000 || Introduction.length() <= 0) {
            return new Response("项目描述长度不符合要求，请限制在1 ~ 1000个字符", false);
        }
        boolean ChangeSuccess = projectDao.UpdateIntroductionByProjectId(ProjectId, Introduction) > 0;
        return ResponseUtils.ResponseMaker(ChangeSuccess,"修改项目介绍成功","修改项目介绍失败");
    }

    public Response ChangeProjectName(Project project) {


        boolean ChangeSuccess = projectDao.UpdateNameByProjectId(project.getProject_id(), project.getProject_name()) > 0;
        return ResponseUtils.ResponseMaker(ChangeSuccess,"修改项目名称成功","修改项目名称失败");
    }

    public Response ChangeProjectVisible(int ProjectId, int Visible){

        boolean ChangeSuccess = projectDao.UpdateVisibleByProjectId(ProjectId,Visible) > 0;
        return ResponseUtils.ResponseMaker(ChangeSuccess,"修改项目可见范围成功","修改项目可见范围失败");
    }

    public Project GetProject(int ProjectId){
        return projectDao.GetProjectById(ProjectId);

    }

    public List<Project> GetProjects(int Visible,String Term){

        return projectDao.GetProjectList(Visible,Term);

    }

}
