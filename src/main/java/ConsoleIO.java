import java.io.*;

public class ConsoleIO implements InputOutput{

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
                return ValidateInput.validName(userInput, isAnUpdate);
            case ContactFields.PhoneNumber: return ValidateInput.validNumber(userInput, isAnUpdate);
            case ContactFields.DOB: return ValidateInput.validDOB(userInput, isAnUpdate);
            case ContactFields.Email: return ValidateInput.validEmail(userInput, isAnUpdate);
            case "Field": return ValidateInput.validField(userInput);
            default: return true;
        }
    }
}
