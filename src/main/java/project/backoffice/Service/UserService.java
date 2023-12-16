package project.backoffice.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.backoffice.DTO.UserDTO;
import project.backoffice.Mapper.UserMapperInterface;
import project.backoffice.Repository.UserRepository;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository userRepository;

    public Page<UserDTO> getUserList(Integer page, String sortBy, Integer numberOfElements, Integer sortOrder){
        return UserMapperInterface.INSTANCE.mapToUserDTOPage(
                userRepository.findAll(
                    PageRequest.of(
                        page,
                        numberOfElements,
                        sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC,
                        sortBy
                    )
        ));
    }
}
