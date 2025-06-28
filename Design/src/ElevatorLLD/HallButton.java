package ElevatorLLD;

public class HallButton {

    private Direction buttonSign;
    private int sourceFloorNumber;

    public HallButton(Direction buttonSign, int sourceFloorNumber) {
        this.buttonSign = buttonSign;
        this.sourceFloorNumber = sourceFloorNumber;
    }

    public Direction getButtonSign() {
        return buttonSign;
    }

    public void setButtonSign(Direction buttonSign) {
        this.buttonSign = buttonSign;
    }

    public int getSourceFloorNumber() {
        return sourceFloorNumber;
    }

    public void setSourceFloorNumber(int sourceFloorNumber) {
        this.sourceFloorNumber = sourceFloorNumber;
    }
}
