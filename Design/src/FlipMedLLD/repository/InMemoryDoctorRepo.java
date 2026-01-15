package FlipMedLLD.repository;

import FlipMedLLD.model.Doctor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class InMemoryDoctorRepo implements  DoctorRepository{

    private HashMap<String,Doctor> map = new HashMap<>();

    @Override
    public void save(Doctor doctor) {
        map.put(doctor.getName(),doctor);
    }

    @Override
    public Doctor get(String name) {
        return map.getOrDefault(name,null);
    }

    @Override
    public Collection<Doctor> getAll() {
        return map.values();
    }
}
