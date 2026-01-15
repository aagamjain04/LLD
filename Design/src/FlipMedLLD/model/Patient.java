package FlipMedLLD.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    String name;
    List<Appointment> appointments = new ArrayList<>();

    public Patient(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
