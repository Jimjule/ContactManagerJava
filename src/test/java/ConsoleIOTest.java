import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class ConsoleIOTest {

    ConsoleIO consoleFixString;
    ConsoleIO consoleFixNumber;
    ConsoleIO consoleFixBoolean;

    @Test
    public void getStringInputCallsGetInput() {
        ConsoleIOSpy consoleIOSpy = new ConsoleIOSpy(System.in, System.out);
        consoleIOSpy.getStringInput(1, "first name");
        assertEquals(true, consoleIOSpy.getInputHasBeenCalled);
    }

    @Test
    public void getStringInputReturnsInput() {
        String fixedString = "Fixed";
        InputStream fixedInputString = new ByteArrayInputStream(fixedString.getBytes());
        consoleFixString = new ConsoleIO(fixedInputString, System.out);
        assertEquals("Fixed", consoleFixString.getStringInput(1, "first name"));
    }

    @Test
    public void getBooleanReturnsTrue() {
        String fixedString = "y";
        InputStream fixedInputString = new ByteArrayInputStream(fixedString.getBytes());
        consoleFixBoolean = new ConsoleIO(fixedInputString, System.out);
        assertEquals(true, consoleFixBoolean.getBoolean());
    }

    @Test
    public void getNumberReturnsNumber() {
        String fixedNumber = "7";
        InputStream fixedInputNumber = new ByteArrayInputStream(fixedNumber.getBytes());
        consoleFixNumber = new ConsoleIO(fixedInputNumber, System.out);
        assertEquals(7, consoleFixNumber.getNumberInput());
    }
}
