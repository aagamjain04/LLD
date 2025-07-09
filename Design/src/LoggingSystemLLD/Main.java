package LoggingSystemLLD;

import static LoggingSystemLLD.LogProcessor.*;

public class Main {

    public static void main(String[] args) {

        LogProcessor logProcessor = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logProcessor.log(LogLevel.INFO.getLevel(), "This is info logger");
        logProcessor.log(LogLevel.DEBUG.getLevel(), "This is debug logger");
        logProcessor.log(LogLevel.ERROR.getLevel(), "This is error logger");
    }
}
