package com.seclass.sepcamp.models;

public class Project {
    int Project_Id;
    String Introduction;
    int Visible;
    String Term;

    public Project(String introduction, int visible, String term) {
        Introduction = introduction;
        Visible = visible;
        Term = term;
    }

    public Project(int project_Id ,String introduction, int visible, String term) {
        Project_Id = project_Id;
        Introduction = introduction;
        Visible = visible;
        Term = term;
    }

    public int getProject_Id() {
        return Project_Id;
    }

    public void setProject_Id(int project_Id) {
        Project_Id = project_Id;
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

    @Override
    public String toString() {
        return "Project{" +
                "project_Id=" + Project_Id +
                ", Introduction='" + Introduction + '\'' +
                ", Visible=" + Visible +
                ", term='" + Term + '\'' +
                '}';
    }
}
