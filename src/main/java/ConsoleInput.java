import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ConsoleInput {

    private final InputStream input;
    private final OutputStream output;
    private final BufferedReader reader;
    private final PrintStream printer;

    public ConsoleInput(InputStream input, OutputStream output) {
        reader = new BufferedReader(new InputStreamReader(input));
        printer = new PrintStream(output);
        this.input = input;
        this.output = output;
    }

    public String confirmInput(String field) {
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = getInput(field);
            validInput = validateInput(field, userInput);
        }
        return userInput;
    }

    public int menuChoice() {
        String userInput;
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            return - 1;
        }
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getInput(String detail) {
        printer.println("Please enter your " + detail + ":");
        String userInput = null;
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            printer.println("Cannot read line" + e);
        }
        return userInput;
    }

    public Boolean validateInput(String detail, String userInput) {
        switch (detail) {
            case "first name":
            case "last name":
                return validName(userInput);
            case "phone number without spaces": return validNumber(userInput);
            case "DOB in dd/mm/yyyy format": return validDOB(userInput);
            case "email": return validEmail(userInput);
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
        try {
            DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dOB, dayMonthYear);
            LocalDate today = LocalDate.now();
            return birthDate.isBefore(today);
        } catch (Exception e) {
            printer.println("Invalid date, please try again");
            return false;
        }
    }

    public Boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^(.+@.+)$");
        return email.matches(String.valueOf(emailPattern));
    }
}
