package com.example.user_demo_client.controller;


import com.example.user_demo_client.domain.User;
import com.example.user_demo_client.domain.UserResponse;
import com.example.user_demo_client.service.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class UserController {
    @Autowired
    private UserApiService userApiService;


    @GetMapping("/index")
    public String main() {
        return "index";
    }


    @GetMapping("/getUsers")
    public ModelAndView getUsers() throws Exception {
        UserResponse userResponse = userApiService.retrieveUserAll();

        ModelAndView mv = new ModelAndView();
        mv.addObject("userResponse", userResponse);
        mv.setViewName("뷰 이름");
        return mv;
    }


    @GetMapping("/getUser")
    public ModelAndView getUser(@RequestParam int id) throws Exception {
        User user = userApiService.retrieveUserById(id);

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("뷰 이름");
        return mv;
    }


}

















