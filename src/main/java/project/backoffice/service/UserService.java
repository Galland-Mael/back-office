package project.backoffice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.dto.UserDTO;
import project.backoffice.dto.UserListParamDTO;
import project.backoffice.entity.User;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.helper.StringHelper;
import project.backoffice.mapper.UserMapperInterface;
import project.backoffice.repository.UserRepository;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.USER_NOT_FOUND, id)));
    }

    public Page<UserDTO> getUserList(UserListParamDTO userListParamDTO){
        return UserMapperInterface.INSTANCE.mapToUserDTOPage(
                userRepository.findByEmailContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        userListParamDTO.getSearchTerm(),
                        PageRequest.of(
                                userListParamDTO.getPage(),
                                userListParamDTO.getNumberOfElements(),
                                userListParamDTO.getSortOrder() == 1 ? Sort.Direction.ASC : Sort.Direction.DESC,
                                userListParamDTO.getSortBy()
                        )
                ));
    }
}
