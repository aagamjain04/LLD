package FlipMedLLD.service;

import CricbuzzLLD.Match;
import FlipMedLLD.Strategy.SortStrategy;
import FlipMedLLD.dto.DoctorSlotView;
import FlipMedLLD.model.*;
import FlipMedLLD.repository.DoctorRepository;
import FlipMedLLD.repository.PatientRepository;

import java.util.*;

public class AppointmentServiceImpl implements AppointmentService{



    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private Map<Integer,Appointment> appointmentMap = new HashMap<>();
    private SortStrategy sortStrategy;



    public AppointmentServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository,SortStrategy sortStrategy) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.sortStrategy = sortStrategy;
    }

    @Override
    public void registerDoc(String name, Speciality speciality) {
        Random random = new Random();
        doctorRepository.save(new Doctor(name,speciality, random.nextInt(5)+1 ));
        System.out.printf("Welcome Dr. %s !!%n",name);

    }

    @Override
    public void makeDocAvail(String name, List<TimeSlot> slots) {


        Doctor d = doctorRepository.get(name);

        for(TimeSlot slot : slots){
            double startTime = slot.getStartTime();
            double endTime = slot.getEndTime();
            if(endTime-startTime!=1){
                System.out.printf("Sorry Dr. %s slots are 60 mins only",name);
                continue;
            }
            d.getAvailableTimeSlots().add(slot);
        }
        System.out.println("Done Doc!");
    }

    @Override
    public void showAvailByspeciality(Speciality speciality) {

        List<DoctorSlotView> doctorSlotViewList = new ArrayList<>();


        for(Doctor d : doctorRepository.getAll()){
            if(d.getSpeciality() == speciality){

                for(TimeSlot t : d.getAvailableTimeSlots()){
                    doctorSlotViewList.add(new DoctorSlotView(d.getName(),t,d.getRating()));
                }
            }
        }

        sortStrategy.sort(doctorSlotViewList);
        for(DoctorSlotView d: doctorSlotViewList){
            System.out.printf("Dr %s: %s\n",d.getName(),d.getTimeSlot().toString());
        }
    }

    @Override
    public void registerPatient(String name) {
        patientRepository.save(new Patient(name));
        System.out.printf("%s registered successfully.\n",name);
    }

    @Override
    public void bookAppointment(String patientName, String doctorName, double startTime) {

        Patient p = patientRepository.get(patientName);
        Doctor d = doctorRepository.get(doctorName);

        // check if doctor has this timeslot
        boolean docAvailable = false;
        for(TimeSlot t : d.getAvailableTimeSlots()){
            if (t.getStartTime() == startTime) {
                docAvailable = true;
                break;
            }
        }
        if(!docAvailable){
            System.out.println("Slot not available");
            return;
        }

        //check if patient already has overlapping booking

        for(Appointment appointment : p.getAppointments()){
            if(appointment.getTimeSlot().getStartTime()==startTime){
                System.out.println("Patient already has booking at this time");
                return;
            }
        }

        TimeSlot slot = new TimeSlot(startTime,startTime+1);


        Appointment appointment = new Appointment(d.getName(),p.getName(),slot);

        p.getAppointments().add(appointment);
        d.getAvailableTimeSlots().remove(slot);
        appointmentMap.put(appointment.getId(),appointment);

        System.out.println("Booked. Booking id: " + appointment.getId());
    }

    @Override
    public void cancelBookingId(int id) {

        Appointment ap = appointmentMap.get(id);
        TimeSlot slot = ap.getTimeSlot();
        appointmentMap.remove(id);

        Patient p = patientRepository.get(ap.getPatient());
        p.getAppointments().remove(ap);

        Doctor d = doctorRepository.get(ap.getDoctor());
        d.getAvailableTimeSlots().add(slot);

        System.out.println("Booking Cancelled");

    }

}
