package project.backoffice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.entity.User;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.helper.StringHelper;
import project.backoffice.repository.LibraryRepository;
import project.backoffice.entity.Library;
import project.backoffice.request.LibraryRequest;


@AllArgsConstructor
@Service
@Transactional
public class LibraryService {
    private LibraryRepository libraryRepository;
    private UserService userService;

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.LIBRARY_NOT_FOUND, id)));
    }

    public Library getLibrariesByUserId(Long userId) {
        return libraryRepository.getLibrariesByUserId(userId);
    }

    public Library createLibrary(LibraryRequest libraryRequest) {
        if (getLibrariesByUserId(libraryRequest.getUserId()) != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    StringHelper.format(MessageExceptionEnum.LIBRARY_ALREADY_EXISTS_FOR_USER,  libraryRequest.getUserId()));
        }
        Library library = new Library();
        library.setJson(libraryRequest.getJson());

        User user = userService.getUserById(libraryRequest.getUserId());
        user.setLibrary(library);

        library.setUser(user);

        return libraryRepository.save(library);
    }


    public Library updateLibrary(Long id, LibraryRequest libraryRequest) {
        Library library = getLibraryById(id);
        library.setJson(libraryRequest.getJson());

        User user = userService.getUserById(libraryRequest.getUserId());
        user.setLibrary(library);

        library.setUser(user);

        return libraryRepository.save(library);
    }
}
