
// Concrete command - OFF
public class LightOffCommand implements Command{

    private Light light;
    private boolean prevState;

    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        prevState = light.isOn();
        light.off();
    }

    @Override
    public void undo() {
        if(prevState){
            light.on();
        }
    }
}
