package com.itheima.controller;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    /**
     * 登录
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user, HttpSession session) {
        User loginUser = userService.findByIdAndName(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            // 写到session中
            session.setAttribute("User", loginUser);
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/findById/{id}")
    @ResponseBody
    public User findById(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(@RequestBody User user){
        userService.update(user);
    }
}
