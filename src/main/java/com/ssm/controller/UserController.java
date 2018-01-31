package com.ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.model.User;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int userId = 1;
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }
}
