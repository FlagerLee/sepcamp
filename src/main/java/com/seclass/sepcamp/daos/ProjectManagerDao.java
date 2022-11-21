package com.seclass.sepcamp.daos;


import com.seclass.sepcamp.models.Homework;

import com.seclass.sepcamp.models.ProjectManager;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectManagerDao {


    //多用户单项目阶段插入
    @Insert("<script> " +
            " INSERT INTO  SEPCAMP_PROJECTMANAGER VALUES(Manager_Id, Project_Id,Team_Id, Phase_Type, Describe_Text) \n"+
            "  <foreach collection= 'list' item= 'item'  separator=','>\n" +
            "   (#{item.Manager_Id},#{item.Project_Id},#{item.Team_Id},#{item.Phase_Type},#{item.Describe_Text})\n"+
            "  </foreach> \n"+
            "</script>")
    void CreateProjectManagerForTeams(@Param(value = "list") List<ProjectManager> list);

    //单用户单作业创建
    @Insert("INSERT INTO SEPCAMP_PROJECTMANAGER(Manager_Id, Project_Id,Team_Id, Phase_Type, Describe_Text) " +
            "VALUE(#{Manager_Id},#{Project_Id},#{Team_Id}, #{Phase_Type}, #{Describe_Text})")
    int CreateOneProjectManager(ProjectManager projectManager);


    @Select("Select *" +
            "From SEPCAMP_PROJECTMANAGER" +
            "Where Team_Id = #{Team_Id}")
    List<ProjectManager>  GetProjectManagerByTeamId(int Team_Id);

    @Select("Select *" +
            "From SEPCAMP_PROJECTMANAGER" +
            "Where Phase_Type = #{Phase_Type} And Manager_Id = #{Manager_Id}")
    List<ProjectManager>  GetProjectManagerByPhaseType(int Phase_Type,int Manager_Id);

    @Select("Select *" +
            "From SEPCAMP_PROJECTMANAGER" +
            "Where Team_Id = #{TeamId} And Phase_Type = #{Phase_Type}")
    ProjectManager  GetProjectManagerByTP(int Team_Id,int Phase_Type);


    //多用户单项目阶段更新
    @Update("<script> " +
            "  <foreach collection= 'list' item= 'item'  separator=','>\n" +
            "   UPDATE  SEPCAMP_PROJECTMANAGER SET \n"+
            "   Phase_Type = #{item.Phase_Type}," +
            "   Describe_Text = #{item.Describe_Text}," +
            "   WHERE Project_Id = #{item.Project_Id} AND Team_Id = #{item.Team_Id}"+
            "  </foreach> \n"+
            "</script>")
    void UpdateProjectManagerForTeams(@Param(value = "list") List<ProjectManager> list);




    //单用户单作业更新
    @Update("Update SEPCAMP_PROJECTMANAGER Set " +
            "Is_Submitted = #{Is_Submitted},Text_Answer = #{Text_Answer},File_Answer = #{File_Answer}" +
            " Where Manager_Id = #{Manager_Id}")
    int UpdateProjectManager(ProjectManager projectManager);

    //删除某个队伍的提交记录
    @Delete("Delete From SEPCAMP_PROJECTMANAGER  Where Manager_Id = #{Manager_Id}")
    int DeleteOneProjectManager(int Manager_Id);


    //删除某个队伍的某个阶段的提交记录
    @Delete("Delete From SEPCAMP_PROJECTMANAGER  Where Manager_Id = #{Manager_Id} AND Phase_Type = #{Phase_Type}")
    int DeleteOneProjectManager(int Manager_Id,int Phase_Type);


}
