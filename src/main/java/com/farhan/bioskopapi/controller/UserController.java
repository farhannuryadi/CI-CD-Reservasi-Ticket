package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/bioskop/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<UserEntity>> create(@Valid @RequestBody UserEntity userEntity, Errors errors){

        ResponseData<UserEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.getMessages().add("sukses");
        responseData.setStatus(true);
        responseData.setData(userService.save(userEntity));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{username}")
    public UserEntity findOne(@PathVariable("username") String username){
        return userService.findOne(username);
    }

    @GetMapping
    public Iterable<UserEntity> findAll(){
        return userService.findAll();
    }

    @PutMapping
    public UserEntity update(@RequestBody UserEntity userEntity){
        return userService.save(userEntity);
    }

    @DeleteMapping("/{username}")
    public void removeOne(@PathVariable("username") String username){
        userService.removeOne(username);
    }
}
