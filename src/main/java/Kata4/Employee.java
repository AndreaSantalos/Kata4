package Kata4;

import java.sql.*;
import java.util.Objects;

public class Employee implements EmployeeLoader, GenderCounter,ReadDatabase{
    private String id;
    private String job;
    private String gender;

    public Employee(String id, String job, String gender) {
        this.id = id;
        this.job = job;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(job, employee.job) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, job, gender);
    }

    @Override
    public String toString() {
        return "Kata4.Employee{" +
                "id='" + id + '\'' +
                ", job='" + job + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }


    public void  showemployees(){
        EmployeeLoader.employees.forEach(System.out::println);
    }



    @Override
    public void employeeload(Employee e) {
        EmployeeLoader.employees.add(e);
    }

    @Override
    public int femalecount() {

        return (int) EmployeeLoader.employees.stream().filter(p ->p.getGender().equals("Female")).count();
    }

    @Override
    public int malecount() {

        return (int) EmployeeLoader.employees.stream().filter(p ->p.getGender().equals("Male")).count();
    }

    @Override
    public void totalcount() {
        totalgenderemployees.put("Female",femalecount());
        totalgenderemployees.put("Male",malecount());
    }


    @Override
    public void reademployeedatabase() throws SQLException {
        String url = "jdbc:sqlite:src/main/resources/employees.sqlite";
        try(Connection c = DriverManager.getConnection(url);Statement s = c.createStatement();){
            ResultSet lecture = s.executeQuery("SELECT*FROM Employees2");
            while(lecture.next()){
                employeeload(new Employee(lecture.getString("EmployeeId"),lecture.getString("Job"),
                        lecture.getString("Gender")));
            }
        }
    }
}
