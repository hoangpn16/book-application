package projectbookapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectbookapplication.repository.entity.RoleApiEntity;

import java.util.List;

@Repository
public interface RoleApiRepository extends JpaRepository<RoleApiEntity,Integer> {
    List<RoleApiEntity> findByRoleName (String roleName);
}
