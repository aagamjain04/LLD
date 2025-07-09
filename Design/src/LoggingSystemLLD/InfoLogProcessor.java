package LoggingSystemLLD;

public class InfoLogProcessor implements LogProcessor{

    LogProcessor nextLogProcessor;

    public InfoLogProcessor(LogProcessor nextLogProcessor){
        this.nextLogProcessor = nextLogProcessor;
    }

    @Override
    public void log(int level, String message) {
        if(level == LogLevel.INFO.getLevel()){
            System.out.println("INFO "+message);
        }else{
            nextLogProcessor.log(level, message);
        }
    }
}
