package LoggingSystemLLD;

public enum LogLevel {
    INFO(1),
    DEBUG(2),
    ERROR(3);

    private final int level;
    LogLevel(int level) {
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
