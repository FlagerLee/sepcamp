package com.seclass.sepcamp.models;

public class Team {
    int Team_id;
    String Team_name;
    int Leader;
    int Project_id;
    String Term;
    String Introduction;

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

    @Override
    public String toString() {
        return "Team{" +
                "TeamId=" + Team_id +
                ", TeamName='" + Team_name + '\'' +
                ", Leader=" + Leader +
                ", ProjectId=" + Project_id +
                ", Term='" + Term + '\'' +
                '}';
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

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }
}
