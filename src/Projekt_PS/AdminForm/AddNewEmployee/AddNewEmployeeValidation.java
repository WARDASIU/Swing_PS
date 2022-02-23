package Projekt_PS.AdminForm.AddNewEmployee;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewEmployeeValidation {
    public void checkPhoneNumberValidation(String number, JTextField PHONE_NUMBERTextField) {
        Pattern VALID_PHONE_NUMBER_REGEX =
                Pattern.compile(
                        "^[0-9]{3}[.][0-9]{3}[.][0-9]{3}$",
                        Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(number);
        if (!matcher.find()) {
            PHONE_NUMBERTextField.setText("");
            JOptionPane.showMessageDialog(null, "Phone number is incorrect!");
        }
    }

    public void checkIntValidation(String number, JTextField EMPLOYEE_IDTextField) {
        boolean isNumeric = true;
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        if (!isNumeric) {
            EMPLOYEE_IDTextField.setText("");
            JOptionPane.showMessageDialog(null, "empID or Salary is wrong!");
        }
    }

    public void checkMailValidation(String email, JTextField EMAILTextField) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile(
                        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            EMAILTextField.setText("");
            JOptionPane.showMessageDialog(null, "Mail is incorrect!");
        }
    }

    public void checkFloatValidation(String commPCT, JTextField COMMISSION_PCTTextField) {
        boolean isNumeric = true;
        boolean comma = false;
        for (int i = 0; i < commPCT.length(); i++) {
            if (!Character.isDigit(commPCT.charAt(i)) && commPCT.charAt(i) != ','
                    && commPCT.charAt(i) != '.') {
                isNumeric = false;
                break;
            }
            if (commPCT.charAt(i) == ',' && comma) {
                isNumeric = false;
                break;
            }
            if (commPCT.charAt(i) == '.' && comma) {
                isNumeric = false;
                break;
            }
            if (commPCT.charAt(i) == ',') {
                comma = true;
                StringBuilder str = new StringBuilder(commPCT);
                str.setCharAt(i, '.');
                commPCT = str.toString();
            }
        }

        double tmp = 0;
        if (!isNumeric) {
            COMMISSION_PCTTextField.setText("");
            JOptionPane.showMessageDialog(null, "empID, jobID or Salary is wrong!");
        } else {
            COMMISSION_PCTTextField.setText(commPCT);
            tmp = Double.parseDouble(COMMISSION_PCTTextField.getText());
        }
        if (tmp > 0.99) {
            COMMISSION_PCTTextField.setText("");
            JOptionPane.showMessageDialog(null, "COMM_PCT is wrong!");
        }
    }

    public void checkDateValidation(String hireDate, JTextField HIRE_DATETextField) {
        Pattern VALID_DATE_REGEX2000 =
                Pattern.compile(
                        "^([2][0][0-2][0-9][\\/][0-1][0-9][\\/][0-3][0-9])$");
        Matcher matcher2000 = VALID_DATE_REGEX2000.matcher(hireDate);

        Pattern VALID_DATE_REGEX1900 =
                Pattern.compile(
                        "^([1][9][8-9][0-9][\\/][0-1][0-9][\\/][0-3][0-9])$");
        Matcher matcher1900 = VALID_DATE_REGEX1900.matcher(hireDate);


        if (matcher1900.find()) {
            checkCharsAtDate(hireDate, HIRE_DATETextField);
        }else if (matcher2000.find()) {
            if (hireDate.charAt(2) == '2' && hireDate.charAt(3) != 48) {
                HIRE_DATETextField.setText("");
                JOptionPane.showMessageDialog(null, "Date is incorrect!");
            } else {
                checkCharsAtDate(hireDate, HIRE_DATETextField);
            }
        }else{
            HIRE_DATETextField.setText("");
            JOptionPane.showMessageDialog(null, "Date is incorrect!");
        }
    }

    private void checkCharsAtDate(String hireDate, JTextField HIRE_DATETextField) {
        if (hireDate.charAt(5) == '1' && hireDate.charAt(6) > 50) {
            HIRE_DATETextField.setText("");
            JOptionPane.showMessageDialog(null, "Date is incorrect!");
        } else if (hireDate.charAt(8) == '3' && hireDate.charAt(9) > 49) {
            HIRE_DATETextField.setText("");
            JOptionPane.showMessageDialog(null, "Date is incorrect!");
        }
    }

    public void checkNameValidation(String name, JTextField FIRST_NAMETextField) {
        boolean onlyLetters = true;
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i))) {
                onlyLetters = false;
                break;
            }
        }
        if (!onlyLetters) {
            FIRST_NAMETextField.setText("");
            JOptionPane.showMessageDialog(null, "First or last name is incorrect!");
        }
    }

    public void checkJobIDValidation(String str, JTextField JOB_IDTextField) {
        boolean isValid = false;
        if (str.equals("AC_ACCOUNT") || str.equals("AC_MGR")
                || str.equals("MK_REP") || str.equals("MK_MAN")
                || str.equals("AD_ASST") || str.equals("SA_REP")
                || str.equals("SA_MAN") || str.equals("ST_CLERK")
                || str.equals("ST_MAN") || str.equals("IT_PROG")
                || str.equals("AD_VP") || str.equals("AD_PRES")) {
            isValid = true;
        }
        if (!isValid) {
            JOB_IDTextField.setText("");
            JOptionPane.showMessageDialog(null, "JOB_ID is incorrect!");
        }
    }
}
