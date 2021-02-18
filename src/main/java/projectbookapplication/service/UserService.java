package projectbookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectbookapplication.repository.UserRepository;
import projectbookapplication.repository.entity.User;
import projectbookapplication.service.model.FixUser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User getUserByPhoneNumbers(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public User insertUser(User user) {
        User newUser = new User();
        boolean check = checkPhoneNumber(user.getPhoneNumber());
        if (check == false) {
            System.out.println("Phone number invalid");
        }
        newUser.setName(user.getName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    public User changePassword(String phoneNumber, String password) {
        User result = userRepository.findByPhoneNumber(phoneNumber);
        if (result != null) {
            result.setPassword(password);
        }
        return userRepository.save(result);
    }

    public User changeInforUser(String phoneNumber, FixUser modelFix) {
        boolean check = checkPhoneNumber(phoneNumber);
        if (check == false) {
            System.out.println("Phone number invalid");
            return null;
        }
        User result = userRepository.findByPhoneNumber(phoneNumber);
        if (result == null) {
            System.out.println("User doesn't existed");
            return null;
        } else {
            if (modelFix.getName() != null) {
                result.setName(modelFix.getName());
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

    public User loginUser(String phoneNumber, String password) {
        boolean check = checkPhoneNumber(phoneNumber);
        if (check == false) {
            System.out.println("Phone number invalid");
            return null;
        }
        User result = userRepository.findByPhoneNumber(phoneNumber);
        if (result == null) {
            System.out.println("Incorrect password");
            return null;
        }
        if (result.getPassword().equals(password)) {
            System.out.println("Login Successfully");
            return result;
        }
        return null;
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^0[98]{1}\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.find();
    }

}
