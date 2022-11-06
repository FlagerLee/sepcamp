package com.seclass.sepcamp.models;

public class Team {
    int TeamId;
    String TeamName;
    int Leader;
    int ProjectId;
    String Term;

    public Team(int teamId, String teamName, int leader, int projectId, String term) {
        TeamId = teamId;
        TeamName = teamName;
        Leader = leader;
        ProjectId = projectId;
        Term = term;
    }

    public Team(String teamName, int leader, int projectId, String term) {
        TeamName = teamName;
        Leader = leader;
        ProjectId = projectId;
        Term = term;
    }

    @Override
    public String toString() {
        return "Team{" +
                "TeamId=" + TeamId +
                ", TeamName='" + TeamName + '\'' +
                ", Leader=" + Leader +
                ", ProjectId=" + ProjectId +
                ", Term='" + Term + '\'' +
                '}';
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public int getLeader() {
        return Leader;
    }

    public void setLeader(int leader) {
        Leader = leader;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }
}
