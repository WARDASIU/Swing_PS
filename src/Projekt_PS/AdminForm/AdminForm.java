package Projekt_PS.AdminForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminForm extends AdminFormController {

    public JPanel adminPanel;
    private JScrollPane scrollPane;
    private JTextArea area;
    private JButton deleteButton;
    private JButton addButton;
    private JButton refreshButton;


    public AdminForm(JFrame frame){
        frame.setVisible(false);
        frame.dispose();

        JFrame frame2 = new JFrame("Projekt_PS");
        frame2.setContentPane(new AdminForm().adminPanel);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setSize(1000,400);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }

    public AdminForm() {
        try {
            displayEmpTable(area);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewEmployee();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteExistingEmployee();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable(area);
            }
        });
    }
}
