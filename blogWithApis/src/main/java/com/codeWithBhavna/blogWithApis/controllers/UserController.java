package com.codeWithBhavna.blogWithApis.controllers;

import com.codeWithBhavna.blogWithApis.payloads.ApiResponse;
import com.codeWithBhavna.blogWithApis.payloads.UserDto;
import com.codeWithBhavna.blogWithApis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    @Qualifier(value = "UserServiceImpl")
    private UserService userService;
// POST- create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
//  PUT - update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
        UserDto updatedUser = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updatedUser);
    }
//  DELETE - delete user
    @DeleteMapping("/{userId}")
//    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
//        this.deleteUser(uid);
//        return new ResponseEntity(Map.of("message", "User deleted successfully"), HttpStatus.OK);
//    }

//    one more way of deleting the user by calling the apiResponse class.
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

//    GET- get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //    GET- get user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}
