package projectbookapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectbookapplication.repository.RoleApiRepository;
import projectbookapplication.repository.TokenRepository;
import projectbookapplication.repository.UserRepository;
import projectbookapplication.repository.entity.RoleApiEntity;
import projectbookapplication.repository.entity.TokenEntity;
import projectbookapplication.repository.entity.UserEntity;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SecurityService {
    @Autowired
    RoleApiRepository roleApiRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    UserRepository userRepository;

    public void validRequest(String token, String apiPath, String apiMethod) {
        TokenEntity tokenEntity = tokenRepository.findByUserToken(token);
        if (tokenEntity == null) {
            throw new RuntimeException("Token not valid");
        }
        if (tokenEntity.getExpriedTime().before(new Timestamp(System.currentTimeMillis()))) {
            throw new RuntimeException("Token expried");
        }
        String userName = tokenEntity.getUserName();
        UserEntity userEntity = userRepository.findByUserName(userName);
        List<RoleApiEntity> listRoles = roleApiRepository.findByRoleName(userEntity.getRole());

        for (RoleApiEntity role : listRoles) {
            if (role.getApiPath().contains(apiPath) && role.getApiMethod().equals(apiMethod)) {
                return;
            }
        }
        throw new RuntimeException("Role has not permission");
    }
}
