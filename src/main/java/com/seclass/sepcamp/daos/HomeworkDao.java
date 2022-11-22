package com.seclass.sepcamp.daos;

import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.Project;
import com.seclass.sepcamp.models.ProjectManager;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeworkDao {


    //多用户单作业插入
    @Insert("<script> " +
            "INSERT INTO  SEPCAMP_HOMEWORK VALUES(Homework_Id,User_Id, Describe_Text, Start_Time,End_Time) \n"+
            "  <foreach collection= 'list' item= 'item'  separator=','>\n" +
            "(#{item.Homework_Id},#{item.User_Id},#{item.Describe_Text},#{item.Start_Time},#{item.End_Time})\n"+
            "</foreach> \n"+
            "</script>")
    int CreateHomeworkForUsers(@Param(value = "list") List<Homework> list);

    //单用户单作业创建
    @Insert("INSERT INTO SEPCAMP_HOMEWORK(Homework_Id,User_Id, Describe_Text, Start_Time,End_Time) " +
            "VALUE(#{Homework_Id},#{User_Id}, #{Describe_Text}, #{Start_Time}, #{End_Time})")
    int CreateOneHomework(Homework homework);

    @Select("Select *" +
            "From SEPCAMP_HOMEWORK" +
            "Where User_Id = #{User_Id}")
    List<Homework>  GetHomeworkByTeamId(int User_Id);

    @Select("Select *" +
            "From SEPCAMP_HOMEWORK" +
            "Where Homework_Id = #{Homework_Id},User_Id = #{User_Id} ")
    Homework  GetHomeworkByHU(String Homework_Id,int User_Id);

    @Select("Select *" +
            "From SEPCAMP_HOMEWORK" +
            "Where Homework_Id = #{Homework_Id}")
    List<Homework>  GetHomeworkByHomeworkId(String Homework_Id);

    @Select("Select Distinct(Homework_Id)" +
            "From SEPCAMP_HOMEWORK" +
            "Where Term = #{Term}")
    List<Homework>  GetHomeworkByTerm(String Term);


    //多用户单作业更新
    @Update("Update SEPCAMP_HOMEWORK SET " +
            "   Describe_Text = #{homework.Describe_Text}," +
            "   Start_Time = #{homework.Start_Time}," +
            "   End_Time = #{homework.End_Time}\n"+
            "   WHERE Homework_Id = #{homework.Homework_Id}")
    void UpdateHomeworkForUsers(Homework homework);



    //单用户单作业更新
    @Update("Update SEPCAMP_HOMEWORK Set " +
            "Is_Submitted = #{Is_Submitted},Text_Answer = #{Text_Answer},File_Answer = #{File_Answer}" +
            " Where Homework_Id = #{Homework_Id} and User_Id = #{User_Id}")
    int UpdateHomework(Homework homework);

    //删除所有用户的某个作业
    @Delete("Delete From SEPCAMP_HOMEWORK  Where Homework_Id = #{Homework_Id}")
    int DeleteOneHomework(String Homework_Id);

}
