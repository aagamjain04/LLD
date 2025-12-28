
// Receiver
public class Light {
    boolean isOn = false;

    public void on(){
        isOn = true;
        System.out.println("Light is ON");
    }

    public void off(){
        isOn = false;
        System.out.println("Light is OFF");
    }
    public boolean isOn(){
        return isOn;
    }



}
