package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeworkDao {



    @Insert("INSERT INTO SEPCAMP_HOMEWORK(Homework_Id,User_Id, Describe_Text, Start_Time,End_Time) " +
            "VALUE(#{Homework_Id},#{User_Id}, #{Describe_Text}, #{Start_Time}, #{End_Time})")
    int CreateOneHomework(Homework homework);

    @Update("Update SEPCAMP_HOMEWORK Set Introduction = #{Introduction}," +
            "Is_Submitted = #{Is_Submitted},Text_Answer = #{Text_Answer},File_Answer = #{File_Answer}" +
            " Where Homework_Id = #{Homework_Id} and User_Id = #{User_Id}")
    int UpdateHomework(Homework homework);


    @Delete("Delete From SEPCAMP_HOMEWORK  Where Homework_Id = #{Homework_Id} and User_Id = #{User_Id}")
    int DeleteOneHomework(int Homework_Id);



    @Select("Select Project_Id,Introduction,Visible,Term" +
            "From SEPCAMP_PROJECT" +
            "Where Visible = #{Visible}")
    List<Project> GetProjectList(int Visible);

    @Select("Select Project_Id,Introduction,Visible,Term" +
            "From SEPCAMP_PROJECT" +
            "Where Project_Id = #{Project_Id}")
    Project  GetProjectById(int Project_Id);



    @Update("Update Sepcamp_project Set Visible = #{Visible} Where Project_Id = #{Project_Id}")
    int UpdateVisibleByProjectId(int Project_Id,int Visible);

    @Update("Update Sepcamp_project Set Term = #{Term} Where Project_Id = #{Project_Id}")
    int UpdateTermByProjectId(int Project_Id,String Term);


}
