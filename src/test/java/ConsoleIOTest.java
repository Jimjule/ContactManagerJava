import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ConsoleIOTest {

    ConsoleIO consoleFixNumber;
    ConsoleIO consoleFixBoolean;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void getStringInputCallsGetInput() {
        ConsoleIOSpy consoleIOSpy = new ConsoleIOSpy(System.in, outputStream);
        consoleIOSpy.getInput("first name");
        assertEquals(true, consoleIOSpy.getInputHasBeenCalled);
    }

    @Test
    public void getBooleanReturnsTrue() {
        String fixedString = "y";
        InputStream fixedInputString = new ByteArrayInputStream(fixedString.getBytes());
        consoleFixBoolean = new ConsoleIO(fixedInputString, outputStream);
        assertEquals(true, consoleFixBoolean.getBoolean());
    }

    @Test
    public void getNumberReturnsNumber() {
        String fixedNumber = "7";
        InputStream fixedInputNumber = new ByteArrayInputStream(fixedNumber.getBytes());
        consoleFixNumber = new ConsoleIO(fixedInputNumber, outputStream);
        assertEquals(7, consoleFixNumber.getNumberInput());
    }
}
