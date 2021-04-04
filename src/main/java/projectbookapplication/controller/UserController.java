package projectbookapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projectbookapplication.repository.TokenRepository;
import projectbookapplication.repository.UserRepository;
import projectbookapplication.repository.entity.TokenEntity;
import projectbookapplication.repository.entity.UserEntity;
import projectbookapplication.service.SecurityService;
import projectbookapplication.service.UserService;
import projectbookapplication.service.model.FixUser;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    SecurityService securityService;

    @GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserEntity> getAllUsers() {
        List<UserEntity> listUser = userService.getAllUser();
        return listUser;
    }

    @GetMapping(value = "/userinfor/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserEntity getUserById(@RequestHeader(name = "token") String token,@PathVariable(name = "id") Integer id) {
        securityService.validRequest(token,"user/userinfor/{id}","GET");
        UserEntity result = userService.getInforUser(id);
        return result;
    }

    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    String registerUser(@RequestBody FixUser user) throws UnsupportedEncodingException {
        userService.insertUser(user);
        return "Register Successfully";
    }

//    @PutMapping(value = "/changepassword/{phonenumber}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String changeUserPassword(@PathVariable(value = "phonenumber") String phonenumber, @RequestParam(value = "password") String password) {
//        userService.changePassword(phonenumber, password);
//        return "Change user's password successfully";
//    }

    @PutMapping(value = "/changeinfor/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody String changeInFor(@RequestHeader(name = "token") String token,@PathVariable(value = "id") Integer id,
                       @RequestBody FixUser modelFix) {
        securityService.validRequest(token,"user/changeinfor","PUT");
        userService.changeInforUser(id, modelFix);
        return "Changed information of user";
    }

    @PostMapping(value = "/login")
    public String loginUser(@RequestParam(value = "username") String name, @RequestParam(value = "password") String password) throws UnsupportedEncodingException {
        UserEntity userEntity = userRepository.findByUserName(name);
        if (userEntity == null) {
            throw new RuntimeException("User is not existed");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Boolean check =encoder.matches(password,userEntity.getPassword());
        if (check.equals(false)) {
            throw new RuntimeException("Pass is not correct");
        }

        long deltaTime = 60 * 3000;
        String token = UUID.randomUUID().toString();
        TokenEntity tokenEntity = tokenRepository.findByUserName(userEntity.getUserName());
        if (tokenEntity == null) {
            tokenEntity = new TokenEntity();
        }
        tokenEntity.setUserToken(token);
        System.out.println(token);
        tokenEntity.setUserName(userEntity.getUserName());
        tokenEntity.setExpriedTime(new Timestamp(System.currentTimeMillis() + deltaTime));
        tokenRepository.save(tokenEntity);
        return token;
    }

}
