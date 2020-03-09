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

    public String getStringInput(String field, boolean isAnUpdate) {
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = getInput(field);
            validInput = ValidateInput.validateInput(field, userInput, isAnUpdate);
        }
        return userInput;
    }

    public int getMenuInput() {
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
        display("Please enter your " + detail + ":");
        String userInput = null;
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            display("Cannot read line" + e);
        }
        return userInput;
    }
}
