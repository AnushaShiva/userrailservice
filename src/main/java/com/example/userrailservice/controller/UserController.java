package com.example.userrailservice.controller;

import com.example.userrailservice.entity.ResponseEntityVo;
import com.example.userrailservice.entity.User;
import com.example.userrailservice.exception.ResourceNotFoundException;
import com.example.userrailservice.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {


    @Autowired
    public UserService userService;

    
    @PostMapping("/")
    public User addUser(@RequestBody User use) {
    	 log.info("adding user into ");
        return userService.addUser(use);

    }


    @ApiOperation(value = "Get specific User by id in the System ", response = User.class, tags = "getById")
    @GetMapping("/get/{id}")
    public Optional<User> getById(@PathVariable Long id) throws ResourceNotFoundException {
    	log.info("retiving data from db based upon id");
        return userService.getById(id);

    }

    @ApiOperation(value = "Get List of users  in the System ", response = Iterable.class, tags = "getAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
    	log.info("retiving all users details");
        return new ResponseEntity<>(userService.getAllDetails(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) throws ResourceNotFoundException {
        userService.delete(id);
        log.info("deleting data of user from db basedUpon id");
        return "trainDetails deleted successfully ";
    }



    @GetMapping("/train/{id}")
    public ResponseEntityVo getuserAlongWithTrainDetails(@PathVariable Long id) throws ResourceNotFoundException {
       log.info("getting user data along with that person bokked traindetails");
    	return  userService.getuserAlongWithTrainDetails(id);
    }


    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User use, @PathVariable Long id) throws ResourceNotFoundException {

    	log.info("update user based upon id");
        return  userService.updateUser(use,id);


    }

}
