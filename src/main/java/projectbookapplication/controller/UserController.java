package projectbookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectbookapplication.repository.entity.User;
import projectbookapplication.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value ="/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/getall",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> getAllUsers(){
        List<User> listUser = userService.getAllUser();
        return listUser;
    }
    @GetMapping(value = "/{phoneNumber}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUserByPhoneNumber(@PathVariable(name="phoneNumber")String phoneNumber){
        User result=userService.getUserByPhoneNumbers(phoneNumber);
        return result;
    }
    @PostMapping(value="/insert",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String registerUser(){
        userService.insertUser("Nghia Hung","0123456789","hung","hungngao@gmail.com");
        return "Register Succesfully";
    }

}
