package project.backoffice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.backoffice.dto.FirmwareCreationDTO;
import project.backoffice.dto.FirmwareDTO;
import project.backoffice.dto.FirmwareVersionDTO;
import project.backoffice.dto.LightFirmwareDto;
import project.backoffice.entity.Firmware;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.service.FirmwareService;

import java.text.ParseException;

@AllArgsConstructor
@RestController
@RequestMapping("api/firmwares")
public class FirmwareController {
private FirmwareService firmwareService;

    @GetMapping("/get-latest")
    public ResponseEntity<FirmwareVersionDTO> getLastestFirmwareVersion() {
        return ResponseEntity.ok(firmwareService.getLatestFirmwareVersion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FirmwareDTO> getFirmwareById(@PathVariable Long id) {
        return ResponseEntity.ok(firmwareService.getFirmwareById(id));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<LightFirmwareDto> createFirmware(@PathVariable Integer productId, @RequestParam("file") MultipartFile firmwareFile, @RequestParam("version") String version, @RequestParam("date") String date) throws ParseException {
        FirmwareCreationDTO firmwareDTO = new FirmwareCreationDTO();
        firmwareDTO.setFile(firmwareFile);
        firmwareDTO.setVersion(version);
        firmwareDTO.setDate(date);
        firmwareDTO.setProductId(productId);
        return ResponseEntity.ok(firmwareService.createFirmware(firmwareDTO));
    }

}
