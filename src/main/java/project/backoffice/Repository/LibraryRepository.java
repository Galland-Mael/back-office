package project.backoffice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backoffice.Entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
}
