package FlipMedLLD.model;

import java.util.Objects;

public class TimeSlot {

    double startTime;
    double endTime;

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public TimeSlot(double startTime, double endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("(%.2f-%.2f)",startTime,endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return Double.compare(startTime, timeSlot.startTime) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(startTime);
    }
}
