package com.nsearch.hr.controller;

import com.nsearch.hr.entity.User;
import com.nsearch.hr.service.UserService;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import com.opencsv.bean.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getString")
    @ResponseBody
    public RequestResponse getString() {

        RequestResponse response = RequestResponse.builder().message("Hello World").build();
        return response;
    }

    @PostMapping(value = "/users/upload")
    public ResponseEntity uploadUsers(@RequestParam("file") MultipartFile file) {
        String fileUploadStatus = "";
        if (file.isEmpty()) {
            fileUploadStatus = "Empty file provided.";
            return ResponseEntity.status(HttpStatus.OK).body(RequestResponse.builder().message(fileUploadStatus).build());
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                // create csv bean reader
                CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader).withType(User.class).withIgnoreLeadingWhiteSpace(true).build();

                // convert `CsvToBean` object to list of users
                List<User> users = csvToBean.parse();
                for (User user : users) {
                    userService.saveUser(user);
                }
                fileUploadStatus = "Successfully uploaded.";
            } catch (Exception ex) {
                fileUploadStatus = "An error occurred while processing the CSV file.";
            }
        }
        return ResponseEntity.ok(RequestResponse.builder().message(fileUploadStatus).build());

    }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity saveUser(@RequestBody User user) {
        User savedUser = null;
        try {
            savedUser = userService.saveUser(user);

        } catch (PersistenceException ex) {
            if (ex instanceof ConstraintViolationException) {
                String statusMsg = "The Id or Login is not unique.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestResponse.builder().message(statusMsg).build());
            }
        } catch (Exception ex) {
            String statusMsg = "Saving had some error.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestResponse.builder().message(statusMsg).build());

        }
        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }


    //TODO: Bonus: Custom order list by ID, employee name, loginId, or salary?
    @GetMapping("users")
    @ResponseBody
    public ResponseEntity fetchUserList(@RequestParam(name = "min-salary", required = false, defaultValue = "0") float minSalary,
                                    @RequestParam(name = "max-salary", required = false, defaultValue = "4000") float maxSalary,
                                    @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
                                    @RequestParam(name = "limit", required = false, defaultValue = "0")  int limit) {
        List<User> userList = (List<User>) userService.fetchUserList(minSalary, maxSalary, offset, limit);
        return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse("Successful", userList));
    }

    @GetMapping("users/{$id}")
    @ResponseBody
    public ResponseEntity getUserById(@PathVariable("id") String id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponse().builder().message("Bad input - no such employee."));
        }
    }

    @PutMapping("/users/{id}")
    @PatchMapping("/users/{id}")
    public ResponseEntity updateUserById(@RequestBody User user, @PathVariable("id") String id) {
        try {
            User updatedUser = userService.updateUserById(user, id);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponse().builder().message("Bad input - no such employee."));
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") String id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse().builder().message("Employee deleted."));
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RequestResponse().builder().message("Bad input - no such employee."));
        }
    }
}
