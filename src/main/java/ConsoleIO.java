import java.io.*;

public class ConsoleIO {

    private final BufferedReader reader;
    public final PrintStream printer;

    public ConsoleIO(InputStream input, OutputStream output) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.printer = new PrintStream(output);
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
            return Integer.parseInt(userInput);
        } catch (Exception e) {
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

    public void clearScreen() {
        display("\033[H\033[2J");
        printer.flush();
    }
}
