package com.ylf.manage.controller;

import com.ylf.manage.entity.Course;
import com.ylf.manage.entity.Uc_itemKey;
import com.ylf.manage.entity.User;
import com.ylf.manage.entity.ZFile;
import com.ylf.manage.iservice.Course_iserv;
import com.ylf.manage.iservice.Score_iserv;
import com.ylf.manage.iservice.UC_item_iserv;
import com.ylf.manage.util.ImgFile;
import com.ylf.manage.util.UUID;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;


@Controller
public class CourseController {
    @Autowired
    private Course_iserv service;
    @Autowired
    private UC_item_iserv ucService;



    @RequestMapping("/course/add")
    @ResponseBody
    @CrossOrigin
    public JSONObject addCourse(ZFile zFile) {        //添加一门课程
        Course course=new Course();
        if (zFile.getUser()!= null) {                              //img课程封面图，user用户id//course包含课程名、班级
            course.setcName(zFile.getcName());
            course.setcClass(zFile.getcClass());
            if (zFile.getcImg()!= null) {
                course.setcImg(ImgFile.save(zFile.getcImg()));
            }
            else {
                course.setcImg("http://47.102.195.147/img/base.png");
            }
                course.setcId(UUID.getCode());
                int n = service.add(course,zFile.getUser());
                if (n > 1) {
                     JSONObject msg=new JSONObject();
                     msg.put("msg","1");
                     return msg;
                } else {
                    JSONObject msg=new JSONObject();
                    msg.put("msg","添加班课失败");
                    return msg;
                }
            }
         JSONObject msg=new JSONObject();
         msg.put("msg","添加班课失败");
         return msg;
        }

        @RequestMapping("/course/user")
        @ResponseBody
        @CrossOrigin
        public List<User> getUser(String c_id){            //返回参加这门课程的成员
         if(c_id!=null){                                   //c_id课程id
             List<User> list=service.findAllJoinUser(c_id);
             return list;
         }
          return null;
        }

        @RequestMapping("/course/del")
        @ResponseBody
        @CrossOrigin
        public JSONObject delCourse(String c_id){                //删除一门课程
           if(c_id!=null){                                       //c_id课程id
               int n=service.del(c_id);
               if(n>0){
                   JSONObject msg=new JSONObject();
                   msg.put("msg","1");
                   return msg;
               }
               else{
                   JSONObject msg=new JSONObject();
                   msg.put("msg","删除失败");
                   return msg;
               }
           }
            JSONObject msg=new JSONObject();
            msg.put("msg","删除失败");
            return msg;
        }

        @RequestMapping("/course/back")
        @ResponseBody
        @CrossOrigin
        public JSONObject backCourse(String c_id, String user){    //用户退课
        if(c_id!=null){                                            //c_id课程id，user用户id
            Uc_itemKey uc=new Uc_itemKey();
            uc.setuEmail(user);
            uc.setcId(c_id);
            int n=ucService.del(uc);
            if(n>0){
                JSONObject msg=new JSONObject();
                msg.put("msg","1");
                return msg;
            }
            else{
                JSONObject msg=new JSONObject();
                msg.put("msg","退课失败");
                return msg;
            }
        }
            JSONObject msg=new JSONObject();
            msg.put("msg","退课失败");
            return msg;
        }

        @RequestMapping("/course/get/one")
        @ResponseBody
        @CrossOrigin
        public Course getOne(String c_id){
            return service.getOne(c_id);
        }






}
