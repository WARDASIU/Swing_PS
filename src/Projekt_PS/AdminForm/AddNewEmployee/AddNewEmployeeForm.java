package Projekt_PS.AdminForm.AddNewEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddNewEmployeeForm extends AddNewEmployeeValidation {
    private JTextField PHONE_NUMBERTextField;
    private JTextField EMAILTextField;
    private JTextField LAST_NAMETextField;
    private JTextField FIRST_NAMETextField;
    private JTextField HIRE_DATETextField;
    private JTextField EMPLOYEE_IDTextField;
    private JTextField JOB_IDTextField;
    private JTextField SALARYTextField;
    private JTextField COMMISSION_PCTTextField;
    private JPanel panel;
    private JButton addEmpButton;

    private int empID = 0;
    private String phoneNumber;
    private String email;
    private String lastName;
    private String firstName;
    private String hireDate;
    private String jobID;
    private int salary = 0;
    private double commPCT = 0;

    public AddNewEmployeeForm() {
        addEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!EMPLOYEE_IDTextField.getText().equals("")
                        && !PHONE_NUMBERTextField.getText().equals("")
                        && !EMAILTextField.getText().equals("")
                        && !LAST_NAMETextField.getText().equals("")
                        && !FIRST_NAMETextField.getText().equals("")
                        && !HIRE_DATETextField.getText().equals("")
                        && !JOB_IDTextField.getText().equals("")
                        && !SALARYTextField.getText().equals("")
                        && !COMMISSION_PCTTextField.getText().equals("")){
                    checkValidations();
                }else JOptionPane.showMessageDialog(null, "One of the inputs is empty!");
                if(!EMPLOYEE_IDTextField.getText().equals("")
                        && !PHONE_NUMBERTextField.getText().equals("")
                        && !EMAILTextField.getText().equals("")
                        && !LAST_NAMETextField.getText().equals("")
                        && !FIRST_NAMETextField.getText().equals("")
                        && !HIRE_DATETextField.getText().equals("")
                        && !JOB_IDTextField.getText().equals("")
                        && !SALARYTextField.getText().equals("")
                        && !COMMISSION_PCTTextField.getText().equals("")){
                    setVariables();
                }
            }
        });
    }

    public void showFrame(){
        JFrame addFrame = new JFrame("Add new Employee");
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setContentPane(new AddNewEmployeeForm().panel);
        addFrame.pack();
        addFrame.setSize(800,600);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    private void setVariables() {
        setCommPCT(Double.parseDouble(COMMISSION_PCTTextField.getText()));
        setEmail(EMAILTextField.getText());
        setEmpID(Integer.parseInt(EMPLOYEE_IDTextField.getText()));
        setFirstName(FIRST_NAMETextField.getText());
        setLastName(LAST_NAMETextField.getText());
        setHireDate(HIRE_DATETextField.getText());
        setJobID(JOB_IDTextField.getText());
        setPhoneNumber(PHONE_NUMBERTextField.getText());
        setSalary(Integer.parseInt(SALARYTextField.getText()));
        try {
            createQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createQuery() throws SQLException {
        String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
        String username = "msbd11";
        String password = "haslo2021";
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        Statement stmt = connection.createStatement();
        String query = "INSERT INTO EMPLOYEES_COPY " +
                "(EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT)" +
                "VALUES("+empID+",'"+firstName+"','"+lastName+"','"+email+"','"+phoneNumber+"',TO_DATE('"+hireDate+"','YYYY-MM-DD'),'"+jobID+"',"+salary+","+commPCT+")";
        int result = stmt.executeUpdate(query);
        System.out.println(result);
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCommPCT(double commPCT) {
        this.commPCT = commPCT;
    }

    private void checkValidations(){
        checkIntValidation(EMPLOYEE_IDTextField.getText(),EMPLOYEE_IDTextField);
        if(!EMPLOYEE_IDTextField.getText().equals("")){
            try {
                checkIfIDExists();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        checkIntValidation(SALARYTextField.getText(),SALARYTextField);
        checkMailValidation(EMAILTextField.getText(),EMAILTextField);
        checkFloatValidation(COMMISSION_PCTTextField.getText(),COMMISSION_PCTTextField);
        checkDateValidation(HIRE_DATETextField.getText(),HIRE_DATETextField);
        checkPhoneNumberValidation(PHONE_NUMBERTextField.getText(),PHONE_NUMBERTextField);
        checkNameValidation(FIRST_NAMETextField.getText(),FIRST_NAMETextField);
        checkNameValidation(LAST_NAMETextField.getText(),LAST_NAMETextField);
        checkJobIDValidation(JOB_IDTextField.getText(),JOB_IDTextField);
    }

    private void checkIfIDExists() throws SQLException {
        String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
        String username = "msbd11";
        String password = "haslo2021";
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        Statement stmt = connection.createStatement();
        String check = "SELECT EMPLOYEE_ID FROM EMPLOYEES_COPY WHERE EMPLOYEE_ID = "+Integer.parseInt(EMPLOYEE_IDTextField.getText())+"";
        ResultSet rs = stmt.executeQuery(check);

        while(rs.next()){
            if(EMPLOYEE_IDTextField.getText().equals(rs.getString("EMPLOYEE_ID"))){
                EMPLOYEE_IDTextField.setText("");
                JOptionPane.showMessageDialog(null,"Employee with that ID already exists!");
            }
        }
        connection.close();
    }
}
