package com.ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.model.People;
import com.ssm.model.User;
import com.ssm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {


    @Resource(name="userServiceImpl")
    private IUserService userService;


    @RequestMapping("/showUser/{id}")
       //Restful请求
    public void selectUser(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        int userId =Integer.valueOf(id);
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }


    @RequestMapping("/showPeople.do")
    public void selectPeople(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int userId = 1;
        People people = this.userService.selectPeople(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(people));
        response.getWriter().close();
    }
}
