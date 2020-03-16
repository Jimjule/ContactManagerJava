import java.io.*;

import static org.mockito.Mockito.when;

public class ConsoleIOSpy extends ConsoleIO {

    public final PrintStream printer;
    public boolean getInputHasBeenCalled = false;
    public boolean getNumberInputHasBeenCalled = false;
    public boolean getStringInputHasBeenCalled = false;
    public boolean getBooleanHasBeenCalled = false;


    public ConsoleIOSpy(InputStream input, OutputStream output) {
        super(input, output);
        BufferedReader bufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        this.printer = new PrintStream(output);
        try {
            when(bufferedReader.readLine()).thenReturn("Firstname").thenReturn("Lastname").thenReturn("Address").thenReturn("012345").thenReturn("01/02/1934").thenReturn("test@place.org");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(String message) {
        printer.println(message);
    }

    public String getStringInput(int field, String fieldName) {
        this.getStringInputHasBeenCalled = true;
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = getInput(fieldName);
            validInput = ValidateInput.validateInput(field, userInput);
        }
        return userInput;
    }

    public int getNumberInput() {
        this.getNumberInputHasBeenCalled = true;
        return 1;
    }

    public String getInput(String fieldName) {
        this.getInputHasBeenCalled = true;
        return "";
    }

    public void clearScreen() {
        display("\033[H\033[2J");
        printer.flush();
    }

    public boolean getBoolean() {
        this.getBooleanHasBeenCalled = true;
        return true;
    }
}
