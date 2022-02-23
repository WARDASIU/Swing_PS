package Projekt_PS.WorkerForm;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkerForm extends WorkerFormController {

    public String ID;
    public String columnName;


    public JTable table;
    public JTextField field;
    private Connection connection;
    public WorkerFormSearch search = new WorkerFormSearch();

    public WorkerForm(JFrame frame) {

        frame.setVisible(false);
        frame.dispose();
        JFrame frame2 = new JFrame("Projekt_PS");

        table = createTable(table);
        createKeybindings(table);
        table.changeSelection(0, 1, false, false); //Selecting focus on 0,1 cell

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10 && table.getSelectedColumn() != 0) {
                    try {
                        int idColumn = 0;
                        int nameRow = table.getSelectedRow();
                        ID = table.getModel().getValueAt(nameRow, idColumn).toString();
                        int selectedColumnNumber = table.getSelectedColumn();
                        columnName = table.getColumnName(selectedColumnNumber);
                        setColumnName(columnName);
                        table.getCellEditor().stopCellEditing();
                        String cellValue = String.valueOf(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
                        checkValidation(cellValue, columnName);
                    } catch (NullPointerException ignored) {
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Start typing to edit");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        createNewForm(frame, frame2, table);
        field = getTextField();

        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == 10){
                    search.showEqualItems(field.getText(),table.getRowCount(),table);
                }
            }
        });
    }

    private void createKeybindings(JTable table) {
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Tab");
        table.getActionMap().put("Tab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        table.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
    }

    public void checkValidation(String cellValue, String columnName) {
        if (columnName.equals("SALARY")) {
            boolean isNumeric = checkIntValidation(cellValue);
            if (isNumeric) {
                sendQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
        }
        if (columnName.equals("FIRST_NAME") || columnName.equals("LAST_NAME")) {
            boolean isLetteric = checkNameValidation(cellValue);
            if (isLetteric) {
                sendQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
        }
        if (columnName.equals("EMAIL")) {
            boolean isValid = checkMailValidation(cellValue);
            if (isValid) {
                sendQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong input!");
            }
        }
        if (columnName.equals("HIRE_DATE")) {
            boolean isValid = checkDateValidation(cellValue);
            if (isValid) {
                sendQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Date is incorrect!");
            }
        }
        if (columnName.equals("PHONE_NUMBER")) {
            boolean isValid = checkPhoneNumberValidation(cellValue);
            if (isValid) {
                sendQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Phone number is incorrect!");
            }
        }
        if (columnName.equals("COMMISSION_PCT")) {
            boolean isValid = checkFloatValidation(cellValue);
            if (isValid) {
                sendQuery();
            } else {
                JOptionPane.showMessageDialog(null, "Comm_PCT is incorrect or too big! Max 0.99");
            }
        }
        if (columnName.equals("JOB_ID")) {
            boolean isValid = checkJobIDValidation(cellValue);
            if (isValid) {
                sendQuery();
            } else {
                JOptionPane.showMessageDialog(null, "JOB_ID is incorrect");
            }
        }
    }

    private void sendQuery() {
        setConnection();
        String query;
        if (columnName.equals("COMMISSION_PCT")) {
            query = "UPDATE EMPLOYEES_COPY SET " +
                    "" + columnName + " = " + table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) + " WHERE EMPLOYEE_ID = " + ID + "";
        } else {
            query = "UPDATE EMPLOYEES_COPY SET " +
                    "" + columnName + " = '" + table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) + "' WHERE EMPLOYEE_ID = " + ID + "";
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int result = 0;
        try {
            result = stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(result);
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setConnection() {
        try {
            String username = "msbd11";
            String password = "haslo2021";
            String dbURL = "jdbc:oracle:thin:@155.158.112.45:1521:oltpstud";
            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}