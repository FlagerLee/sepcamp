package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.ProjectDao;
import com.seclass.sepcamp.models.Project;
import com.seclass.sepcamp.models.ResponseCreater;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectService {
    @Autowired
    private ProjectDao projectDao;


    ResponseCreater CreateNewProject(Project project){

        if(project.getIntroduction().length() > 1000 || project.getIntroduction().length() <= 0){
            return new ResponseCreater("项目描述长度不符合要求，请限制在1 ~ 1000个字符",false);
        }else  if(project.getTerm().length() > 5 || project.getTerm().length() <= 0 ){
            return new ResponseCreater("项目学期标识长度不符合要求，请限制在1 ~ 5个字符",false);
        }
        //TODO 可见的几个级别枚举值

        boolean result = projectDao.CreateOneProject(project) > 0;
        if(result){
            return new ResponseCreater("创建项目成功",true);
        }else{
            return new ResponseCreater("创建项目失败",false);
        }
    }



    ResponseCreater DeleteProject(int ProjectId){
        boolean result = projectDao.DeleteOneProject(ProjectId) > 0;
        if(result){
            return new ResponseCreater("删除项目成功",true);
        }else{
            return new ResponseCreater("删除项目失败",false);
        }
    }



}
