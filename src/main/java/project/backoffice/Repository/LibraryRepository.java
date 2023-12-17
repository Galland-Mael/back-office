package project.backoffice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.backoffice.Entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    @Query("SELECT l FROM Library l WHERE l.user.id = ?1")
    Library getLibrariesByUserId(Long userId);

}
