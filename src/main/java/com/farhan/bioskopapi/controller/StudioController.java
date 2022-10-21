package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.entity.StudioEntity;
import com.farhan.bioskopapi.helper.utility.ErrorParsingUtility;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.StudioService;
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
@RequestMapping("/bioskop/api/studios")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Studio", description = "Operation about studio")
public class StudioController {

    public static final Logger logger = LoggerFactory.getLogger(StudioEntity.class);

    private StudioService studioService;

    @Autowired
    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @Operation(summary = "Add a new studio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping
    public ResponseEntity<ResponseData<StudioEntity>> create(@Valid @RequestBody StudioEntity studioEntity, Errors errors){

        ResponseData<StudioEntity> responseData = new ResponseData<>();

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
            responseData.setData(studioService.save(studioEntity));
            logger.info("create studio : {}", studioEntity.getStudioName());
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

    @Operation(summary = "Get a studio by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<StudioEntity>> findOne(@PathVariable("id") Long id){
        ResponseData<StudioEntity> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            responseData.setData(studioService.findOne(id));
            logger.info("call find studio with id : {}", id);
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

    @Operation(summary = "Get all studios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<StudioEntity>>> findAll(){
        ResponseData<Iterable<StudioEntity>> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            responseData.setData(studioService.findAll());
            logger.info("call find all studio");
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

    @Operation(summary = "Update a studio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StudioEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping
    public ResponseEntity<ResponseData<StudioEntity>> update(@Valid @RequestBody StudioEntity studioEntity, Errors errors){

        ResponseData<StudioEntity> responseData = new ResponseData<>();

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
            responseData.setData(studioService.save(studioEntity));
            logger.info("update studio name : {}", studioEntity.getStudioName());
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

    @Operation(summary = "Delete a studio by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> removeOne(@PathVariable("id") Long id){
        ResponseData responseData = new ResponseData();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessage().add("sukses");
            studioService.removeOne(id);
            logger.info("delete studio id : {}", id);
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
