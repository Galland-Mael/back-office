package project.backoffice.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backoffice.Service.LibraryService;
import project.backoffice.entity.Library;

@AllArgsConstructor
@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;

    @RequestMapping("/{id}")
    public Library getLibraryById(@PathVariable Long id) {
        return libraryService.getLibraryById(id);
    }
}
