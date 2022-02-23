package Projekt_PS.WorkerForm;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class WorkerFormController extends WorkerFormValidation{
    private final String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
    private final String username = "msbd11";
    private final String password = "haslo2021";
    private Connection connection;

    public JTextField field;
    public String columnName;
    private String[][] data = new String[0][];

    private void setConnection() {
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setColumnName(String columnName){
        this.columnName = columnName;
    }

    public JTextField getTextField(){
        return field;
    }

    public void createNewForm(JFrame frame, JFrame frame2, JTable table) {
        table.setSelectionBackground(Color.LIGHT_GRAY);
        field = new JTextField();
        field.setBounds(frame.getWidth()/2,0,frame.getWidth(),25);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0,100,0,0);

        frame2.setLayout(new BorderLayout());

        frame2.add(field, BorderLayout.PAGE_START);
        frame2.add(sp, BorderLayout.CENTER);

        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.pack();
        frame2.setSize(1000, 500);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }

    public JTable createTable(JTable table){
        try {
            data = setArrayOfEmployees();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String[] columnNames = {"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "EMAIL",
                "PHONE_NUMBER", "HIRE_DATE", "JOB_ID", "SALARY", "COMMISSION_PCT"};

        table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 2 || column == 3 || column == 4 ||
                        column == 5 || column == 6 || column == 7 || column == 8;
            }
        };
        return table;
    }

    public int setArrayLength() throws SQLException {
        int length = 0;
        setConnection();
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        Statement stmt = connection.createStatement();
        String query = "SELECT COUNT(*) AS total FROM EMPLOYEES_COPY";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            length = rs.getInt("total");
        }
        connection.close();
        return length;
    }

    public String[][] setArrayOfEmployees() throws SQLException {
        int index = setArrayLength();
        String[][] data = new String[index][9];
        setConnection();
        String query = "SELECT EMPLOYEE_ID,FIRST_NAME,LAST_NAME,EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT FROM EMPLOYEES_COPY";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        int rowIndex = 0;

        try {
            while (rs.next()) {
                int columnIndex = 0;
                data[rowIndex][columnIndex] = String.valueOf(rs.getInt("EMPLOYEE_ID"));
                columnIndex++;
                data[rowIndex][columnIndex] = rs.getString("FIRST_NAME");
                columnIndex++;
                data[rowIndex][columnIndex] = rs.getString("LAST_NAME");
                columnIndex++;
                data[rowIndex][columnIndex] = rs.getString("EMAIL");
                columnIndex++;
                data[rowIndex][columnIndex] = rs.getString("PHONE_NUMBER");
                columnIndex++;
                data[rowIndex][columnIndex] = String.valueOf(rs.getString("HIRE_DATE"));
                columnIndex++;
                data[rowIndex][columnIndex] = String.valueOf(rs.getString("JOB_ID"));
                columnIndex++;
                data[rowIndex][columnIndex] = String.valueOf(rs.getInt("SALARY"));
                columnIndex++;
                data[rowIndex][columnIndex] = String.valueOf(rs.getFloat("COMMISSION_PCT"));
                columnIndex++;
                rowIndex++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connection.close();
        return data;
    }
    public String[][] getData(){
        int index = 0;
        try {
            index = setArrayLength();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String[][] arr = new String[index][9];
        try {
            arr = setArrayOfEmployees();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }
}
