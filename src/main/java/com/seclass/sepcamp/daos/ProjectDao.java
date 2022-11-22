package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectDao {

    @Insert("INSERT INTO SEPCAMP_PROJECT(INTRODUCTION, VISIBLE, TERM) " +
            "VALUE(#{Introduction}, #{Visible}, #{Term})")
    int CreateOneProject(Project project);

    @Delete("Delete From SEPCAMP_PROJECT  Where Project_Id = #{Project_Id}")
    int DeleteOneProject(int Project_Id);

    @Select("Select Project_Id,Introduction,Visible,Term" +
            "From SEPCAMP_PROJECT" +
            "Where Visible = #{Visible} AND Term = #{Term}")
    List<Project>  GetProjectList(int Visible,String Term);

    @Select("Select Project_Id,Introduction,Visible,Term" +
            "From SEPCAMP_PROJECT" +
            "Where Project_Id = #{Project_Id}")
    Project  GetProjectById(int Project_Id);

    @Update("Update Sepcamp_project Set Introduction = #{Introduction} Where Project_Id = #{Project_Id}")
    int UpdateIntroductionByProjectId(int Project_Id,String Introduction);

    @Update("Update Sepcamp_project Set Visible = #{Visible} Where Project_Id = #{Project_Id}")
    int UpdateVisibleByProjectId(int Project_Id,int Visible);

    @Update("Update Sepcamp_project Set Term = #{Term} Where Project_Id = #{Project_Id}")
    int UpdateTermByProjectId(int Project_Id,String Term);

}
