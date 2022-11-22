package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.HomeworkDao;
import com.seclass.sepcamp.daos.UserDao;
import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.ResponseCreater;
import com.seclass.sepcamp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

    public ResponseCreater CreateHomeworkForUsers(String DescribeText,long StartTime,long EndTime,String Term){

        if(DescribeText.length() <= 0 || DescribeText.length() >= 1000){
            return new ResponseCreater("描述文本长度不符合要求，应在1~1000个字符之间",false);
        }
        if(Term.length() <= 0 || Term.length() > 5){
            return new ResponseCreater("学期格式不符合要求",false);
        }

        //TODO 统一term格式
       // String thisTerm = "20222";
       // thisTerm = Term;
        List<User> userList = userDao.getUserByTerm(Term);
        ArrayList<Homework> homeworkList = new ArrayList<Homework>();
        for(int i = 0 ;i < userList.size();i++){

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
            String startTimeStr = df.format(new Timestamp(StartTime));//获取开始时间时间戳
            String endTimeStr =  df.format(new Timestamp(EndTime));//获取结束时间时间戳

            Homework temp = new Homework(String.valueOf(System.currentTimeMillis()),userList.get(i).getUser_id(),DescribeText,startTimeStr,endTimeStr,Term);
            homeworkList.add(temp);
        }

        boolean createHomeworkSuccess = homeworkDao.CreateHomeworkForUsers(homeworkList) >  0;
        if(createHomeworkSuccess){
            return new ResponseCreater("提交作业成功",true);
        }else{
            return new ResponseCreater("提交作业失败",true);
        }
    }

    public void DeleteHomework(String HomeworkId) {
        homeworkDao.DeleteOneHomework(HomeworkId);
    }


    public void UpdateHomework(String DescribeText,String StartTime,String EndTime,String HomeworkId) {

         homeworkDao.UpdateHomeworkForUsers(new Homework(HomeworkId, DescribeText, StartTime, EndTime));

    }


    public ResponseCreater SubmitHomeworkByUser(String HomeworkId,int UserId,boolean IsSubmitted,String TextAnswer,String FileAnswer) {

        boolean updateHomeworkSuccess = homeworkDao.UpdateHomework(new Homework(HomeworkId, UserId, IsSubmitted, TextAnswer, FileAnswer)) > 0;

        if(updateHomeworkSuccess){
            return new ResponseCreater("提交作业成功",true);
        }else{
            return new ResponseCreater("提交作业失败",true);
        }

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
