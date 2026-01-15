package FlipMedLLD.Strategy;

import FlipMedLLD.dto.DoctorSlotView;

import java.util.List;

public interface SortStrategy {

    void sort(List<DoctorSlotView> doctorSlotViewList);
}
