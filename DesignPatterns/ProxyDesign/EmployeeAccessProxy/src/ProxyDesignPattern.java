public class ProxyDesignPattern {
    public static void main(String[] args) {

        EmployeeDao employeeDao = new EmployeeDaoProxy();
        try {
            employeeDao.create("ADMIN",1);
            System.out.println("Operation Successful");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            employeeDao.delete("USER",1);
            System.out.println("Operation Successful");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            employeeDao.get("USER",1);
            System.out.println("Operation Successful");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
