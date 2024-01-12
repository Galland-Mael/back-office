package project.backoffice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.dto.LibraryDTO;
import project.backoffice.mapper.LibraryMapper;
import project.backoffice.request.LibraryRequest;
import project.backoffice.service.LibraryService;

@AllArgsConstructor
@RestController
@RequestMapping("/library")
public class LibraryController {

    private LibraryService libraryService;
    private LibraryMapper libraryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Long id) {
        return ResponseEntity.ok(libraryMapper.toDTO(libraryService.getLibraryById(id)));
    }

    @PostMapping
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody LibraryRequest libraryRequest) {
        return new ResponseEntity<>(libraryMapper.toDTO(libraryService.createLibrary(libraryRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Long id, @RequestBody LibraryRequest libraryRequest) {
        return ResponseEntity.ok(libraryMapper.toDTO(libraryService.updateLibrary(id, libraryRequest)));
    }
}
