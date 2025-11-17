public class main {
    public static void main(String[] args) {
        ThreePinCharger oldCharger = new ThreePinCharger();

        Charger adapter = new Adapter(oldCharger);
        adapter.charge();

        // NOTE : The client still only knows Charger, not ThreePinCharger.
    }
}
