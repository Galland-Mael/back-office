package project.backoffice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backoffice.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
