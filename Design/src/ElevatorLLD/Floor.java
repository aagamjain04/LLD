package ElevatorLLD;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private List<Display> displays;
    private List<HallButton> panels;

    public Floor() {
        this.displays = new ArrayList<>();
        this.panels = new ArrayList<>();
    }

    public boolean isBottomMost() {
        return displays.get(0).getFloor() == 1;
    }

    public boolean isTopMost() {
        return displays.get(0).getFloor() == 10; // Assuming 10 is the top floor
    }

    public void addDisplays(Display display) {
        displays.add(display);
    }

    public void addPanel(HallButton panel) {
        panels.add(panel);
    }

    public List<Display> getDisplays() {
        return displays;
    }

    public List<HallButton> getPanels() {
        return panels;
    }
}
