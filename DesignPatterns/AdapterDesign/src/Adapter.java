public class Adapter implements Charger{

    ThreePinCharger threePinCharger;

    public Adapter(ThreePinCharger threePinCharger) {
        this.threePinCharger = threePinCharger;
    }

    @Override
    public void charge() {
        threePinCharger.threePinCharge();
    }
}
