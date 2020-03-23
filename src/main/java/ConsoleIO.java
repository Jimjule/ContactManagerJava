import java.io.*;

public class ConsoleIO {

    private final BufferedReader reader;
    private final PrintStream printer;

    public ConsoleIO(InputStream input, OutputStream output) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.printer = new PrintStream(output);
    }

    public void display(String message) {
        printer.println(message);
    }

    public String getStringInput(int field, String fieldName) {
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = getInput(fieldName);
            validInput = ValidateInput.validateInput(field, userInput);
        }
        return userInput;
    }

    public int getNumberInput() {
        String userInput;
        try {
            userInput = reader.readLine();
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return 0;
        }
    }

    public String getInput(String fieldName) {
        display("Please enter your " + fieldName + ":");
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

    public boolean getBoolean() {
        String userInput;
        try {
            userInput = reader.readLine();
            if (userInput.equals("y")) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            userInput = "error";
        }
        return userInput == "y";
    }
}
