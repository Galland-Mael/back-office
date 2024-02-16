package project.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.backoffice.entity.Firmware;

@Repository
public interface FirmwareRepository extends JpaRepository<Firmware, Long> {

    @Query("SELECT f FROM Firmware f ORDER BY f.date DESC LIMIT 1")
    Firmware findLastestFirmware();

}