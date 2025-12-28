
// Concrete command - OFF
public class LightOnCommand implements Command{

    private Light light;
    private boolean prevState;

    public LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        prevState = light.isOn();
        light.on();
    }

    @Override
    public void undo() {
        if(!prevState){
            light.off();
        }
    }
}
