package projectbookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projectbookapplication.repository.entity.User;
import projectbookapplication.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> getAllUsers() {
        List<User> listUser = userService.getAllUser();
        return listUser;
    }

    @GetMapping(value = "/{phoneNumber}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User getUserByPhoneNumber(@PathVariable(name = "phoneNumber") String phoneNumber) {
        User result = userService.getUserByPhoneNumbers(phoneNumber);
        return result;
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String registerUser(@RequestBody User user) {
        userService.insertUser(user);
        return "Register Successfully";
    }

    @PutMapping(value = "/changepassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String changeUserPassword(@PathVariable(value = "phonenumber") String phonenumber, @RequestParam(value = "password") String password) {
        userService.changePassword(phonenumber, password);
        return "Change user's password successfully";
    }

    @GetMapping(value = "/login")
    public User loginUser(@RequestParam(value = "phonenumber") String phonenumber, @RequestParam(value = "password") String password) {
        User user = userService.loginUser(phonenumber, password);
        return user;
    }

}
