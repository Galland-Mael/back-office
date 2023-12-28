package project.backoffice.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.Entity.PresetsStandard;
import project.backoffice.Service.PresetsStandardService;
import project.backoffice.Mapper.PresetsStandardMapper;
import project.backoffice.DTO.PresetsStandardDTO;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/preset_standard")
public class PresetsStandardController {

    private PresetsStandardService presetsStandardService;
    private PresetsStandardMapper presetsStandardMapper;

    @PostMapping
    public ResponseEntity<?> getPresetsStandardByType(@RequestBody Map<String, String> body) {
        try {
            String type = body.get("type");
            PresetsStandard presetsStandard = presetsStandardService.getPresetStandardByType(type);
            return new ResponseEntity<>(presetsStandardMapper.toDTO(presetsStandard), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}