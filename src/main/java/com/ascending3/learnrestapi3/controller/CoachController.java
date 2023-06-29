package com.ascending3.learnrestapi3.controller;

import com.ascending3.learnrestapi3.dto.CoachDto;
import com.ascending3.learnrestapi3.exception.ExceptionResponse;
import com.ascending3.learnrestapi3.exception.ItemNotFoundException;
import com.ascending3.learnrestapi3.service.CoachService;
import com.ascending3.learnrestapi3.service.CourseService;
import com.ascending3.learnrestapi3.service.Impl.CoachServiceImpl;
import com.ascending3.learnrestapi3.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/project")
public class CoachController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CoachService coachService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/coaches", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoachDto>> findAllCoaches(){
        List<CoachDto> coachDtoList = coachService.getCoaches();
        return new ResponseEntity<>(coachDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/coaches/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findCoachDtoByCoachId(@PathVariable("id") Long coachId){
        CoachDto coachDto = null;
        ResponseEntity<Object> responseEntity = null;
        try {
            coachDto = coachService.getCoachById(coachId);
            responseEntity = new ResponseEntity<>(coachDto, HttpStatus.OK);
        } catch (ItemNotFoundException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse("ItemNotFoundException", LocalDateTime.now(), e.getMessage());
            responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping(value = "/coaches/param_practice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findCoachDtoByRequestParamCoachId(@RequestParam("id") Long coachId){
        CoachDto coachDto = null;
        ResponseEntity<Object> responseEntity = null;
        try {
            coachDto = coachService.getCoachById(coachId);
            responseEntity = new ResponseEntity<>(coachDto, HttpStatus.OK);
        } catch (ItemNotFoundException e){
            ExceptionResponse exceptionResponse = new ExceptionResponse("ItemNotFoundException", LocalDateTime.now(), e.getMessage());
            responseEntity = new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

//    @PostMapping(value = "/coaches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<CoachDto> saveCoachDto(@RequestBody CoachDto coachDto){
//        CoachDto savedCoachDto = coachService.save(coachDto);
//        return new ResponseEntity(savedCoachDto, HttpStatus.CREATED);
//    }

//    @PostMapping(value = "/coaches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PostMapping(value = "/coaches", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<CoachDto> saveCoachDto(@RequestBody CoachDto coachDto){
//        CoachDto savedCoachDto = coachService.save(coachDto);
//
//        URI urilocation = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(savedCoachDto.getId())
//                .toUri();
//        logger.info("===================, before return URI value = {}", urilocation);
//        return ResponseEntity.created(urilocation).body(savedCoachDto);
//    }

    @PostMapping(value = "/coaches", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CoachDto> createCoach(@RequestBody CoachDto coachDto){
        CoachDto savedCoachDto = coachService.save(coachDto);

        URI urilocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCoachDto.getId())
                .toUri();
        logger.info("===================, before return URI value = {}", urilocation);
        return ResponseEntity.created(urilocation).body(savedCoachDto);
    }

    @PutMapping(path = "/coaches", consumes = "application/json", produces = "application/json")
//    public CoachDto updateCoachDto(@RequestBody CoachDto coachDto){
//        CoachDto updatedCoachDto = coachService.update(coachDto);
//        return updatedCoachDto;
//    }
    public ResponseEntity<CoachDto> updateCoachDto(@RequestBody CoachDto coachDto){
        CoachDto updatedCoachDto = coachService.update(coachDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedCoachDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(updatedCoachDto);
    }

    @DeleteMapping(value = "/coaches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteCoachDto(@RequestBody CoachDto coachDto){
        boolean deleteResult = coachService.delete(coachDto);
        if(deleteResult)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @DeleteMapping(value = "/coaches/{id}", produces = "application/json")
    public ResponseEntity deleteCoachByCoachId(@PathVariable("id") Long coachId){
        boolean deleteResult = coachService.deleteById(coachId);
        if(deleteResult)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

}
