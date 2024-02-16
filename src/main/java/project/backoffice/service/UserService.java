package project.backoffice.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.dto.UserDTO;
import project.backoffice.entity.Quality;
import project.backoffice.entity.User;
import project.backoffice.exception.ApiException;
import project.backoffice.exception.MessageExceptionEnum;
import project.backoffice.helper.StringHelper;
import project.backoffice.mapper.QualityMapper;
import project.backoffice.mapper.UserMapper;
import project.backoffice.mapper.UserMapperInterface;
import project.backoffice.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private QualityService qualityService;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.USER_NOT_FOUND, id)));
    }

    public Page<UserDTO> getUserList(String searchTerm,PageRequest pageRequest) {
        return userMapper.toDTOPage(
                userRepository.findByEmailOrLastName(
                        searchTerm,
                        pageRequest
                ));
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.USER_NOT_FOUND, email)));
        userRepository.delete(user);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,
                        StringHelper.format(MessageExceptionEnum.USER_NOT_FOUND, id)));
        updateUserFromDTO(user, userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    private void updateUserFromDTO(User user, UserDTO userDTO) {
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        user.setQuality(qualityService.getQualityById(userDTO.getQuality().getId()));
    }
}
