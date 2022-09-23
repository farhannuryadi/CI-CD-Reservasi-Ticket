package com.farhan.bioskopapi.controller;

import com.farhan.bioskopapi.dto.ResponseData;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.StudioEntity;
import com.farhan.bioskopapi.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bioskop/api/studios")
public class StudioController {

    private StudioService studioService;

    @Autowired
    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @PostMapping
    public ResponseEntity<ResponseData<StudioEntity>> create(@Valid @RequestBody StudioEntity studioEntity, Errors errors){

        ResponseData<StudioEntity> responseData = new ResponseData<>();

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
        responseData.setData(studioService.save(studioEntity));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public StudioEntity findOne(@PathVariable("id") Long id){
        return studioService.findOne(id);
    }

    @GetMapping
    public Iterable<StudioEntity> findAll(){
        return studioService.findAll();
    }

    @PutMapping
    public StudioEntity update(@RequestBody StudioEntity studioEntity){
        return studioService.save(studioEntity);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        studioService.removeOne(id);
    }
}
