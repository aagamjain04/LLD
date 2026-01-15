package FlipMedLLD.dto;

import FlipMedLLD.model.TimeSlot;

public class DoctorSlotView {
    String name;
    TimeSlot timeSlot;
    int rating;

    public String getName() {
        return name;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public int getRating() {
        return rating;
    }

    public DoctorSlotView(String name, TimeSlot timeSlot, int rating) {
        this.name = name;
        this.timeSlot = timeSlot;
        this.rating = rating;
    }


}
