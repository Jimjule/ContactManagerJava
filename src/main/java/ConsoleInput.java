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

    public String confirmInput(String field, boolean update) {
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = getInput(field);
            validInput = validateInput(field, userInput, update);
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

    public Boolean validateInput(String detail, String userInput, boolean update) {
        switch (detail) {
            case "first name":
            case "last name":
                return validName(userInput, update);
            case "phone number without spaces": return validNumber(userInput, update);
            case "DOB in dd/mm/yyyy format": return validDOB(userInput, update);
            case "email": return validEmail(userInput, update);
            default: return true;
        }
    }

    public Boolean validName(String name, boolean update) {
        Pattern namePattern = Pattern.compile("^[A-Z]'?[- a-zA-Z]+$");
        return name.matches(String.valueOf(namePattern)) || isBlank(name, update);
    }
    public Boolean validNumber(String phoneNumber, boolean update) {
        Pattern phonePattern = Pattern.compile("^[\\d]+$");
        return (phoneNumber.matches(String.valueOf(phonePattern)) || isBlank(phoneNumber, update));
    }

    public Boolean validDOB(String dOB, boolean update) {
        try {
            DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dOB, dayMonthYear);
            LocalDate today = LocalDate.now();
            return (birthDate.isBefore(today) || isBlank(dOB, update));
        } catch (Exception e) {
            return isBlank(dOB, update);
        }
    }

    public Boolean validEmail(String email, boolean update) {
        Pattern emailPattern = Pattern.compile("^(.+@.+)$");
        return (email.matches(String.valueOf(emailPattern)) || isBlank(email, update));
    }

    public boolean isBlank(String userInput, boolean update) {
        Pattern blankPattern = Pattern.compile("^$");
        return (userInput.matches(String.valueOf(blankPattern)) && update);
    }
}
