import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.*;

public class InputTest {

    @Test
    public void testValidatesInput() {
        Input input = new Input();
        String testInput = "077075643";
        assertTrue(input.validNumber(testInput));
    }

    @Test
    public void testInvalidInput() {
        Input input = new Input();
        assertFalse(input.validNumber("07d7075643"));
    }

    @Test
    public void testValidInput() {
        Input input = new Input();
        assertTrue(input.validateInput("phone number","077075643"));
    }

    @Test
    public void testValidateNumber() {
        Input input = new Input();
        assertTrue(input.validNumber("077075643"));
    }


    @Test
    public void testFailValidateNumber() {
        Input input = new Input();
        assertFalse(input.validNumber("077D75643"));
    }

    @Test
    public void testValidateDOB() {
        Input input = new Input();
        assertTrue(input.validDOB("10/02/1999"));
    }

    @Test
    public void testDOBInvalidInFuture() {
        Input input = new Input();
        assertFalse(input.validDOB("10/02/2300"));
    }

    @Test
    public void testFailValidateDOB() {
        Input input = new Input();
        assertFalse(input.validDOB("10-02-99"));
    }

    @Test
    public void testValidateEmail() {
        Input input = new Input();
        assertTrue(input.validEmail("this@that"));
    }

    @Test
    public void testFailValidateEmail() {
        Input input = new Input();
        assertFalse(input.validEmail("thisatthat"));
    }
}
