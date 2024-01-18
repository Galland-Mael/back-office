package project.backoffice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.service.PresetsStandardService;
import project.backoffice.dto.PresetsStandardDTO;

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