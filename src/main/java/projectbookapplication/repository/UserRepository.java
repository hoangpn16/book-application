package projectbookapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectbookapplication.repository.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByName(String name);
    User findByPhoneNumber(String phoneNumber);

}