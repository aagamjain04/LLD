public class EmployeeDaoProxy implements EmployeeDao{

    EmployeeDao employeeDao;
    EmployeeDaoProxy(){
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void create(String client, int employeeId) throws Exception {
        if(client.equals("ADMIN")) {
            employeeDao.create(client,employeeId);
            return;
        }
        throw new Exception("Access Denied");

    }

    @Override
    public void delete(String client, int employeeId) throws Exception {
        if(client.equals("ADMIN")) {
            employeeDao.delete(client,employeeId);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public void get(String client, int employeeId) throws Exception {
        if(client.equals("ADMIN") || client.equals("USER")) {
            employeeDao.get(client,employeeId);
            return;
        }
        throw new Exception("Access Denied");
    }
}
