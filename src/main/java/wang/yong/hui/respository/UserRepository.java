package wang.yong.hui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wang.yong.hui.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
