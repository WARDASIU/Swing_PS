package Projekt_PS.AdminForm;

import Projekt_PS.AdminForm.AddNewEmployee.AddNewEmployeeForm;
import Projekt_PS.AdminForm.DeleteEmployee.DeleteExistingEmployeeForm;

import javax.swing.*;
import java.sql.*;

public class AdminFormController {
    public String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
    public String username = "msbd11";
    public String password = "haslo2021";

    public Connection connection;

    public void displayEmpTable(JTextArea area) throws SQLException{
        setConnection();

        String query = "SELECT * FROM EMPLOYEES_COPY";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        try {
            while (rs.next()) {

                int empID = rs.getInt("EMPLOYEE_ID");
                String firstName = rs.getString("FIRST_NAME");
                String lastName = rs.getString("LAST_NAME");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONE_NUMBER");
                String hireDate = rs.getString("HIRE_DATE");
                String jobId = rs.getString("JOB_ID");
                int salary = rs.getInt("SALARY");
                float commisionPct = rs.getFloat("COMMISSION_PCT");
                int managerId = rs.getInt("MANAGER_ID");
                int departmentId = rs.getInt("DEPARTMENT_ID");

                area.append(empID + " | " + firstName + " | " + lastName
                        + " | " + email + " | " + phoneNumber + " | " + hireDate
                        + " | " + jobId + " | " + salary + " | " + commisionPct + "\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
    }

    private void setConnection() {
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addNewEmployee(){
        AddNewEmployeeForm addNewEmployeeForm = new AddNewEmployeeForm();
        addNewEmployeeForm.showFrame();
    }
    public void deleteExistingEmployee(){
        DeleteExistingEmployeeForm deleteExistingEmployeeForm = new DeleteExistingEmployeeForm();
        deleteExistingEmployeeForm.showFrame();
    }
    public void refreshTable(JTextArea area){
        area.setText("");
        try {
            displayEmpTable(area);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
