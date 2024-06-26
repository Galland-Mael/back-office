package project.backoffice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.backoffice.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.token = :searchTerm")
    Optional<User> findByToken(@Param("searchTerm") String token);

    @Query("""
    SELECT u 
    FROM User u
    WHERE (:#{#searchTerm} IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :#{#searchTerm}, '%')))
    OR (:#{#searchTerm} IS NULL OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :#{#searchTerm}, '%')))
""")
    Page<User> findByEmailOrLastName(@Param("searchTerm") String searchTerm, Pageable pageable);
}
