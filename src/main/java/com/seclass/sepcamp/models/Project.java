package com.seclass.sepcamp.models;

public class Project {
    int Project_id;
    String Project_name;
    String Introduction;
    int Visible;
    String Term;

    public Project(String introduction, String name,int visible, String term) {
        Introduction = introduction;
        Project_name = name;
        Visible = visible;
        Term = term;
    }

    public Project(int project_id) {
        Project_id = project_id;
    }

    public Project(int project_id, String introduction, String name, int visible, String term) {
        Project_id = project_id;
        Introduction = introduction;
        Project_name = name;
        Visible = visible;
        Term = term;
    }

    public int getProject_id() {
        return Project_id;
    }

    public void setProject_id(int project_id) {
        Project_id = project_id;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public int getVisible() {
        return Visible;
    }

    public void setVisible(int visible) {
        Visible = visible;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getProject_name() {
        return Project_name;
    }

    public void setProject_name(String project_name) {
        Project_name = project_name;
    }


}
