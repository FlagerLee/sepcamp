package com.seclass.sepcamp.models;

public class Team {
    int Team_id;
    String Team_name;
    int Leader;
    int Project_id;
    String Term;
    String Introduction;
    String Interests;
    String QQNumber;

    public Team(int teamId, String teamName, int leader, int projectId, String term) {
        Team_id = teamId;
        Team_name = teamName;
        Leader = leader;
        Project_id = projectId;
        Term = term;
    }

    public Team(String teamName, int leader, String term) {
        Team_name = teamName;
        Leader = leader;
        Term = term;
    }

    public Team(String teamName, int leader, int projectId, String term) {
        Team_name = teamName;
        Leader = leader;
        Project_id = projectId;
        Term = term;
    }

    public Team() {
        Team_id = 0;
        Team_name = "";
        Leader = 0;
        Project_id = 0;
        Term = "";
    }

    public Team(String team_name, int leader, int project_id, String term, String interests, String introduction) {
        Team_name = team_name;
        Leader = leader;
        Project_id = project_id;
        Term = term;
        Interests = interests;
        Introduction = introduction;
    }

    public Team(String team_name, int leader, int project_id, String term, String introduction, String interests, String QQNumber) {
        Team_name = team_name;
        Leader = leader;
        Project_id = project_id;
        Term = term;
        Introduction = introduction;
        Interests = interests;
        this.QQNumber = QQNumber;
    }


    public String getQQNumber() {
        return QQNumber;
    }

    public void setQQNumber(String QQNumber) {
        this.QQNumber = QQNumber;
    }

    public String getInterests() {
        return Interests;
    }

    public void setInterests(String interests) {
        Interests = interests;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public int getTeam_id() {
        return Team_id;
    }

    public void setTeam_id(int team_id) {
        Team_id = team_id;
    }

    public String getTeam_name() {
        return Team_name;
    }

    public void setTeam_name(String team_name) {
        Team_name = team_name;
    }

    public int getLeader() {
        return Leader;
    }

    public void setLeader(int leader) {
        Leader = leader;
    }

    public int getProject_id() {
        return Project_id;
    }

    public void setProject_id(int project_id) {
        Project_id = project_id;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

}
