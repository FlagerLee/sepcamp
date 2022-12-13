package com.seclass.sepcamp.models;

import java.sql.Time;

public class Homework {
    String Homework_Id;
    int User_Id;
    boolean Is_Submitted;
    String Describe_Text;
    String Last_UpdateTime;
    String Start_Time;
    String End_Time ;
    String Text_Answer;
    String File_Answer;
    String Term;
    short Homework_Type;
    int Score;

    public short getHomework_Type() {
        return Homework_Type;
    }

    public void setHomework_Type(short homework_Type) {
        Homework_Type = homework_Type;
    }

    //创造新作业
    public Homework( String homework_Id,int user_Id, String describe_Text, String start_Time, String end_Time,String term, short homework_Type) {
        Homework_Id = homework_Id;
        User_Id = user_Id;
        Describe_Text = describe_Text;
        Start_Time = start_Time;
        End_Time = end_Time;
        Term = term;
        Homework_Type = homework_Type;
    }

    public Homework( String homework_Id, String describe_Text, String start_Time, String end_Time) {
        Homework_Id = homework_Id;
        Describe_Text = describe_Text;
        Start_Time = start_Time;
        End_Time = end_Time;
    }

    public Homework(int user_Id, String describe_Text, String start_Time, String end_Time) {
        User_Id = user_Id;
        Describe_Text = describe_Text;
        Start_Time = start_Time;
        End_Time = end_Time;
    }

    //提交、修改作业
    public Homework(String homework_Id,int user_Id,boolean is_Submitted,String text_Answer, String file_Answer) {
        Is_Submitted = is_Submitted;
        User_Id = user_Id;
        Homework_Id = homework_Id;
        Text_Answer = text_Answer;
        File_Answer = file_Answer;
    }


    public Homework() {
    }

    public Homework(String homework_Id, int user_Id, boolean is_Submitted, String describe_Text, String last_UpdateTime, String start_Time, String end_Time, String text_Answer, String file_Answer) {
        Homework_Id = homework_Id;
        User_Id = user_Id;
        Is_Submitted = is_Submitted;
        Describe_Text = describe_Text;
        Last_UpdateTime = last_UpdateTime;
        Start_Time = start_Time;
        End_Time = end_Time;
        Text_Answer = text_Answer;
        File_Answer = file_Answer;
    }




    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getHomework_Id() {
        return Homework_Id;
    }

    public void setHomework_Id(String homework_Id) {
        Homework_Id = homework_Id;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public boolean isIs_Submitted() {
        return Is_Submitted;
    }

    public void setIs_Submitted(boolean is_Submitted) {
        Is_Submitted = is_Submitted;
    }

    public String getDescribe_Text() {
        return Describe_Text;
    }

    public void setDescribe_Text(String describe_Text) {
        Describe_Text = describe_Text;
    }

    public String getLast_UpdateTime() {
        return Last_UpdateTime;
    }

    public void setLast_UpdateTime(String last_UpdateTime) {
        Last_UpdateTime = last_UpdateTime;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(String start_Time) {
        Start_Time = start_Time;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String end_Time) {
        End_Time = end_Time;
    }

    public String getText_Answer() {
        return Text_Answer;
    }

    public void setText_Answer(String text_Answer) {
        Text_Answer = text_Answer;
    }

    public String getFile_Answer() {
        return File_Answer;
    }

    public void setFile_Answer(String file_Answer) {
        File_Answer = file_Answer;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
