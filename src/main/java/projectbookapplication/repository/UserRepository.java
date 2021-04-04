package projectbookapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectbookapplication.repository.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUserName(String name);
    UserEntity findByPhoneNumber(String phoneNumber);
    UserEntity findOneById(Integer id);
}
