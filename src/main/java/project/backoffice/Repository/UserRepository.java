package project.backoffice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backoffice.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
