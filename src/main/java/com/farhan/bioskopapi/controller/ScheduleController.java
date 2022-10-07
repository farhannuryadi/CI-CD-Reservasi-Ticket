package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.request.ScheduleRequest;
import com.farhan.bioskopapi.dto.request.SearchRequest;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.service.FilmService;
import com.farhan.bioskopapi.service.ScheduleService;
import com.farhan.bioskopapi.service.StudioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bioskop/api/schedules")
@Tag(name = "Schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    private FilmService filmService;

    private StudioService studioService;

    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @Autowired
    public void setStudioService(StudioService studioService) {
        this.studioService = studioService;
    }

    @PostMapping("/with/data")
    public ResponseEntity<ResponseData<ScheduleEntity>> create(@Valid @RequestBody ScheduleEntity scheduleEntity, Errors errors){

        ResponseData<ScheduleEntity> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        filmService.save(scheduleEntity.getFilm());
        studioService.save(scheduleEntity.getStudio());

        responseData.setStatusCode(StatusCode.OK);
        responseData.setStatus(true);
        responseData.getMessages().add("sukses");
        responseData.setData(scheduleService.save(scheduleEntity));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/with/id")
    public ResponseEntity<ResponseData<ScheduleEntity>> create(@Valid @RequestBody ScheduleRequest scheduleRequest, Errors errors){

        ResponseData<ScheduleEntity> responseData = new ResponseData<>();

        if ((errors.hasErrors()) || (studioService.findOne(scheduleRequest.getStudioId()) == null) || (filmService.findOne(scheduleRequest.getFilmId())) == null) {
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

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
        responseData.getMessages().add("sukses");
        responseData.setData(scheduleService.save(scheduleEntity));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ScheduleEntity findOne(@PathVariable("id") Long id){
        return scheduleService.findOne(id);
    }

    @GetMapping
    public Iterable<ScheduleEntity> findAll(){
        return scheduleService.findAll();
    }

    @PutMapping
    public ScheduleEntity update(@RequestBody ScheduleEntity scheduleEntity){
        return scheduleService.save(scheduleEntity);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        scheduleService.removeOne(id);
    }

    @PostMapping("/film/name")
    public ResponseEntity<ResponseData<List<ScheduleEntity>>> findFilmName(@RequestBody SearchRequest filmName, Errors errors){
        ResponseData<List<ScheduleEntity>> responseData = new ResponseData<>();
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
        FilmEntity filmEntity = filmService.findByName(filmName.getSearchKey());
        responseData.setData(scheduleService.findByFilmName(filmEntity));
        return ResponseEntity.ok(responseData);
    }
}
