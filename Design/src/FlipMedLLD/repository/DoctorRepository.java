package FlipMedLLD.repository;

import FlipMedLLD.model.Doctor;

import java.util.Collection;

public interface DoctorRepository {
        void save(Doctor doctor);
        Doctor get(String name);
        Collection<Doctor> getAll();
}
