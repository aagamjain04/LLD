package LoggingSystemLLD;

public class ErrorLogProcessor implements LogProcessor{

    LogProcessor nextLogProcessor;
    public  ErrorLogProcessor(LogProcessor nextLogProcessor){
        this.nextLogProcessor = nextLogProcessor;
    }

    @Override
    public void log(int level, String message) {
        if(level == LogLevel.ERROR.getLevel()){
            System.out.println("ERROR "+message);
        }else {
            nextLogProcessor.log(level,message);
        }

    }
}
