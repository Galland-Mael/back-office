package project.backoffice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.dto.FirmwareDTO;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.entity.Firmware;
import project.backoffice.service.FirmwareService;

@AllArgsConstructor
@RestController
@RequestMapping("api/firmwares")
public class FirmwareController {
private FirmwareService firmwareService;

    @GetMapping("/get-latest")
    public ResponseEntity<FirmwareVersionDTO> getLastestFirmwareVersion() throws JsonProcessingException {
        return ResponseEntity.ok(firmwareService.getLatestFirmwareVersion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FirmwareDTO> getFirmwareById(@PathVariable Long id) throws JsonProcessingException {
        return ResponseEntity.ok(firmwareService.getFirmwareById(id));
    }
}
