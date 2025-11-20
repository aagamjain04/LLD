public class Computer {
    // required parameters
    private String CPU;
    private String RAM;

    // optional parameters
    private String storage;
    private String graphicsCard;
    private String powerSupply;
    private String motherboard;
    private boolean isWifiEnabled;
    private boolean isBluetoothEnabled;

    // Private constructor to enforce object creation through the Builder
    private Computer(ComputerBuilder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.powerSupply = builder.powerSupply;
        this.motherboard = builder.motherboard;
        this.isWifiEnabled = builder.isWifiEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer Configuration:\n" +
                "CPU: " + CPU + "\n" +
                "RAM: " + RAM + "\n" +
                "Storage: " + (storage != null ? storage : "Not specified") + "\n" +
                "Graphics Card: " + (graphicsCard != null ? graphicsCard : "Integrated") + "\n" +
                "Motherboard: " + (motherboard != null ? motherboard : "Not specified") + "\n" +
                "Power Supply: " + (powerSupply != null ? powerSupply : "Not specified") + "\n" +
                "WiFi Enabled: " + isWifiEnabled + "\n" +
                "Bluetooth Enabled: " + isBluetoothEnabled;
    }


    public static class ComputerBuilder {
        // required parameters
        private String CPU;
        private String RAM;

        // Optional parameters - initialized to default values
        private String storage;
        private String graphicsCard;
        private String motherboard;
        private String powerSupply;
        private boolean isWifiEnabled = false;
        private boolean isBluetoothEnabled = false;

        // Builder constructor with required parameters
        public ComputerBuilder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        public ComputerBuilder setStorage(String storage){
            this.storage = storage;
            return this;
        }
        public ComputerBuilder setGraphicsCard(String graphicsCard){
            this.graphicsCard = graphicsCard;
            return this;
        }
        public ComputerBuilder setPowerSupply(String powerSupply){
            this.powerSupply = powerSupply;
            return this;
        }
        public ComputerBuilder setMotherboard(String motherboard){
            this.motherboard = motherboard;
            return this;
        }
        public ComputerBuilder enableWifi(){
            this.isWifiEnabled = true;
            return this;
        }
        public ComputerBuilder enableBluetooth(){
            this.isBluetoothEnabled = true;
            return this;
        }

        // build method to create Computer object
        public Computer build(){
            return new Computer(this);
        }
    }

}
