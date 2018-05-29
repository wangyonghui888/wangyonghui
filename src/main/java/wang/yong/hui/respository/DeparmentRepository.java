package wang.yong.hui.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wang.yong.hui.domain.Deparment;

@Repository
public interface DeparmentRepository extends JpaRepository<Deparment, Long> {

}


