public class Client1 {
    public static void main(String[] args) {

        Light livingRoomLight = new Light();

        Command onCommand = new LightOnCommand(livingRoomLight);
        Command offCommand = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        remote.pressButton(onCommand); // Light ON
        remote.pressButton(offCommand); // Light OFF

        remote.pressUndo(); // Light ON
        remote.pressUndo(); // Light OFF


    }
}
