package FlipMedLLD.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Doctor {

    String name;
    Speciality speciality;
    int rating;
    Set<TimeSlot> availableTimeSlots = new HashSet<>();

    public Doctor(String name, Speciality speciality, int rating) {
        this.name = name;
        this.speciality = speciality;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }
    public int getRating() {
        return rating;
    }

    public Set<TimeSlot> getAvailableTimeSlots() {
        return availableTimeSlots;
    }
}
