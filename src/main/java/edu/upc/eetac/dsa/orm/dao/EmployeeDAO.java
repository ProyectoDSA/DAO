package edu.upc.eetac.dsa.orm.dao;

import edu.upc.eetac.dsa.orm.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    public int addEmployee(String name, String surname, double salary);
    public Employee getEmployee(int employeeID);
    public void updateEmployee(int employeeID, String name, String surname, double salary);
    public void deleteEmployee(int employeeID);
    public List<Employee> getEmployees();
    public List<Employee> getEmployeeByDept(int deptId);

}
