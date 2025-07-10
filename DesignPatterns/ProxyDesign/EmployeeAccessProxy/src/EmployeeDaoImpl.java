public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void create(String client, int employeeId) {
        System.out.println("Created new row in the employee table");
    }

    @Override
    public void delete(String client, int employeeId) {
        System.out.println("Deleted row in the employee table");
    }

    @Override
    public void get(String client, int employeeId) {
        System.out.println("Fetching data from DB");
    }
}
