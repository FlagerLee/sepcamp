package com.seclass.sepcamp.daos;


import com.seclass.sepcamp.models.Homework;

import com.seclass.sepcamp.models.ProjectManager;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectManagerDao {


    //多用户单项目阶段插入
    @Insert("<script> " +
            " INSERT INTO  SEPCAMP_PROJECTMANAGER(Manager_Id, Project_Id,Team_Id, Phase_Type, Describe_Text,Term) VALUES\n"+
            "  <foreach collection= 'list' item= 'item'  separator=','>\n" +
            "   (#{item.Manager_Id},#{item.Project_Id},#{item.Team_Id},#{item.Phase_Type},#{item.Describe_Text},#{item.Term})\n"+
            "  </foreach> \n"+
            "</script>")
    int CreateProjectManagerForTeams(@Param(value = "list") List<ProjectManager> list);

    //单用户单作业创建
    @Insert("INSERT INTO SEPCAMP_PROJECTMANAGER(Manager_Id, Project_Id,Team_Id, Phase_Type, Describe_Text) " +
            "VALUE(#{Manager_Id},#{Project_Id},#{Team_Id}, #{Phase_Type}, #{Describe_Text})")
    int CreateOneProjectManager(ProjectManager projectManager);


    @Select("Select * " +
            " From SEPCAMP_PROJECTMANAGER " +
            " Where Manager_Id = #{Manager_Id} ")
    List<ProjectManager>  GetProjectManagerByManagerId(String Manager_Id);


    @Select("Select Manager_Id,Phase_Type,Describe_Text\n" +
            "From SEPCAMP_PROJECTMANAGER\n" +
            "Where Term = #{Term}\n" +
            "Group by Manager_Id")
    List<ProjectManager>  GetProjectManagerByTerm(String Term);

    @Select("Select * " +
            " From SEPCAMP_PROJECTMANAGER " +
            " Where Manager_Id = #{Manager_Id} And Project_Id = #{Project_Id} ")
    ProjectManager  GetProjectManagerByMP(String Manager_Id,int Project_Id);


    //多用户单项目阶段更新
    @Update(
            " UPDATE  SEPCAMP_PROJECTMANAGER SET \n"+
            "   Phase_Type = #{Phase_Type}," +
            "   Describe_Text = #{Describe_Text}" +
            "   WHERE Manager_Id = #{Manager_Id}")
    int UpdateProjectManagerForTeams(String Describe_Text,int Phase_Type,String Manager_Id);




    //单用户单作业更新
    @Update("Update SEPCAMP_PROJECTMANAGER Set " +
            " Is_Submitted = #{Is_Submitted},Text_Answer = #{Text_Answer},File_Answer = #{File_Answer}" +
            " Where Manager_Id = #{Manager_Id} And Project_Id = #{Project_Id}")
    int SubmitProjectManager(ProjectManager projectManager);

    //删除某个队伍的提交记录
    @Delete("Delete From SEPCAMP_PROJECTMANAGER  Where Manager_Id = #{Manager_Id}")
    int DeleteOneProjectManager(String Manager_Id);




}
