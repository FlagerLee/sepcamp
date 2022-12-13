package com.seclass.sepcamp.controllers;

import com.seclass.sepcamp.models.Homework;
import com.seclass.sepcamp.models.Response;
import com.seclass.sepcamp.models.User;
import com.seclass.sepcamp.services.HomeworkService;
import com.seclass.sepcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    public HomeworkService homeworkService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    //public Response CreateHomework(String DescribeText,long StartTime,long EndTime,String Term,short HomeworkType) {
    //    return homeworkService.CreateHomeworkForUsers(DescribeText,StartTime,EndTime,Term,HomeworkType);
    //}
    public Response CreateHomework(@RequestBody Homework homework) {
        return homeworkService.CreateHomeworkForUsers(
                homework.getDescribe_Text(),
                Long.parseLong(homework.getStart_Time()),
                Long.parseLong(homework.getEnd_Time()),
                homework.getTerm(),
                homework.getHomework_Type()
        );
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
    public Response SubmitHomework(@RequestBody Homework homework, Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        return homeworkService.SubmitHomeworkByUser(
                homework.getHomework_Id(),
                user.getUser_id(),
                homework.getText_Answer(),
                homework.getFile_Answer()
        );
    }
    @PostMapping("/getOneList")
    public List<Homework> GetOneHomeworkListById(@RequestBody Homework homework) {
        return homeworkService.GetOneHomeworkListById(homework.getHomework_Id());
    }
    @PostMapping("/getAllList")
    public List<Homework> GetAllHomeworkList(@RequestBody Homework homework) {
        return homeworkService.GetAllHomeworkList(homework.getTerm());
    }
    @PostMapping("/getOne")
    public Homework GetOneHomework(@RequestBody Homework homework, Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        return homeworkService.GetOneHomework(homework.getHomework_Id(), user.getUser_id());
    }
    @PostMapping("/getOneWithUser")
    public Homework GetOneHomeworkWithUser(@RequestBody Homework homework) {
        return homeworkService.GetOneHomework(homework.getHomework_Id(), homework.getUser_Id());
    }
    //public Homework GetOneHomework(String HomeworkId,int UserId) {
    //    return homeworkService.GetOneHomework(HomeworkId,UserId);
    //}

    @PostMapping("/getOneUserList")
    public List<Homework> GetOneHomeworkList(Principal principal) {
        String username = principal.getName();
        User user = (User) userService.loadUserByUsername(username);
        return homeworkService.GetOneHomeworkList(user.getUser_id());
    }

    @PostMapping("/updateScore")
    public Response UpdateScore(@RequestBody Homework homework) {
        return homeworkService.UpdateScore(homework.getHomework_Id(), homework.getUser_Id(), homework.getScore());
    }
}
