package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.helper.utility.ErrorParsingUtility;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/bioskop/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "User", description = "Operation about user")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserEntity.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //add user sekarang lewat AuthController
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
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
<<<<<<< HEAD
            responseData.setMessage(ErrorParsingUtility.parse(errors));
=======
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
>>>>>>> 9c72f9c2a2eef9973588130d549498053ae0eea0
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            responseData.setData(userService.save(userEntity));
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(true);
<<<<<<< HEAD
            responseData.getMessage().add(ex.getMessage());
=======
            responseData.getMessages().add(ex.getMessage());
            logger.warn("error from server : {}", ex.getMessage());
>>>>>>> 9c72f9c2a2eef9973588130d549498053ae0eea0
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get a user by its username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/{username}")
    public ResponseEntity<ResponseData<UserEntity>> findOne(@PathVariable("username") String username){
        ResponseData<UserEntity> responseData = new ResponseData<>();
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            responseData.setData(userService.findOne(username));
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
<<<<<<< HEAD
            responseData.getMessage().add(ex.getMessage());
=======
            responseData.getMessages().add(ex.getMessage());
            logger.warn("error from server : {}", ex.getMessage());
>>>>>>> 9c72f9c2a2eef9973588130d549498053ae0eea0
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<UserEntity>>> findAll(){
        ResponseData<Iterable<UserEntity>> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            responseData.setData(userService.findAll());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
<<<<<<< HEAD
            responseData.getMessage().add(ex.getMessage());
=======
            responseData.getMessages().add(ex.getMessage());
            logger.warn("error from server : {}", ex.getMessage());
>>>>>>> 9c72f9c2a2eef9973588130d549498053ae0eea0
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @PutMapping
    public ResponseEntity<ResponseData<UserEntity>> update(@Valid @RequestBody UserEntity userEntity, Errors errors){
        ResponseData<UserEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
<<<<<<< HEAD
            responseData.setMessage(ErrorParsingUtility.parse(errors));
=======
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
>>>>>>> 9c72f9c2a2eef9973588130d549498053ae0eea0
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            responseData.setData(userService.save(userEntity));
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(true);
<<<<<<< HEAD
            responseData.getMessage().add(ex.getMessage());
=======
            responseData.getMessages().add(ex.getMessage());
            logger.warn("error from server : {}", ex.getMessage());
>>>>>>> 9c72f9c2a2eef9973588130d549498053ae0eea0
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Delete a user by its username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseData> removeOne(@PathVariable("username") String username){
        ResponseData responseData = new ResponseData();

        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            userService.removeOne(username);
            logger.info("delete user : {}", username);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
<<<<<<< HEAD
            responseData.getMessage().add(ex.getMessage());
=======
            responseData.getMessages().add(ex.getMessage());
            logger.warn("error from server : {}", ex.getMessage());
>>>>>>> 9c72f9c2a2eef9973588130d549498053ae0eea0
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
}
