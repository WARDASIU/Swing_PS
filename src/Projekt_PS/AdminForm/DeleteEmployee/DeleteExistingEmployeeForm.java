package Projekt_PS.AdminForm.DeleteEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteExistingEmployeeForm {

    private JButton deleteButton;
    private JTextField idField;
    private JPanel panel;
    private JLabel lb;

    public DeleteExistingEmployeeForm() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteByID();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == 10){
                    try {
                        deleteByID();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    public void showFrame(){
        JFrame addFrame = new JFrame("Add new Employee");
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setContentPane(new DeleteExistingEmployeeForm().panel);
        addFrame.pack();
        addFrame.setSize(400,300);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);

    }

    private void deleteByID() throws SQLException {
        String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
        String username = "msbd11";
        String password = "haslo2021";
        Connection connection = DriverManager.getConnection(dbURL, username, password);
        Statement stmt = connection.createStatement();
        String query = "DELETE FROM EMPLOYEES_COPY WHERE employee_ID="+Integer.parseInt(idField.getText())+"";
        int result = stmt.executeUpdate(query);
        if(result == 0){
            JOptionPane.showMessageDialog(null,"There's no employee with that ID!");
            idField.setText("");
        }else{
            JOptionPane.showMessageDialog(null,"Employee deleted!");
        }
        System.out.println(result);
    }
}
