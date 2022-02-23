package Projekt_PS.StartingPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.*;

public class StartPage extends StartPageController {
    private static JFrame frame;
    private JButton submit;
    private JPanel board;
    private JPasswordField passField;
    private JTextField loginField;
    private JButton registerButton;
    public boolean correctAcc;

    public StartPage() {
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    correctAcc = checkDataLogin(loginField.getText(),passField.getText());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                checkAccountRole(correctAcc,frame);
            }
        });
        passField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == 10){
                    try {
                        correctAcc = checkDataLogin(loginField.getText(),passField.getText());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    checkAccountRole(correctAcc,frame);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
                registerForm.createForm();
            }
        });
    }

    public static void main(String[] args) {
        changeLookAndFeel();

        File file = new File("logo.png");
        String filePath = file.getAbsolutePath();
        Image icon = Toolkit.getDefaultToolkit().getImage(filePath);

        frame = new JFrame("Projekt_PS");
        frame.setContentPane(new StartPage().board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(450,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(icon);
    }
}

