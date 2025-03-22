interface Database {
    void save(String data);
}

class MySQLDatabase1 implements Database {

    @Override
    public void save(String data) {
        System.out.println("Saving data to MySQL: "+ data);
    }
}

class MongoDatabase implements Database {

    @Override
    public void save(String data) {
        System.out.println("Saving data to MongoDB: "+ data);
    }
}

class DBService {
    private Database database;

    DBService(Database database) {
        this.database = database;
    }

    public void saveData(String data){
        database.save(data);
    }
}

public class DependencyInversionSolution {
    public static void main(String[] args) {
        DBService dbService1 = new DBService(new MongoDatabase());
        DBService dbService2 = new DBService(new MySQLDatabase1());

        dbService1.saveData("data");
        dbService2.saveData("data");
    }
}
