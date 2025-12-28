import java.util.Stack;


// Invoker with command history
public class RemoteControl {

    private Stack<Command> history = new Stack<>();

    public void pressButton(Command command){
        command.execute();
        history.push(command);
    }

    public void pressUndo(){
        if(!history.isEmpty()){
            history.pop().undo();
        }
    }
}
