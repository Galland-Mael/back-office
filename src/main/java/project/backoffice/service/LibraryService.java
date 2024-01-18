package project.backoffice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.dto.LibraryDTO;
import project.backoffice.entity.User;
import project.backoffice.enumeration.JsonTypeEnum;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.helper.JsonHelper;
import project.backoffice.helper.StringHelper;
import project.backoffice.mapper.LibraryMapper;
import project.backoffice.repository.LibraryRepository;
import project.backoffice.entity.Library;
import project.backoffice.repository.UserRepository;


@AllArgsConstructor
@Service
@Transactional
public class LibraryService {
    private LibraryRepository libraryRepository;
    private UserService userService;
    private LibraryMapper LibraryMapper;
    private UserRepository userRepository;

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.LIBRARY_NOT_FOUND, id)));
    }


    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        Library optionalLibrary = libraryRepository.getLibrariesByUserId(libraryDTO.getUserId());
        if (optionalLibrary != null) {
            return updateLibrary(optionalLibrary.getId(), libraryDTO);
        }
        Library library = LibraryMapper.toEntity(libraryDTO);
        User user = updateLibraryInUser(libraryDTO,library);
        library.setUser(user);
        return LibraryMapper.toDTO(libraryRepository.save(library));
    }

    public User updateLibraryInUser(LibraryDTO libraryDTO,Library library) {
        User user = userService.getUserById(libraryDTO.getUserId());
        user.setLibrary(library);
        userRepository.save(user);
        return user;
    }

    public LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO) {
        Library library = getLibraryById(id);
        Object libraryObject = libraryDTO.getLibrary();
        library.setJson(JsonHelper.JsonToString(libraryObject));
        User user = updateLibraryInUser(libraryDTO,library);
        library.setUser(user);
        return LibraryMapper.toDTO(libraryRepository.save(library));
    }

    public LibraryDTO getLibraryByUserId(Long userId) throws JsonProcessingException {
        Library library = libraryRepository.getLibrariesByUserId(userId);
        if (library == null) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    StringHelper.format(MessageExceptionEnum.LIBRARY_NOT_FOUND, userId));
        }
        return LibraryMapper.toDTO(library);
    }
    
}
