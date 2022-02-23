package Projekt_PS.StartingPage;
import Projekt_PS.AdminForm.AdminForm;
import Projekt_PS.WorkerForm.WorkerForm;

import javax.swing.*;
import java.sql.*;

public class StartPageController {
    public String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
    public String username = "msbd11";
    public String password = "haslo2021";
    public String isAdmin;

    public Connection connection;
    public StartPageController()  {
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to Oracle database server!");
        } catch (SQLException throwables) {
            System.out.println("Couldn't connect to Oracle database!");
            throwables.printStackTrace();
        }
    }

    public boolean checkDataLogin(String login, String pass) throws SQLException {
        String query = "SELECT * FROM accounts";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        if(login != null && pass != null){
            try{
                while (rs.next()) {
                    String loginDB = rs.getString("Login");
                    String passDB = rs.getString("Password");
                    isAdmin = rs.getString("isAdmin");
                    if(login.equals(loginDB) && pass.equals(passDB)){
                        return true;
                    }
                }
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public void checkAccountRole(boolean correctAcc, JFrame frame){
        if(correctAcc && getIsAdmin().equals("Y")){
            JOptionPane.showMessageDialog(null,"Logged succesfully!");
            AdminForm adminForm = new AdminForm(frame);
        }else if(correctAcc && getIsAdmin().equals("N")){
            JOptionPane.showMessageDialog(null,"Logged succesfully!");
            WorkerForm workerForm = new WorkerForm(frame);
        }else JOptionPane.showMessageDialog(null,"Login failed! Check login or password!");
    }

    public static void changeLookAndFeel(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :  javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIsAdmin() {
        return isAdmin;
    }

}
