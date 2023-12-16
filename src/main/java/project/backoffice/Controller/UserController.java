package project.backoffice.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.DTO.UserDTO;
import project.backoffice.Service.UserService;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping
    ResponseEntity<Page<UserDTO>> getUserList(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy,
            @RequestParam Optional<Integer> sortOrder,
            @RequestParam Optional  <Integer> numberOfElements
            ){
        if ((page.isPresent() && page.get() < 0) ||
                (sortBy.isPresent() && !Arrays.asList("id", "firstName", "lastName", "email", "quality.name", "phone").contains(sortBy.get())) ||
                (sortOrder.isPresent() && !(sortOrder.get() == -1 || sortOrder.get() == 1)) ||
                (numberOfElements.isPresent() && numberOfElements.get() < 1)){
            return ResponseEntity.badRequest().build();
        }

        Page<UserDTO> dto = userService.getUserList(
                page.orElse(0),
                sortBy.orElse("id"),
                numberOfElements.orElse(5),
                sortOrder.orElse(1)
        );
        return ResponseEntity.ok(dto);
    }
}
