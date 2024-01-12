package project.backoffice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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


    @GetMapping
    public ResponseEntity<PresetsStandardDTO> getPresetsStandardByType(@RequestParam String type) throws Exception {
            return ResponseEntity.ok(presetsStandardService.getPresetStandardByType(type));
    }
}