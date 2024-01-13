package Kata4;

import java.sql.SQLException;

public class Business {
    public static void main(String[] args) throws SQLException {
        Employee a = new Employee("100","Director","Female");
        a.reademployeedatabase();
        a.showemployees();


    }

}
