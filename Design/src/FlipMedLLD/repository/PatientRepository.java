package FlipMedLLD.repository;

import FlipMedLLD.model.Patient;

public interface PatientRepository {
    void save(Patient patient);
    Patient get(String name);
}
