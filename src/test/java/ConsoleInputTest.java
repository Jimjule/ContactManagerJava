import org.junit.Test;

import static org.junit.Assert.*;

public class ConsoleInputTest {

    @Test
    public void testValidatesInput() {
        ConsoleInput consoleInput = new ConsoleInput();
        String testInput = "077075643";
        assertTrue(consoleInput.validNumber(testInput));
    }

    @Test
    public void testInvalidInput() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertFalse(consoleInput.validNumber("07d7075643"));
    }

    @Test
    public void testValidInput() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertTrue(consoleInput.validateInput("phone number","077075643"));
    }

    @Test
    public void testValidateNumber() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertTrue(consoleInput.validNumber("077075643"));
    }


    @Test
    public void testFailValidateNumber() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertFalse(consoleInput.validNumber("077D75643"));
    }

    @Test
    public void testValidateDOB() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertTrue(consoleInput.validDOB("10/02/1999"));
    }

    @Test
    public void testDOBInvalidInFuture() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertFalse(consoleInput.validDOB("10/02/2300"));
    }

    @Test
    public void testFailValidateDOB() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertFalse(consoleInput.validDOB("10-02-99"));
    }

    @Test
    public void testValidateEmail() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertTrue(consoleInput.validEmail("this@that"));
    }

    @Test
    public void testFailValidateEmail() {
        ConsoleInput consoleInput = new ConsoleInput();
        assertFalse(consoleInput.validEmail("thisatthat"));
    }
}
