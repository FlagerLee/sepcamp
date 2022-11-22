package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.ResponseCreater;
import com.seclass.sepcamp.models.Team;
import com.seclass.sepcamp.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    public HomeworkService homeworkService;

    @PostMapping("/CreateHomework")
    public ResponseCreater CreateHomework(String DescribeText,long StartTime,long EndTime,String Term) {
        return homeworkService.CreateHomeworkForUsers(DescribeText,StartTime,EndTime,Term);
    }

    @PostMapping("/DeleteHomework")
    public void DeleteHomework(String HomeworkId) {
        homeworkService.DeleteHomework(HomeworkId);
    }

    @PostMapping("/UpdateHomework")
    public void UpdateHomework(String DescribeText,String StartTime,String EndTime,String HomeworkId) {
        homeworkService.UpdateHomework(DescribeText,StartTime,EndTime,HomeworkId);
    }
    @PostMapping("/SubmitHomework")
    public ResponseCreater SubmitHomework(String HomeworkId,int UserId,boolean IsSubmitted,String TextAnswer,String FileAnswer) {
        return homeworkService.SubmitHomeworkByUser(HomeworkId,UserId,IsSubmitted,TextAnswer,FileAnswer);
    }
    @PostMapping("/GetOneHomeworkList")
    public List<Homework> GetOneHomeworkList(String HomeworkId) {
        return homeworkService.GetOneHomeworkList(HomeworkId);
    }
    @PostMapping("/GetAllHomeworkList")
    public List<Homework> GetAllHomeworkList(String term) {
        return homeworkService.GetAllHomeworkList(term);
    }
    @PostMapping("/GetOneHomework")
    public Homework GetOneHomework(String HomeworkId,int UserId) {
        return homeworkService.GetOneHomework(HomeworkId,UserId);
    }


}
