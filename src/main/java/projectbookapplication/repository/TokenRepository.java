package projectbookapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectbookapplication.repository.entity.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity,Integer> {
    TokenEntity findByUserToken(String token);
    TokenEntity findByUserName(String username);
}
