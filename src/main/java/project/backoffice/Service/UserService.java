package project.backoffice.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import project.backoffice.Entity.User;
import project.backoffice.Exception.ApiException;
import project.backoffice.Exception.MessageExceptionEnum;
import project.backoffice.Helper.StringHelper;
import project.backoffice.Repository.UserRepository;


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
}
