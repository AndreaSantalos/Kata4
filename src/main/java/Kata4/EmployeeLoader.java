package Kata4;

import Kata4.Employee;

import java.util.ArrayList;

public interface EmployeeLoader {
    ArrayList<Employee> employees = new ArrayList<>();
    public void employeeload(Employee e);
}