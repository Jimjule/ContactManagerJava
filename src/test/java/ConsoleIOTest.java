import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleIOTest {

    ConsoleIO consoleFixNumber;
    ConsoleIO consoleFixBoolean;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void getStringInputCallsGetInput() {
        String fixedString = "y";
        InputStream fixedInputString = new ByteArrayInputStream(fixedString.getBytes());
        ConsoleIOSpy consoleIOSpy = new ConsoleIOSpy(fixedInputString, outputStream);
        consoleIOSpy.getInput("first name");
        assertTrue(consoleIOSpy.returnGetInputHasBeenCalled());
    }

    @Test
    public void getBooleanReturnsTrue() {
        String fixedString = "y";
        InputStream fixedInputString = new ByteArrayInputStream(fixedString.getBytes());
        consoleFixBoolean = new ConsoleIO(fixedInputString, outputStream);
        assertTrue(consoleFixBoolean.getBoolean());
    }

    @Test
    public void getNumberReturnsNumber() {
        String fixedNumber = "7";
        InputStream fixedInputNumber = new ByteArrayInputStream(fixedNumber.getBytes());
        consoleFixNumber = new ConsoleIO(fixedInputNumber, outputStream);
        assertEquals(7, consoleFixNumber.getNumberInput());
    }
}
