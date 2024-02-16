package project.backoffice.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backoffice.dto.QualityDTO;
import project.backoffice.entity.Quality;
import project.backoffice.mapper.QualityMapper;
import project.backoffice.service.QualityService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/qualities")
public class QualityController {

    private QualityService qualityService;
    private QualityMapper qualityMapper;
    @GetMapping
    public ResponseEntity<List<QualityDTO>> getQualities() {
        return ResponseEntity.ok(qualityMapper.toListDTO(qualityService.getAllQuality()));
    }
}
