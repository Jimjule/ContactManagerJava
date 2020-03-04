import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConsoleInputTest {

    private ConsoleInput consoleInput;

    @Before
    public void setUp() {
        this.consoleInput = new ConsoleInput(System.in, System.out);
    }

    @Test
    public void testValidatesInput() {
        String testInput = "077075643";
        assertTrue(consoleInput.validNumber(testInput));
    }

    @Test
    public void testInvalidInput() {
        assertFalse(consoleInput.validNumber("07d7075643"));
    }

    @Test
    public void testValidInput() {
        assertTrue(consoleInput.validateInput("phone number","077075643"));
    }

    @Test
    public void testValidateNumber() {
        assertTrue(consoleInput.validNumber("077075643"));
    }


    @Test
    public void testFailValidateNumber() {
        assertFalse(consoleInput.validNumber("077D75643"));
    }

    @Test
    public void testValidateDOB() {
        assertTrue(consoleInput.validDOB("10/02/1999"));
    }

    @Test
    public void testDOBInvalidInFuture() {
        assertFalse(consoleInput.validDOB("10/02/2300"));
    }

    @Test
    public void testFailValidateDOB() {
        assertFalse(consoleInput.validDOB("10-02-99"));
    }

    @Test
    public void testValidateEmail() {
        assertTrue(consoleInput.validEmail("this@that"));
    }

    @Test
    public void testFailValidateEmail() {
        assertFalse(consoleInput.validEmail("thisatthat"));
    }
}
