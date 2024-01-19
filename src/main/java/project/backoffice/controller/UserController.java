package project.backoffice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backoffice.dto.UserDTO;
import project.backoffice.enumeration.SortByEnum;
import project.backoffice.exception.ApiException;
import project.backoffice.service.UserService;

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
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam Integer numberOfElements,
            @RequestParam(required = false) String searchTerm
    ) {
        if ((page < 0)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "The specified page doesn't exist");
        }
        checkUserListParameter(sortBy, sortOrder, numberOfElements);
        PageRequest pageRequest = PageRequest.of(
                page,
                numberOfElements,
                sortOrder == null || "ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortBy == null ? "id" : sortBy
        );
        Page<UserDTO> dto = userService.getUserList(searchTerm, pageRequest);
        return ResponseEntity.ok(dto);
    }

    private static void checkUserListParameter(String sortBy, String sortOrder, Integer numberOfElements) {
        List<SortByEnum> listSortBy = Arrays.asList(
                SortByEnum.PHONE,
                SortByEnum.EMAIL,
                SortByEnum.FIRST_NAME,
                SortByEnum.LAST_NAME,
                SortByEnum.QUALITY_NAME);
        if (sortBy != null) {
            if (!listSortBy.contains(SortByEnum.findByCode(sortBy))) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "The sort element has to be 'id', 'firstName', 'lastName', 'email', 'quality.name' or 'phone'");
            }
            if (sortOrder != null) {
                if (!("ASC".equals(sortOrder) || "DESC".equals(sortOrder))) {
                    throw new ApiException(HttpStatus.BAD_REQUEST, "The sort order has to be DESC or ASC");
                }
            }
        }
        if (numberOfElements != null) {
            if (numberOfElements < 1) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "The number of elements by page must be a positive integer");
            }
        }
    }
}
