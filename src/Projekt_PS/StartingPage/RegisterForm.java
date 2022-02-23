package Projekt_PS.StartingPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterForm {
    private JTextField loginRegisterField;
    private JTextField passRegisterField;
    private JTextField isAdminField;
    private JButton submitRegister;
    private JPanel registerBoard;
    private JTextField idRegisterField;

    public RegisterForm() {
        submitRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginRegisterField.getText() != null &&
                    passRegisterField !=  null &&
                        isAdminField != null &&
                        idRegisterField != null
                ) checkFieldsValidation();
                else{
                    JOptionPane.showMessageDialog(null, "One of the inputs is empty!");
                }
            }
        });
    }

    public void createForm(){
        JFrame frame = new JFrame("Register Form");
        frame.setContentPane(new RegisterForm().registerBoard);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(500,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void checkFieldsValidation(){
        String tmp = isAdminField.getText();
        if(tmp.equals("Y") || tmp.equals("N")) {
            try {
                sendRegisterQuery();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
                isAdminField.setText("");
                JOptionPane.showMessageDialog(null,"Admin field is incorrect! \n Type Y or N");
        }
        for (int i = 0; i < idRegisterField.getText().length(); i++) {
            if(Character.isLetter(idRegisterField.getText().charAt(i))){
                JOptionPane.showMessageDialog(null,"ID invalid, digits only!");
                idRegisterField.setText("");
                break;
            }
        }
    }

    private void sendRegisterQuery() throws SQLException {
        String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
        String username = "msbd11";
        String password = "haslo2021";
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        Statement stmt = connection.createStatement();
        String check = "SELECT id,login FROM accounts WHERE id = "+Integer.parseInt(idRegisterField.getText())+"";
        ResultSet rs = stmt.executeQuery(check);

        boolean valid = true;
        while(rs.next()){
            if(idRegisterField.getText().equals(rs.getString("id")) || loginRegisterField.getText().equals(rs.getString("login"))){
                valid = false;
                idRegisterField.setText("");
                JOptionPane.showMessageDialog(null,"This ID or login is already taken, u gotta change it!");
            }
        }
        if(valid){
            String query = "INSERT INTO ACCOUNTS (id,login,password,isAdmin) VALUES ("+Integer.parseInt(idRegisterField.getText())+",'"+loginRegisterField.getText()+"','"+passRegisterField.getText()+"','"+isAdminField.getText()+"')";
            int result = stmt.executeUpdate(query);
        }
        connection.close();
    }

}
