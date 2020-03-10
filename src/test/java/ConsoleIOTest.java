import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConsoleIOTest {

    private ConsoleIO consoleIO;

    @Before
    public void setUp() {
        this.consoleIO = new ConsoleIO(System.in, System.out);
    }

    @Test
    public void testValidatesInput() {
        String testInput = "077075643";
        assertTrue(consoleIO.validNumber(testInput, false));
    }

    @Test
    public void testInvalidInput() {
        assertFalse(consoleIO.validNumber("07d7075643", false));
    }

    @Test
    public void testValidInput() {
        assertTrue(consoleIO.validateInput("phone number","077075643", false));
    }

    @Test
    public void testValidateNumber() {
        assertTrue(consoleIO.validNumber("077075643", false));
    }


    @Test
    public void testFailValidateNumber() {
        assertFalse(consoleIO.validNumber("077D75643", false));
    }

    @Test
    public void testValidateDOB() {
        assertTrue(consoleIO.validDOB("10/02/1999", false));
    }

    @Test
    public void testDOBInvalidInFuture() {
        assertFalse(consoleIO.validDOB("10/02/2300", false));
    }

    @Test
    public void testFailValidateDOB() {
        assertFalse(consoleIO.validDOB("10-02-99", false));
    }

    @Test
    public void testValidateEmail() {
        assertTrue(consoleIO.validEmail("this@that", false));
    }

    @Test
    public void testFailValidateEmail() {
        assertFalse(consoleIO.validEmail("thisatthat", false));
    }
}
