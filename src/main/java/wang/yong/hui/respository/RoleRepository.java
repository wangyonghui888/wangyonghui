package wang.yong.hui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wang.yong.hui.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
