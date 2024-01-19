package project.backoffice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.dto.LibraryDTO;
import project.backoffice.service.LibraryService;


@AllArgsConstructor
@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private LibraryService libraryService;

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<LibraryDTO> getLibraryByUserId(@PathVariable Long userId) throws JsonProcessingException {
        return ResponseEntity.ok(libraryService.getLibraryByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody LibraryDTO LibraryDTO) {
        return new ResponseEntity<>(libraryService.createLibrary(LibraryDTO), HttpStatus.CREATED);
    }

}
