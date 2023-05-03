package com.example.patientsapi.model.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PatientDto {
    private Long id;
//    @NotBlank(message = "Name cannot be empty")
    private String name;
//    @NotBlank(message = "gender cannot be empty")
    private String gender;
//    @NotNull(message = "age cannot be empty")
    private Integer age;
//    @NotBlank(message = "location cannot be empty")
    private String location;

}
