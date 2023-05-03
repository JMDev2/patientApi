package com.example.patientsapi.service.impl;


import com.example.patientsapi.model.Patient;
import com.example.patientsapi.repository.PatientRepository;
import com.example.patientsapi.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<Patient> getAllTopics(){


         List<Patient> patients = new ArrayList<>();
         patientRepository.findAll()
                 .forEach(patients::add);
         return patients;
     }

     //post
     @Override
     public Patient createPatient(Patient patient) {
        patient.setName(patient.getName());
        patient.setGender(patient.getGender());
        patient.setLocation(patient.getLocation());
        patient.setAge(patient.getAge());

        Patient newPatient = patientRepository.save(patient);
        return newPatient;
     }

    //getall
    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patient = patientRepository.findAll();

        if (patient.isEmpty()){
            System.out.println("Patient list is empty");
        }else {
            System.out.println("Patient list retrieved succesfully");
        }

        return patient;
    }


    //findByid
    @Override
    public Patient findById(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);

        if (patient.isPresent()) {
            System.out.println("Fetched patient by ID succesfully");

        } else {
            System.out.println("Patient with id" + patientId + "not found");
        }return patient.get();
    }
}
