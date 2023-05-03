package com.example.patientsapi.service;

import com.example.patientsapi.model.Patient;

import java.util.List;

public interface PatientService {

    Patient createPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient findById(Long patientId);
}
