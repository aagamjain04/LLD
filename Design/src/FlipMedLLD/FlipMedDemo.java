package FlipMedLLD;

import FlipMedLLD.Strategy.SortByTimeStrategy;
import FlipMedLLD.Strategy.SortStrategy;
import FlipMedLLD.model.Speciality;
import FlipMedLLD.model.TimeSlot;
import FlipMedLLD.repository.DoctorRepository;
import FlipMedLLD.repository.InMemoryDoctorRepo;
import FlipMedLLD.repository.InMemoryPatientRepo;
import FlipMedLLD.repository.PatientRepository;
import FlipMedLLD.service.AppointmentService;
import FlipMedLLD.service.AppointmentServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class FlipMedDemo {
    public static void main(String[] args) {
        DoctorRepository doctorRepository = new InMemoryDoctorRepo();
        PatientRepository patientRepository = new InMemoryPatientRepo();
        SortStrategy sortStrategy = new SortByTimeStrategy();

        AppointmentService service = new AppointmentServiceImpl(doctorRepository,patientRepository,sortStrategy);

        service.registerDoc("Curious", Speciality.CARDIOLOGIST);
        service.makeDocAvail("Curious", List.of(new TimeSlot(9,10.30)));
        service.makeDocAvail("Curious",List.of(new TimeSlot(9,10),new TimeSlot(12,13), new TimeSlot(16,17)));
        service.registerDoc("Dreadful",Speciality.DERMATOLOGIST);
        service.makeDocAvail("Dreadful", List.of(new TimeSlot(9,10),new TimeSlot(11,12),new TimeSlot(13,14)));
        service.showAvailByspeciality(Speciality.CARDIOLOGIST);
        service.registerPatient("PatientA");
        service.bookAppointment("PatientA", "Curious", 12);
        service.showAvailByspeciality(Speciality.CARDIOLOGIST);
        service.cancelBookingId(1000);
        service.showAvailByspeciality(Speciality.CARDIOLOGIST);
        service.registerPatient("PatientB");
        service.bookAppointment("PatientB", "Curious", 12);
        service.registerDoc("Daring",Speciality.DERMATOLOGIST);
        service.makeDocAvail("Daring",List.of(new TimeSlot(11,12), new TimeSlot(14,15)));
        service.registerPatient("PatientF");
        service.bookAppointment("PatientF", "Daring", 11);
        service.showAvailByspeciality(Speciality.DERMATOLOGIST);


    }
}
