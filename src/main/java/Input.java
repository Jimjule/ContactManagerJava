import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Input {

    public Input() {
    }

    public String confirmInput(String detail) {
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = getInput(detail);
            validInput = validateInput(detail, userInput);
        }
        return userInput;
    }

    public int contactChoice() {
        return Integer.parseInt(confirmInput("contact choice"));
    }

    public int menuChoice() {
        String input;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
        } catch (IOException e) {
            return - 1;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getInput(String detail) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your " + detail + ":");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Cannot read line" + e);
        }
        return input;
    }

    public Boolean validateInput(String detail, String input) {
        switch (detail) {
            case "first name":
            case "last name":
                return validName(input);
            case "phone number": return validNumber(input);
            case "DOB in dd/mm/yyyy format": return validDOB(input);
            case "email": return validEmail(input);
            default: return true;
        }
    }

    public Boolean validName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Z]'?[- a-zA-Z]+$");
        return name.matches(String.valueOf(namePattern));
    }
    public Boolean validNumber(String phoneNumber) {
        Pattern phonePattern = Pattern.compile("^[\\d]+$");
        return phoneNumber.matches(String.valueOf(phonePattern));
    }

    public Boolean validDOB(String dOB) {
        SimpleDateFormat dayMonthYear = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthDate = dayMonthYear.parse(dOB);
            Date today = new Date();
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(birthDate);
            cal2.setTime(today);
            return cal1.before(cal2);
        } catch (
            ParseException e) {
            return false;
        }
    }

    public Boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^(.+@.+)$");
        return email.matches(String.valueOf(emailPattern));
    }
}
