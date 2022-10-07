package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.request.SearchRequest;
import com.farhan.bioskopapi.dto.request.SearchStatusRequest;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bioskop/api/films")
@Tag(name = "Film", description = "Operation about film")
public class FilmController {

    private FilmService filmService;

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
    public ResponseEntity<ResponseData<FilmEntity>> create(@Valid @RequestBody FilmEntity filmEntity, Errors errors){

        ResponseData<FilmEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
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
        responseData.setData(filmService.save(filmEntity));
        return ResponseEntity.ok(responseData);
    }

    @Operation(summary = "Get film by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping("/{id}")
    public FilmEntity findOne(@PathVariable("id") String id){
        return filmService.findOne(id);
    }

    @Operation(summary = "Get all films")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping
    public Iterable<FilmEntity> findAll(){
        return filmService.findAll();
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
    public FilmEntity update(@RequestBody FilmEntity filmEntity){
        return filmService.save(filmEntity);
    }

    @Operation(summary = "Get a film by its name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping("/search/name")
    public FilmEntity getFilmByName(@RequestBody SearchRequest searchRequest){
        return filmService.findByName(searchRequest.getSearchKey());
    }

    @Operation(summary = "Get films show by its status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping("/search/status")
    public List<FilmEntity> getFilmByStatus(@RequestBody SearchStatusRequest searchStatusRequest){
        return filmService.findByStatus(searchStatusRequest.getStatusKey());
    }

    @Operation(summary = "delete a film by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FilmEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") String id){
        filmService.removeOne(id);
    }
}
