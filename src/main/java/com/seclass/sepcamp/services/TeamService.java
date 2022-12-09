package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.TeamMapper;
import com.seclass.sepcamp.daos.UserDao;
import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private UserDao userDao;
    public Team GetTeamById(int teamId){

        return teamMapper.GetOneTeam(teamId);
    }

    public List<Team> GetAllTeams(){

        return teamMapper.GetAllTeams();
    }
    public synchronized Response CreateTeam(Team t){
        //先查询该用户是否具有队伍
        User user = userDao.getUserByUserId(t.getLeader());
        if(user == null){
            return new Response("该用户不存在",false);
        }

        //TODO 会有并发问题，同一用户同一时间进行两支队伍的创建,暂时通过synchronized解决
        if(user.getTeam_id() != 0){
            //表示该用户已经有队伍
            System.out.println(user.toString());
            return new Response("该用户已经在一支队伍中，无法创建新队伍",false);
        }

        System.out.println(t);
        //check parameter
        if(t.getTeam_name().length() > 20 || t.getTeam_name().length() <= 0 ){
            return new Response("队伍名字长度不符合要求，请限制在1 ~ 20个字符",false);
        }  else  if(t.getTerm().length() > 5 || t.getTerm().length() <= 0 ){
            return new Response("队伍学期标识长度不符合要求，请限制在1 ~ 5个字符",false);
        }


        Boolean createResult = teamMapper.CreateTeam(t) > 0;
        if(!createResult){
            return new Response("创建队伍失败",false);
        }
        //通过teamname 反查team id
        boolean updateUserSuccess = false;
        List<Team> teamLists  = teamMapper.GetTeamByTeamname(t.getTeam_name());
        System.out.println(teamLists);
        for(int i = 0 ;i < teamLists.size();i++){
            Team temp = teamLists.get(i);
            if (temp.getLeader() == t.getLeader()){
                //找到了刚刚创建的这个team
                updateUserSuccess = userDao.updateTeamIdForUser(temp.getLeader(), temp.getTeam_id()) > 0;
                break;
            }

        }

        return ResponseUtils.ResponseMaker(updateUserSuccess,"创建队伍成功","创建队伍失败");

    }

    public Response UpdateTeamNameById(String teamName, int teamId) {
        boolean updateSuccess = teamMapper.UpdateTeamNameById(teamName, teamId) > 0;
        return ResponseUtils.ResponseMaker(updateSuccess,"修改队伍名字成功","修改队伍名字失败");
    }

    public Response UpdateTeamLeaderById(int leaderId, int teamId) {

        boolean updateSuccess = teamMapper.UpdateTeamLeaderById(leaderId, teamId) > 0;
        return ResponseUtils.ResponseMaker(updateSuccess,"修改队伍Leader成功","修改队伍Leader失败");
    }

    public Response UpdateTeamProjectById(int  projectId, int teamId) {

        boolean updateSuccess =  teamMapper.UpdateTeamProjectById(projectId, teamId) > 0;
        return ResponseUtils.ResponseMaker(updateSuccess,"修改队伍项目成功","修改队伍项目失败");
    }

    public Response DropOutOfLine(int userId) {

        User thisUser = userDao.getUserByUserId(userId);
        int teamId = 0 ;
        if(thisUser == null){
            return new Response("请求用户不存在",false);
        }else if(thisUser.getTeam_id() == 0 ){
            return new Response("用户不在队伍中",false);
        }
        else {
            teamId = thisUser.getTeam_id();
        }
        boolean updateSuccess =  userDao.updateTeamIdForUser(userId,0) > 0;
        if(updateSuccess){
            //若此队伍中没有人，删除此队伍
            List<User> ThisTeamMembers = userDao.getUserByTeamId(teamId);
            if (ThisTeamMembers.size() == 0){
                teamMapper.DeleteOneTeam(teamId);
            }
            return new Response("退出队伍成功",true);
        }else{
            return new Response("退出队伍失败",false);
        }
    }



}
