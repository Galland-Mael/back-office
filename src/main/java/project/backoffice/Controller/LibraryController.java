package project.backoffice.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.DTO.LibraryDTO;
import project.backoffice.Mapper.LibraryMapper;
import project.backoffice.Request.LibraryRequest;
import project.backoffice.Service.LibraryService;


@AllArgsConstructor
@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;
    private LibraryMapper LibraryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Long id) {
        return ResponseEntity.ok(LibraryMapper.toDTO(libraryService.getLibraryById(id)));
    }

    @PostMapping
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody LibraryRequest libraryRequest) {
        return new ResponseEntity<>(LibraryMapper.toDTO(libraryService.createLibrary(libraryRequest)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Long id, @RequestBody LibraryRequest libraryRequest) {
        return ResponseEntity.ok(LibraryMapper.toDTO(libraryService.updateLibrary(id, libraryRequest)));
    }
}
