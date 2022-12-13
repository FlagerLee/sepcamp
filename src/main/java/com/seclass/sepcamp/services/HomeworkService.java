package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.HomeworkDao;
import com.seclass.sepcamp.daos.UserDao;
import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private UserDao userDao;

    public Response CreateHomeworkForUsers(String DescribeText, long StartTime, long EndTime, String Term, short HomeworkType){

        if(DescribeText.length() <= 0 || DescribeText.length() >= 1000){
            return new Response("描述文本长度不符合要求，应在1~1000个字符之间",false);
        }
        if(Term.length() <= 0 || Term.length() > 5){
            return new Response("学期格式不符合要求",false);
        }

        //TODO 统一term格式
       // String thisTerm = "20222";
       // thisTerm = Term;
        List<User> userList = userDao.getUserByTerm(Term);
        ArrayList<Homework> homeworkList = new ArrayList<Homework>();
        long currentTime = System.currentTimeMillis();
        for(int i = 0 ;i < userList.size();i++){

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
            String startTimeStr = df.format(new Timestamp(StartTime));//获取开始时间时间戳
            String endTimeStr =  df.format(new Timestamp(EndTime));//获取结束时间时间戳

            Homework temp = new Homework(df.format(currentTime),userList.get(i).getUser_id(),DescribeText,startTimeStr,endTimeStr,Term,HomeworkType);
            homeworkList.add(temp);
        }

        boolean createHomeworkSuccess = homeworkDao.CreateHomeworkForUsers(homeworkList) >  0;

        return ResponseUtils.ResponseMaker(createHomeworkSuccess,"创建作业成功","创建作业失败");

    }

    public Response DeleteHomework(String HomeworkId) {
        boolean deleteSuccess =  homeworkDao.DeleteOneHomework(HomeworkId) > 0;
        return ResponseUtils.ResponseMaker(deleteSuccess,"删除作业成功","删除作业失败");

    }


    public Response UpdateHomework(String DescribeText,long StartTime,long EndTime,String HomeworkId) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
        String startTimeStr = df.format(new Timestamp(StartTime));//获取开始时间时间戳
        String endTimeStr =  df.format(new Timestamp(EndTime));//获取结束时间时间戳
        boolean updateSuccess = homeworkDao.UpdateHomeworkForUsers(new Homework(HomeworkId, DescribeText, startTimeStr, endTimeStr)) > 0 ;
        return ResponseUtils.ResponseMaker(updateSuccess,"更新作业成功","更新作业失败");

    }


    public Response SubmitHomeworkByUser(String HomeworkId, int UserId, boolean IsSubmitted, String TextAnswer, String FileAnswer) {

        boolean updateHomeworkSuccess = homeworkDao.UpdateHomework(new Homework(HomeworkId, UserId, IsSubmitted, TextAnswer, FileAnswer)) > 0;
        return ResponseUtils.ResponseMaker(updateHomeworkSuccess,"提交作业成功","提交作业失败");

    }


    public List<Homework> GetOneHomeworkList(String HomeworkId) {
        return homeworkDao.GetHomeworkByHomeworkId(HomeworkId);
    }

    public List<Homework> GetAllHomeworkList(String term) {
        return homeworkDao.GetHomeworkByTerm(term);
    }

    public Homework GetOneHomework(String HomeworkId,int UserId) {
        return homeworkDao.GetHomeworkByHU(HomeworkId,UserId);
    }


}
