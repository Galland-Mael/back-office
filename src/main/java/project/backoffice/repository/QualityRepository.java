package project.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backoffice.entity.Quality;

@Repository
public interface QualityRepository extends JpaRepository<Quality, Long> {
}
