package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.TeamMapper;
import com.seclass.sepcamp.daos.UserDao;
import com.seclass.sepcamp.models.ResponseCreater;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.models.User;
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
       // return new Team(1,"123",1,1,"321");
        return teamMapper.GetOneTeam(teamId);
    }

    public List<Team> GetAllTeams(){
        // return new Team(1,"123",1,1,"321");
        return teamMapper.GetAllTeams();
    }
    public synchronized ResponseCreater AddTeam(Team t){
        //先查询该用户是否具有队伍
        User user = userDao.getUserByUserId(t.getLeader());
        if(user == null){
            return new ResponseCreater("该用户不存在",false);
        }

        //TODO 会有并发问题，同一用户同一时间进行两支队伍的创建,暂时通过synchronized解决
        if(user.getTeam_id() != 0){
            //表示该用户已经有队伍
            return new ResponseCreater("该用户已经在一支队伍中，无法创建新队伍",false);
        }

        //check parameter
        if(t.getTeamName().length() > 20 || t.getTeamName().length() <= 0 ){
            return new ResponseCreater("队伍名字长度不符合要求，请限制在1 ~ 20个字符",false);
        }  else  if(t.getTerm().length() > 5 || t.getTerm().length() <= 0 ){
            return new ResponseCreater("队伍学期标识长度不符合要求，请限制在1 ~ 5个字符",false);
        }


        Boolean createResult = teamMapper.CreateTeam(t) > 0;
        if(!createResult){
            return new ResponseCreater("创建队伍失败",false);
        }
        //通过teamname 反查team id
        boolean updateUserSuccess = false;
        List<Team> teamLists  = teamMapper.GetTeamByTeamname(t.getTeamName());
        for(int i = 0 ;i < teamLists.size();i++){
            Team temp = teamLists.get(i);
            if (temp.getLeader() == t.getLeader()){
                //找到了刚刚创建的这个team
                updateUserSuccess = userDao.updateTeamIdForUser(temp.getLeader(), temp.getTeamId()) > 0;
                break;
            }

        }

        if(updateUserSuccess){
            return new ResponseCreater("创建队伍成功",true);
        }else{
            //TODO 创建队伍和修改用户之间宕机会出现问题
            return new ResponseCreater("创建队伍失败",true);
        }

    }

    public ResponseCreater UpdateTeamNameById(String teamName,int teamId) {
        boolean updateSuccess = teamMapper.UpdateTeamNameById(teamName, teamId) > 0;
        if(updateSuccess){
            return new ResponseCreater("修改队伍Leader成功",true);
        }else{
            return new ResponseCreater("修改队伍Leader失败",false);
        }
    }

    public ResponseCreater UpdateTeamLeaderById(int leaderId,int teamId) {

        boolean updateSuccess = teamMapper.UpdateTeamLeaderById(leaderId, teamId) > 0;
        if(updateSuccess){
            return new ResponseCreater("修改队伍Leader成功",true);
        }else{
            return new ResponseCreater("修改队伍Leader失败",false);
        }
    }

    public ResponseCreater UpdateTeamProjectById(int  projectId,int teamId) {

        boolean updateSuccess =  teamMapper.UpdateTeamProjectById(projectId, teamId) > 0;
        if(updateSuccess){
            return new ResponseCreater("修改队伍项目成功",true);
        }else{
            return new ResponseCreater("修改队伍项目失败",false);
        }
    }

    public ResponseCreater DropOutOfLine(int userId) {

        User thisUser = userDao.getUserByUserId(userId);
        int teamId = 0 ;
        if(thisUser == null){
            return new ResponseCreater("请求用户不存在",false);
        }else{
            teamId = thisUser.getTeam_id();
        }
        boolean updateSuccess =  userDao.updateTeamIdForUser(userId,0) > 0;
        if(updateSuccess){
            //若此队伍中没有人，删除此队伍
            List<User> ThisTeamMembers = userDao.getUserByTeamId(teamId);
            if (ThisTeamMembers.size() == 0){
                teamMapper.DeleteOneTeam(teamId);
            }
            return new ResponseCreater("退出队伍成功",true);
        }else{
            return new ResponseCreater("退出队伍失败",false);
        }
    }



}
