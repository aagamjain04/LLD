class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving data to MySQL:" + data);
    }
}

class UserService {
    private  MySQLDatabase mySQLDatabase;

    UserService() {
        this.mySQLDatabase = new MySQLDatabase();
    }

    public void saveUser(String userData) {
        mySQLDatabase.save(userData);
    }
}


public class DependencyInversionViolation {

    public static void main(String[] args) {

        UserService service = new UserService();
        service.saveUser("userData");

    }
}
