package com.huawei.ibooking.controller;

import com.huawei.ibooking.business.StudentBusiness;
import com.huawei.ibooking.model.StudentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

//@RestController
@Controller
public class IndexController {

    @Autowired
    StudentBusiness studentBusiness;
    @RequestMapping(value = {"", "/", "index"}, method={RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest serverRequest) {

        HttpSession httpSession = serverRequest.getSession();
        StudentDO studentDO = (StudentDO) httpSession.getAttribute("loginUser");
        if("0".equals(studentDO.getRoleType())){
            //跳转管理员页面
            return "index";

        }else{
            //跳转学生页面
            return "index";
        }
    }

    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","hello,world");
        return "hello";
    }

    @RequestMapping(value="loginInvalid", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginInvalid(HttpServletRequest serverRequest, HttpServletResponse serverResponse) throws IOException {
        String userName = serverRequest.getParameter("usercode");
        String passWord = serverRequest.getParameter("password");
        String contextPath = ((HttpServletRequest) serverRequest).getContextPath();
        Optional<StudentDO> students = studentBusiness.getStudentByLoginName(userName);
        StudentDO studentDO = students.get();

        HttpSession httpSession = serverRequest.getSession();
        if(studentDO.getPassword().equals(passWord)){
            //认证通过
            httpSession.setAttribute("loginUser", studentDO);
            if("2".equals(studentDO.getRoleType())){
                //跳转管理员页面
                return "index";
//                serverResponse.sendRedirect(contextPath + "/index");
            }else{
                //跳转学生页面
                return "index";
            }

        }else{
            //跳转到登录页面
//            serverResponse.sendRedirect(contextPath + "/login");
            return "login";
        }


    }
}
