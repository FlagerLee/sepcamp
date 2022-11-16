package com.seclass.sepcamp.models;

public class ProjectManager {

    int Manager_Id;
    int Project_Id;
    int Phase_Type;
    boolean Is_Submitted;
    String Describe_Text;
    String Last_UpdateTime;
    String Text_Answer;
    String File_Answer;

    //创建阶段
    public ProjectManager(int project_Id, int phase_Type, String describe_Text) {
        Project_Id = project_Id;
        Phase_Type = phase_Type;
        Describe_Text = describe_Text;
    }
    //修改阶段
    public ProjectManager(int manager_Id, boolean is_Submitted, String text_Answer, String file_Answer) {
        Manager_Id = manager_Id;
        Is_Submitted = is_Submitted;
        Text_Answer = text_Answer;
        File_Answer = file_Answer;
    }

    public ProjectManager(int manager_Id, int project_Id, int phase_Type, boolean is_Submitted, String describe_Text, String last_UpdateTime, String text_Answer, String file_Answer) {
        Manager_Id = manager_Id;
        Project_Id = project_Id;
        Phase_Type = phase_Type;
        Is_Submitted = is_Submitted;
        Describe_Text = describe_Text;
        Last_UpdateTime = last_UpdateTime;
        Text_Answer = text_Answer;
        File_Answer = file_Answer;
    }

    public ProjectManager() {
    }

    @Override
    public String toString() {
        return "ProjectManagerDao{" +
                "Manager_Id=" + Manager_Id +
                ", Project_Id=" + Project_Id +
                ", Phase_Type=" + Phase_Type +
                ", Is_Submitted=" + Is_Submitted +
                ", Describe_Text='" + Describe_Text + '\'' +
                ", Last_UpdateTime='" + Last_UpdateTime + '\'' +
                ", Text_Answer='" + Text_Answer + '\'' +
                ", File_Answer='" + File_Answer + '\'' +
                '}';
    }

    public int getManager_Id() {
        return Manager_Id;
    }

    public void setManager_Id(int manager_Id) {
        Manager_Id = manager_Id;
    }

    public int getProject_Id() {
        return Project_Id;
    }

    public void setProject_Id(int project_Id) {
        Project_Id = project_Id;
    }

    public int getPhase_Type() {
        return Phase_Type;
    }

    public void setPhase_Type(int phase_Type) {
        Phase_Type = phase_Type;
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
}
