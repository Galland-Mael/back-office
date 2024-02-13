package project.backoffice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.service.FirmwareService;

@AllArgsConstructor
@RestController
@RequestMapping("/firmware")
public class FirmwareController {
private FirmwareService firmwareService;

    @GetMapping("/get-latest")
    public ResponseEntity<FirmwareVersionDTO> getLastestFirmwareVersion() throws JsonProcessingException {
        return ResponseEntity.ok(firmwareService.getLatestFirmwareVersion());
    }
}
