package FlipMedLLD.repository;

import FlipMedLLD.model.Patient;

import java.util.HashMap;

public class InMemoryPatientRepo implements PatientRepository{

    private HashMap<String,Patient> map = new HashMap<>();
    @Override
    public void save(Patient patient) {
        map.put(patient.getName(),patient);
    }

    @Override
    public Patient get(String name) {
        return map.getOrDefault(name,null);
    }
}
