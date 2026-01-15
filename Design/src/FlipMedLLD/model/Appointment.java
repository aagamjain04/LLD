package FlipMedLLD.model;

public class Appointment {

    static int ID_GEN = 1000;

    int id;
    String doctor;
    String patient;
    TimeSlot timeSlot;

    public Appointment(String doctor, String patient, TimeSlot timeSlot) {
        this.id = ID_GEN++;
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
