package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/bioskop/api/users")
@Tag(name = "User", description = "Operation about user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping
    public ResponseEntity<ResponseData<UserEntity>> create(@Valid @RequestBody UserEntity userEntity, Errors errors){

        ResponseData<UserEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatusCode(StatusCode.OK);
        responseData.setStatus(true);
        responseData.getMessages().add("sukses");
        responseData.setData(userService.save(userEntity));
        return ResponseEntity.ok(responseData);
    }

    @Operation(summary = "Get a user by its username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/{username}")
    public UserEntity findOne(@PathVariable("username") String username){
        return userService.findOne(username);
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping
    public Iterable<UserEntity> findAll(){
        return userService.findAll();
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping
    public UserEntity update(@RequestBody UserEntity userEntity){
        return userService.save(userEntity);
    }

    @Operation(summary = "Delete a user by its username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping("/{username}")
    public void removeOne(@PathVariable("username") String username){
        userService.removeOne(username);
    }
}
