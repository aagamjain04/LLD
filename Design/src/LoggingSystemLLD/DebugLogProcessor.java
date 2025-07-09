package LoggingSystemLLD;

public class DebugLogProcessor implements LogProcessor{


    LogProcessor nextLogProcessor;

    public DebugLogProcessor(LogProcessor nextLogProcessor){
        this.nextLogProcessor = nextLogProcessor;
    }

    @Override
    public void log(int level, String message) {
        if(level == LogLevel.DEBUG.getLevel()){
            System.out.println("DEBUG "+message);
        }else {
            nextLogProcessor.log(level,message);
        }
    }
}
