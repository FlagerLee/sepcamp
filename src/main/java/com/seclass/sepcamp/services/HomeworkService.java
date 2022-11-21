package com.seclass.sepcamp.services;

import com.seclass.sepcamp.daos.HomeworkDao;
import com.seclass.sepcamp.daos.UserDao;
import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.ResponseCreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkDao homeworkDao;
    @Autowired
    private UserDao userDao;

    public ResponseCreater CreateHomeworkForUsers(String DescribeText,String StartTime,String EndTime,String Term){

        return new ResponseCreater();
    }

    public ResponseCreater DeleteHomework(String HomeworkId) {


        return new ResponseCreater();
    }


    public ResponseCreater UpdateHomework(String DescribeText,String StartTime,String EndTime,String HomeworkId) {

        return new ResponseCreater();
    }


    public ResponseCreater SubmitHomeworkByUser(String HomeworkId,int UserId,boolean IsSubmitted,String TextAnswer,String FileAnswer) {

        return new ResponseCreater();
    }

    public List<Homework> GetOneHomeworkList(String HomeworkId) {

        return null;
    }

    public List<Homework> GetAllHomeworkList(String term) {
        return homeworkDao.GetHomeworkByTerm(term);
    }

    public Homework GetOneHomework(String HomeworkId,int UserId) {
        return homeworkDao.GetHomeworkByHU(HomeworkId,UserId);
    }


}
