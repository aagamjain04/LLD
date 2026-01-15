package FlipMedLLD.service;

import FlipMedLLD.model.Speciality;
import FlipMedLLD.model.TimeSlot;

import java.sql.Time;
import java.util.List;

public interface AppointmentService {
    void registerDoc(String name, Speciality speciality);
    void makeDocAvail(String name, List<TimeSlot> slots);

    void showAvailByspeciality(Speciality speciality);

    void registerPatient(String name);

    void bookAppointment(String patientName,String doctorName,double startTime);

    void cancelBookingId(int id);
}
