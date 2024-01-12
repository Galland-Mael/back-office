package project.backoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.backoffice.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<User> findByEmailContainingIgnoreCaseOrLastNameContainingIgnoreCase(String searchTerm, Pageable pageable);
}
