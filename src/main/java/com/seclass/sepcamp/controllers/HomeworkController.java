package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.Response;
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

    @PostMapping("/create")
    public Response CreateHomework(String DescribeText, long StartTime, long EndTime, String Term) {
        return homeworkService.CreateHomeworkForUsers(DescribeText,StartTime,EndTime,Term);
    }

    @PostMapping("/delete")
    public Response DeleteHomework(String HomeworkId) {
        return homeworkService.DeleteHomework(HomeworkId);
    }

    @PostMapping("/update")
    public Response UpdateHomework(String DescribeText,long StartTime,long EndTime,String HomeworkId) {
        return homeworkService.UpdateHomework(DescribeText,StartTime,EndTime,HomeworkId);
    }
    @PostMapping("/submit")
    public Response SubmitHomework(String HomeworkId, int UserId, boolean IsSubmitted, String TextAnswer, String FileAnswer) {
        return homeworkService.SubmitHomeworkByUser(HomeworkId,UserId,IsSubmitted,TextAnswer,FileAnswer);
    }
    @PostMapping("/getOneList")
    public List<Homework> GetOneHomeworkList(String HomeworkId) {
        return homeworkService.GetOneHomeworkList(HomeworkId);
    }
    @PostMapping("/getAllList")
    public List<Homework> GetAllHomeworkList(String term) {
        return homeworkService.GetAllHomeworkList(term);
    }
    @PostMapping("/getOne")
    public Homework GetOneHomework(String HomeworkId,int UserId) {
        return homeworkService.GetOneHomework(HomeworkId,UserId);
    }


}
