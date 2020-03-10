import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ConsoleIO {

    private final InputStream input;
    private final OutputStream output;
    private final BufferedReader reader;
    public final PrintStream printer;

    public ConsoleIO(InputStream input, OutputStream output) {
        reader = new BufferedReader(new InputStreamReader(input));
        printer = new PrintStream(output);
        this.input = input;
        this.output = output;
    }

    public void display(String message) {
        printer.println(message);
    }

    public String confirmInput(String field, boolean isAnUpdate) {
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = getInput(field);
            validInput = validateInput(field, userInput, isAnUpdate);
        }
        return userInput;
    }

    public int getNumberInput() {
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

    public Boolean validateInput(String detail, String userInput, boolean isAnUpdate) {
        switch (detail) {
            case ContactFields.FirstName:
            case ContactFields.LastName:
                return validName(userInput, isAnUpdate);
            case ContactFields.PhoneNumber: return validNumber(userInput, isAnUpdate);
            case ContactFields.DOB: return validDOB(userInput, isAnUpdate);
            case ContactFields.Email: return validEmail(userInput, isAnUpdate);
            case "Field": return validField(userInput);
            default: return true;
        }
    }

    public Boolean validName(String name, boolean isAnUpdate) {
        Pattern namePattern = Pattern.compile("^[A-Z]'?[- a-zA-Z]+$");
        return name.matches(String.valueOf(namePattern)) || isBlank(name, isAnUpdate);
    }

    public Boolean validNumber(String phoneNumber, boolean isAnUpdate) {
        Pattern phonePattern = Pattern.compile("^[\\d]+$");
        return (phoneNumber.matches(String.valueOf(phonePattern)) || isBlank(phoneNumber, isAnUpdate));
    }

    public Boolean validField(String fieldNumber) {
        Pattern fieldPattern = Pattern.compile("^[1-6]$");
        return (fieldNumber.matches(String.valueOf(fieldPattern)));
    }

    public Boolean validDOB(String dOB, boolean isAnUpdate) {
        try {
            DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dOB, dayMonthYear);
            LocalDate today = LocalDate.now();
            return (birthDate.isBefore(today) || isBlank(dOB, isAnUpdate));
        } catch (Exception e) {
            return isBlank(dOB, isAnUpdate);
        }
    }

    public Boolean validEmail(String email, boolean isAnUpdate) {
        Pattern emailPattern = Pattern.compile("^(.+@.+)$");
        return (email.matches(String.valueOf(emailPattern)) || isBlank(email, isAnUpdate));
    }

    public boolean isBlank(String userInput, boolean isAnUpdate) {
        Pattern blankPattern = Pattern.compile("^$");
        return (userInput.matches(String.valueOf(blankPattern)) && isAnUpdate);
    }
}
