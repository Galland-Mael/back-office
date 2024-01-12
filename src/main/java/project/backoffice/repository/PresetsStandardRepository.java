package project.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.backoffice.entity.PresetsStandard;

@Repository
public interface PresetsStandardRepository extends JpaRepository<PresetsStandard, Long> {

    @Query("""

            SELECT ps FROM PresetsStandard ps WHERE ps.type = :type 
            """)
    PresetsStandard getPresetStandardByType(String type);

}
