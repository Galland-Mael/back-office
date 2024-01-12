package project.backoffice.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.DTO.LibraryDTO;
import project.backoffice.Entity.User;
import project.backoffice.Enum.JsonTypeEnum;
import project.backoffice.Exception.ApiException;
import project.backoffice.Exception.MessageExceptionEnum;
import project.backoffice.Helper.JsonHelper;
import project.backoffice.Helper.StringHelper;
import project.backoffice.Mapper.LibraryMapper;
import project.backoffice.Repository.LibraryRepository;
import project.backoffice.Entity.Library;
import project.backoffice.Request.LibraryRequest;


@AllArgsConstructor
@Service
@Transactional
public class LibraryService {
    private LibraryRepository libraryRepository;
    private UserService userService;
    private LibraryMapper LibraryMapper;

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.LIBRARY_NOT_FOUND, id)));
    }


    public LibraryDTO createLibrary(LibraryRequest libraryRequest) {
        if (libraryRepository.getLibrariesByUserId(libraryRequest.getUserId()) != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    StringHelper.format(MessageExceptionEnum.LIBRARY_ALREADY_EXISTS_FOR_USER,  libraryRequest.getUserId()));
        }
        Library library = new Library();
        library.setJson(libraryRequest.getJson());

        User user = userService.getUserById(libraryRequest.getUserId());
        user.setLibrary(library);

        library.setUser(user);

        return LibraryMapper.toDTO(libraryRepository.save(library));
    }


    public LibraryDTO updateLibrary(Long id, LibraryRequest libraryRequest) {
        Library library = getLibraryById(id);
        library.setJson(libraryRequest.getJson());

        User user = userService.getUserById(libraryRequest.getUserId());
        user.setLibrary(library);

        library.setUser(user);

        return LibraryMapper.toDTO(libraryRepository.save(library));
    }

    public LibraryDTO getLibraryByUserId(Long userId) throws JsonProcessingException {
        Library library = libraryRepository.getLibrariesByUserId(userId);
        if (library == null) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    StringHelper.format(MessageExceptionEnum.LIBRARY_NOT_FOUND, userId));
        }
        LibraryDTO libraryDTO = LibraryMapper.toDTO(library);
        libraryDTO.setJson(JsonHelper.getJsonStringAsObject(library.getJson(), JsonTypeEnum.LIBRARY));
        return libraryDTO;
    }
}
