package com.example.patientsapi.controller;


import com.example.patientsapi.model.Patient;
import com.example.patientsapi.model.dto.PatientDto;
import com.example.patientsapi.repository.PatientRepository;
import com.example.patientsapi.service.PatientService;
import com.example.patientsapi.service.impl.PatientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper; //map dto to the model

    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }


    //post
    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) {
        Patient patientRequest = modelMapper.map(patientDto, Patient.class);
        Patient patient = patientService.createPatient(patientRequest);
        PatientDto patientResponse = modelMapper.map(patient, PatientDto.class);
        if (patient == null) {
            System.out.println("Patient not saved");
        } else {
            System.out.println("Patient saved successfully");

        }
        return new ResponseEntity<PatientDto>(patientResponse, HttpStatus.CREATED);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatient(){
        System.out.println("Fteched income succesfully now");

        return ResponseEntity.ok(patientService.getAllPatients().stream()
        .map(patient -> modelMapper.map(patient, PatientDto.class))
        .collect(Collectors.toList()));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> findPatientById(@PathVariable Long patientId){
        Patient patient = patientService.findById(patientId);
        PatientDto patientResponse = modelMapper.map(patient, PatientDto.class);
        if (patientResponse !=null){
            System.out.println("Patient has been fetched with ID "+ patientId);

        }else {
            System.out.println("Unable to fetch patient with ID "+ patientId);
        }
        return ResponseEntity.ok().body(patientResponse);
    }


}
