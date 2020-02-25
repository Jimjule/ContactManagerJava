import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Input {

    public void main(String[] args) {
    }

    public String confirmInput(String detail) {
        String input = getInput(detail);
        System.out.println("1. " + input);
        Boolean validInput = validateInput(detail, input);
        if (validInput.equals(false)) {
            System.out.println("2. " + input);
            confirmInput(detail);
            input = "";
        }
        System.out.println("3. " + input);
        return input;
    }

    public String getNewInput(String detail) {
        Scanner newUserInput = new Scanner(System.in);
        System.out.println("Please try again:");
        String newInput = newUserInput.nextLine();
        return newInput;
    }

    public Integer menuChoice() {
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String getInput(String detail) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your " + detail + ":");
        String input = userInput.nextLine();
        return input;
    }

    public static Boolean validateInput(String detail, String input) {
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

    public static Boolean validName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Z]'?[- a-zA-Z]+$");
        return name.matches(String.valueOf(namePattern));
    }
    public static Boolean validNumber(String phoneNumber) {
        Pattern phonePattern = Pattern.compile("^[\\d]+$");
        return phoneNumber.matches(String.valueOf(phonePattern));
    }

    public static Boolean validDOB(String dOB) {
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
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^(.+@.+)$");
        return email.matches(String.valueOf(emailPattern));
    }
}
