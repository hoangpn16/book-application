package projectbookapplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projectbookapplication.repository.TokenRepository;
import projectbookapplication.repository.UserRepository;

import projectbookapplication.repository.entity.UserEntity;
import projectbookapplication.service.model.FixUser;

import java.io.UnsupportedEncodingException;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    public UserEntity getUserByName(String name) {
        return userRepository.findByUserName(name);
    }

    public UserEntity getUserByPhoneNumbers(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
    public UserEntity getInforUser(Integer id){
        return userRepository.findOneById(id);
    }

    public UserEntity insertUser(FixUser user) throws UnsupportedEncodingException {
        UserEntity newUser = new UserEntity();
        boolean check = checkPhoneNumber(user.getPhonenumber());
        if (!check) {
            System.out.println("Phone number invalid");
        }

        newUser.setUserName(user.getName());
        newUser.setPhoneNumber(user.getPhonenumber());
        newUser.setEmail(user.getEmail());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(user.getPassword());
        newUser.setPassword(password);
        newUser.setRole("user");

        return userRepository.save(newUser);
    }

    public UserEntity changePassword(String phoneNumber, String password) {
        UserEntity result = userRepository.findByPhoneNumber(phoneNumber);
        if (result != null) {
            result.setPassword(password);
        }
        return userRepository.save(result);
    }

    public UserEntity changeInforUser(Integer id, FixUser modelFix) {
        UserEntity result = userRepository.findOneById(id);
        if (result == null) {
            throw new RuntimeException("User doesn't exists");
        } else {
            if (modelFix.getName() != null) {
                result.setUserName(modelFix.getName());
            }
            if (modelFix.getEmail() != null) {
                result.setEmail(modelFix.getEmail());
            }
            if (modelFix.getPassword() != null) {
                result.setPassword(modelFix.getPassword());
            }
        }
        return userRepository.save(result);
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^0[98]{1}\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }



}
