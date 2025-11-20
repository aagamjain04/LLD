class main{
    public static void main(String[] args) {
        System.out.println("========== BUILDER DESIGN PATTERN DEMO ==========\n");

        // Example 1: Building a Gaming Computer
        System.out.println("--- Example 1: Gaming Computer ---");
        Computer gamingPC = new Computer.ComputerBuilder("Intel i9-13900K", "32GB DDR5")
                .setStorage("2TB NVMe SSD")
                .setGraphicsCard("NVIDIA RTX 4090")
                .setMotherboard("ASUS ROG Maximus")
                .setPowerSupply("1000W 80+ Gold")
                .enableWifi()
                .enableBluetooth()
                .build();

        System.out.println(gamingPC);
        System.out.println("\n");

        // Example 2: Building a Basic Office Computer
        System.out.println("--- Example 2: Office Computer ---");
        Computer officePC = new Computer.ComputerBuilder("Intel i5-12400", "16GB DDR4")
                .setStorage("512GB SSD")
                .enableWifi()
                .build();

        System.out.println(officePC);
        System.out.println("\n");

        // Example 3: Building a Minimal Computer
        System.out.println("--- Example 3: Minimal Computer ---");
        Computer minimalPC = new Computer.ComputerBuilder("AMD Ryzen 5", "8GB DDR4")
                .build();

        System.out.println(minimalPC);
        System.out.println("\n");
    }
}