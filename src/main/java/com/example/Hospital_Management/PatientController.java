package com.example.Hospital_Management;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class PatientController {
    HashMap<Integer,Patient> hashmap = new HashMap<>();
    @PostMapping("/addPatient")
    public String addPatient(@RequestParam("patientId") Integer patientId,@RequestParam("name") String name, @RequestParam("age") Integer age,@RequestParam("disease") String disease){
          Patient patient = new Patient(patientId,name,disease,age);
          hashmap.put(patientId, patient);
          return "Patient added Successfully";
    }


    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient){
        int key = patient.getPatientId();
        hashmap.put(key,patient);
        return "Patient added successfully via RequestBody";
    }
    @GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam("patientId") Integer patientId){
        Patient patient = hashmap.get(patientId);
        return patient;
    }

    @GetMapping("/getAllPatient")
    public List<Patient> getAllPatient(){
        List<Patient> patients = new ArrayList<>();

        for(Patient p:hashmap.values()){
            patients.add(p);
        }
        return patients;
    }

    @GetMapping("/getPatientByName")
    public Patient getpatientbyname(@RequestParam("name") String name){

        for(Patient p:hashmap.values()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    @GetMapping("/getPatientListGreaterThanAge")
    public List<Patient> getPatientGreaterThanAge(@RequestParam("age") Integer age){
        List<Patient> patients = new ArrayList<>();

        for(Patient p:hashmap.values()){
            if(p.getAge() > age){
                patients.add(p);
            }
        }
        return patients;
    }
}
