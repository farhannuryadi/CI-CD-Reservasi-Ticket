package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.dto.request.ScheduleDto;
import com.farhan.bioskopapi.dto.request.SearchDto;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.ScheduleEntity;
import com.farhan.bioskopapi.service.FilmService;
import com.farhan.bioskopapi.service.ScheduleService;
import com.farhan.bioskopapi.service.StudioService;
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
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        filmService.save(scheduleEntity.getFilm());
        studioService.save(scheduleEntity.getStudio());

        responseData.setStatus(true);
        responseData.getMessages().add("sukses");
        responseData.setData(scheduleService.save(scheduleEntity));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/with/id")
    public ResponseEntity<ResponseData<ScheduleEntity>> create(@Valid @RequestBody ScheduleDto scheduleDto, Errors errors){

        ResponseData<ScheduleEntity> responseData = new ResponseData<>();

        if ((errors.hasErrors()) || (studioService.findOne(scheduleDto.getStudioId()) == null) || (filmService.findOne(scheduleDto.getFilmId())) == null) {
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(scheduleDto.getStudioId());
        scheduleEntity.setTanggalTayang(scheduleDto.getTanggalTayang());
        scheduleEntity.setJamMulai(scheduleDto.getJamMulai());
        scheduleEntity.setJamSelesai(scheduleDto.getJamSelesai());
        scheduleEntity.setHarga(scheduleDto.getHarga());
        scheduleEntity.setFilm(filmService.findOne(scheduleDto.getFilmId()));
        scheduleEntity.setStudio(studioService.findOne(scheduleDto.getStudioId()));

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
    public ResponseEntity<ResponseData<List<ScheduleEntity>>> findFilmName(@RequestBody SearchDto filmName, Errors errors){
        ResponseData<List<ScheduleEntity>> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.getMessages().add("sukses");
        FilmEntity filmEntity = filmService.findByName(filmName.getSearchKey());
        responseData.setData(scheduleService.findByFilmName(filmEntity));
        return ResponseEntity.ok(responseData);
    }
}
