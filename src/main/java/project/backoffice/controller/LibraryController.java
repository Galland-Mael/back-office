package project.backoffice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backoffice.entity.Library;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.service.LibraryService;

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
