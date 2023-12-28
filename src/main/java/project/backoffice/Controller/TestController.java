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
@RequestMapping("/test")
public class TestController {
    // return 'working' when the endpoint is called
    @GetMapping
    public ResponseEntity<?> getTest() {
        try {
            return new ResponseEntity<>("Working", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}