package kz.bitlab.logistic.repository;

import jakarta.transaction.Transactional;
import kz.bitlab.logistic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String string);
}
