package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.request.FilmRequest;
import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.request.SearchRequest;
import com.farhan.bioskopapi.dto.request.SearchStatusRequest;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.helper.utility.ErrorParsingUtility;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;

import static com.farhan.bioskopapi.helper.utility.StatusMsg.*;

@RestController
@RequestMapping("/bioskop/api/films")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Film", description = "Operation about film")
public class FilmController {

    private final FilmService filmService;

    public static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @Operation(summary = "Add a new film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping
    public ResponseEntity<ResponseData<FilmEntity>> create(@Valid @RequestBody FilmRequest filmRequest, Errors errors){

        ResponseData<FilmEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn(REQUEST_INVALID, ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            FilmEntity filmEntity = new FilmEntity(filmRequest.getId(),
                    filmRequest.getFilmName(), filmRequest.getFilmStatus());
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(filmService.save(filmEntity));
            logger.info("save film : {}", filmEntity.getFilmName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get film by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<FilmEntity>> findOne(@PathVariable("id") String id){
        ResponseData<FilmEntity> responseData = new ResponseData<>();
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(filmService.findOne(id));
            logger.info("sukses find film by id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get all films")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<FilmEntity>>> findAll(){
        ResponseData<Iterable<FilmEntity>> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(filmService.findAll());
            logger.info("sukses get all films");
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Update a film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping
    public ResponseEntity<ResponseData<FilmEntity>> update(@Valid @RequestBody FilmRequest filmRequest, Errors errors){
        ResponseData<FilmEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn(REQUEST_INVALID, ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            FilmEntity filmEntity = new FilmEntity(filmRequest.getId(),
                    filmRequest.getFilmName(), filmRequest.getFilmStatus());
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(filmService.save(filmEntity));
            logger.info("update film  : {}", filmEntity.getFilmName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get a film by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/search/name")
    public ResponseEntity<ResponseData<FilmEntity>> getFilmByName(@Valid @RequestBody SearchRequest searchRequest, Errors errors){
        ResponseData<FilmEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("request invalid :{}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(filmService.findByName(searchRequest.getSearchKey()));
            logger.info("sukses get film name : {}", searchRequest.getSearchKey());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get films show by its status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/search/status")
    public ResponseEntity<ResponseData<List<FilmEntity>>> getFilmByStatus(@Valid @RequestBody SearchStatusRequest searchStatusRequest, Errors errors){
        ResponseData<List<FilmEntity>> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("request invalid :{}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(filmService.findByStatus(searchStatusRequest.getStatusKey()));
            logger.info("sukses get film status : {}", searchStatusRequest.getStatusKey());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn("error from server :{}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "delete a film by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> removeOne(@PathVariable("id") String id){
        ResponseData<String> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            filmService.removeOne(id);
            logger.info("sukses remove film by id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn("error from server :{}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
}
