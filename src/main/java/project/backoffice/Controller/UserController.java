package project.backoffice.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.DTO.UserDTO;
import project.backoffice.DTO.UserListParamDTO;
import project.backoffice.Exception.ApiException;
import project.backoffice.Service.UserService;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping
    ResponseEntity<Page<UserDTO>> getUserList(
            @RequestParam Integer page,
            @RequestParam Optional<String> sortBy,
            @RequestParam Optional<Integer> sortOrder,
            @RequestParam Optional<Integer> numberOfElements,
            @RequestParam Optional<String> searchTerm
            ){
        if ((page < 0)){
            throw new ApiException(HttpStatus.BAD_REQUEST, "The specified page doesn't exist");
        }
        List<String> sortElements = Arrays.asList("id", "firstName", "lastName", "email", "quality.name", "phone");
        if (sortBy.isPresent() && !sortElements.contains(sortBy.get())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "The sort element has to be 'id', 'firstName', 'lastName', 'email', 'quality.name' or 'phone'");
        }
        if (sortOrder.isPresent() && !(sortOrder.get() == -1 || sortOrder.get() == 1)){
            throw new ApiException(HttpStatus.BAD_REQUEST, "The sort order has to be -1 = DESC or 1 = ASC");
        }
        if (numberOfElements.isPresent() && numberOfElements.get() < 1) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "The number of elements by page must be a positive integer");
        }

        UserListParamDTO userListParamDTO = new UserListParamDTO(
                page,
                sortBy.orElse("id"),
                sortOrder.orElse(1),
                numberOfElements.orElse(5),
                searchTerm.orElse("")
        );

        Page<UserDTO> dto = userService.getUserList(userListParamDTO);

        return ResponseEntity.ok(dto);
    }
}
