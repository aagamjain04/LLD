package FlipMedLLD.Strategy;

import FlipMedLLD.dto.DoctorSlotView;

import java.util.Comparator;
import java.util.List;

public class SortByTimeStrategy implements SortStrategy{
    @Override
    public void sort(List<DoctorSlotView> doctorSlotViewList) {
        doctorSlotViewList.sort(Comparator.comparingDouble(t -> t.getTimeSlot().getStartTime()));
    }
}
