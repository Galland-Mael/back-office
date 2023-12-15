package project.backoffice.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backoffice.Exception.ApiException;
import project.backoffice.Exception.MessageExceptionEnum;
import project.backoffice.Service.LibraryService;
import project.backoffice.Entity.Library;

@AllArgsConstructor
@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;

    @RequestMapping("/{id}")
    public Library getLibraryById(@PathVariable Long id) {
        throw new ApiException(HttpStatus.BAD_REQUEST, MessageExceptionEnum.USER_NOT_FOUND);
//        return libraryService.getLibraryById(id);
    }
}
