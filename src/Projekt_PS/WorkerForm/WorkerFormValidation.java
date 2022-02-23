package Projekt_PS.WorkerForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkerFormValidation {
    public boolean checkPhoneNumberValidation(String number){
        boolean isValid = true;
        Pattern VALID_PHONE_NUMBER_REGEX =
                Pattern.compile(
                        "^[0-9]{3}[.][0-9]{3}[.][0-9]{3}$",
                        Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(number);
        if(!matcher.find()){
            isValid = false;
        }
        return isValid;
    }
    public boolean checkIntValidation(String number){
        boolean isNumeric = true;
        for (int i = 0; i < number.length(); i++) {
            if(!Character.isDigit(number.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        return isNumeric;
    }
    public boolean checkMailValidation(String email){
        boolean isValid = true;
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile(
                        "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if(!matcher.find()){
            isValid = false;
        }
        return isValid;
    }
    public boolean checkFloatValidation(String commPCT){
        boolean isNumeric = true;
        boolean comma = false;
        for (int i = 0; i < commPCT.length(); i++) {
            if(!Character.isDigit(commPCT.charAt(i)) && commPCT.charAt(i) != ','
                    && commPCT.charAt(i) != '.') {
                isNumeric = false;
                break;
            }
            if(commPCT.charAt(i) == ',' && comma){
                isNumeric = false;
                break;
            }
            if(commPCT.charAt(i) == '.' && comma){
                isNumeric = false;
                break;
            }
            if(commPCT.charAt(i) == ','){
                comma = true;
                StringBuilder str = new StringBuilder(commPCT);
                str.setCharAt(i,'.');
                commPCT = str.toString();
            }
        }
        double tmp = Double.parseDouble(commPCT);
        if(tmp > 0.99){
            isNumeric = false;
        }
        return isNumeric;
    }

    public boolean checkDateValidation(String hireDate){
        boolean isValid = true;
        Pattern VALID_DATE_REGEX2000 =
                Pattern.compile(
                        "^([2][0][0-2][0-9][\\/][0-1][0-9][\\/][0-3][0-9])$");
        Matcher matcher2000 = VALID_DATE_REGEX2000.matcher(hireDate);

        Pattern VALID_DATE_REGEX1900 =
                Pattern.compile(
                        "^([1][9][8-9][0-9][\\/][0-1][0-9][\\/][0-3][0-9])$");
        Matcher matcher1900 = VALID_DATE_REGEX1900.matcher(hireDate);

        if (matcher1900.find()) {
            checkCharsAtDate(hireDate,isValid);
        }else if (matcher2000.find()) {
            if (hireDate.charAt(2) == '2' && hireDate.charAt(3) != 48) {
                isValid = false;
            } else {
                checkCharsAtDate(hireDate,isValid);
            }
        }else{
            isValid = false;
        }
        return isValid;
    }

    private boolean checkCharsAtDate(String hireDate, boolean isValid) {
        isValid = true;
        if (hireDate.charAt(5) == '1' && hireDate.charAt(6) > 50) {
            isValid = false;
        } else if (hireDate.charAt(8) == '3' && hireDate.charAt(9) > 49) {
           isValid = false;
        }
        return isValid;
    }

    public boolean checkNameValidation(String name){
        boolean onlyLetters = true;
        for (int i = 0; i < name.length(); i++) {
            if(!Character.isLetter(name.charAt(i))){
                onlyLetters = false;
                break;
            }
        }
        return onlyLetters;
    }
    public boolean checkJobIDValidation(String str){
        boolean isValid = false;

        if(str.equals("AC_ACCOUNT") || str.equals("AC_MGR")
                || str.equals("MK_REP") || str.equals("MK_MAN")
                || str.equals("AD_ASST") || str.equals("SA_REP")
                || str.equals("SA_MAN") || str.equals("ST_CLERK")
                || str.equals("ST_MAN") || str.equals("IT_PROG")
                || str.equals("AD_VP") || str.equals("AD_PRES")){
            isValid = true;
        }

        return isValid;
    }
}
