package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.request.ScheduleRequest;
import com.farhan.bioskopapi.dto.request.SearchRequest;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;
import com.farhan.bioskopapi.helper.utility.ErrorParsingUtility;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.FilmService;
import com.farhan.bioskopapi.service.ScheduleService;
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
import java.util.List;

import static com.farhan.bioskopapi.helper.utility.StatusMsg.*;

@RestController
@RequestMapping("/bioskop/api/schedules")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Schedule", description = "Operation about schedule")
public class ScheduleController {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    private final ScheduleService scheduleService;

    private final FilmService filmService;

    private final StudioService studioService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, FilmService filmService, StudioService studioService) {
        this.scheduleService = scheduleService;
        this.filmService = filmService;
        this.studioService = studioService;
    }

    @Operation(summary = "Add a new schdedule with id film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping("/with/id")
    public ResponseEntity<ResponseData<ScheduleEntity>> create(@Valid @RequestBody ScheduleRequest scheduleRequest, Errors errors){
        ResponseData<ScheduleEntity> responseData = new ResponseData<>();

        if ((errors.hasErrors()) || (studioService.findOne(scheduleRequest.getStudioId()) == null) || (filmService.findOne(scheduleRequest.getFilmId())) == null) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn(REQUEST_INVALID, ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setId(scheduleRequest.getStudioId());
            scheduleEntity.setShowDate(scheduleRequest.getTanggalTayang());
            scheduleEntity.setStartTime(scheduleRequest.getJamMulai());
            scheduleEntity.setEndTime(scheduleRequest.getJamSelesai());
            scheduleEntity.setPrice(scheduleRequest.getHarga());
            scheduleEntity.setFilm(filmService.findOne(scheduleRequest.getFilmId()));
            scheduleEntity.setStudio(studioService.findOne(scheduleRequest.getStudioId()));

            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(scheduleService.save(scheduleEntity));
            logger.info("sukses create schedule id : {}, for film : {}, in studio : {}", scheduleEntity.getId(),
                    scheduleEntity.getFilm().getFilmName(), scheduleEntity.getStudio().getStudioName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get a schedule by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<ScheduleEntity>> findOne(@PathVariable("id") Long id){
        ResponseData<ScheduleEntity> responseData = new ResponseData<>();
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(scheduleService.findOne(id));
            logger.info("sukses get schedule id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get all schedules")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<ScheduleEntity>>> findAll(){
        ResponseData<Iterable<ScheduleEntity>> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(scheduleService.findAll());
            logger.info("sukses get all schedule");
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Update a schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping("/update/with/id")
    public ResponseEntity<ResponseData<ScheduleEntity>> update(@Valid @RequestBody ScheduleRequest scheduleRequest, Errors errors){
        ResponseData<ScheduleEntity> responseData = new ResponseData<>();

        if ((errors.hasErrors()) || (studioService.findOne(scheduleRequest.getStudioId()) == null) || (filmService.findOne(scheduleRequest.getFilmId())) == null) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn(REQUEST_INVALID, ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setId(scheduleRequest.getStudioId());
            scheduleEntity.setShowDate(scheduleRequest.getTanggalTayang());
            scheduleEntity.setStartTime(scheduleRequest.getJamMulai());
            scheduleEntity.setEndTime(scheduleRequest.getJamSelesai());
            scheduleEntity.setPrice(scheduleRequest.getHarga());
            scheduleEntity.setFilm(filmService.findOne(scheduleRequest.getFilmId()));
            scheduleEntity.setStudio(studioService.findOne(scheduleRequest.getStudioId()));

            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            responseData.setData(scheduleService.save(scheduleEntity));
            logger.info("sukses update schedule id : {}, for film : {}, in studio : {}", scheduleEntity.getId(),
                    scheduleEntity.getFilm().getFilmName(), scheduleEntity.getStudio().getStudioName());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Delete a schedule by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<String>> removeOne(@PathVariable("id") Long id){
        ResponseData<String> responseData = new ResponseData<>();
        try{
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            scheduleService.removeOne(id);
            logger.info("delete schedule with id : {}", id);
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @Operation(summary = "Get a schedule by its film name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "sukses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/film/name")
    public ResponseEntity<ResponseData<List<ScheduleEntity>>> findFilmName(@Valid @RequestBody SearchRequest filmName, Errors errors){
        ResponseData<List<ScheduleEntity>> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("error request : {}", ErrorParsingUtility.parse(errors));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            responseData.setStatusCode(StatusCode.OK);
            responseData.setStatus(true);
            responseData.getMessages().add(SUCCSESS);
            FilmEntity filmEntity = filmService.findByName(filmName.getSearchKey());
            responseData.setData(scheduleService.findByFilmName(filmEntity));
            logger.info("find schedule with film name : {}", filmName.getSearchKey());
            return ResponseEntity.ok(responseData);
        }catch (Exception ex){
            responseData.setStatusCode(StatusCode.INTERNAL_ERROR);
            responseData.setStatus(false);
            responseData.getMessages().add(ex.getMessage());
            logger.warn(ERROR_SERVER, ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
}
