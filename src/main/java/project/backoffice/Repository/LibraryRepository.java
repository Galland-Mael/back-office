package project.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backoffice.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
}
