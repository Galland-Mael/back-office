package project.backoffice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.backoffice.Entity.PresetsStandard;

@Repository
public interface PresetsStandardRepository extends JpaRepository<PresetsStandard, Long> {

    @Query(value = "SELECT * FROM presets_standard WHERE type = ?1", nativeQuery = true)
    PresetsStandard getPresetStandardByType(String type);

}
