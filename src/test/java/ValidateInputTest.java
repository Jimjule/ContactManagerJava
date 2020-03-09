import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateInputTest {

    @Test
    public void testValidatesInput() {
        String testInput = "077075643";
        assertTrue(ValidateInput.validNumber(testInput, false));
    }

    @Test
    public void testInvalidInput() {
        assertFalse(ValidateInput.validNumber("07d7075643", false));
    }

    @Test
    public void testValidateNumber() {
        assertTrue(ValidateInput.validNumber("077075643", false));
    }

    @Test
    public void testFailValidateNumber() {
        assertFalse(ValidateInput.validNumber("077D75643", false));
    }

    @Test
    public void testValidateDOB() {
        assertTrue(ValidateInput.validDOB("10/02/1999", false));
    }

    @Test
    public void testDOBInvalidInFuture() {
        assertFalse(ValidateInput.validDOB("10/02/2300", false));
    }

    @Test
    public void testFailValidateDOB() {
        assertFalse(ValidateInput.validDOB("10-02-99", false));
    }

    @Test
    public void testValidateEmail() {
        assertTrue(ValidateInput.validEmail("this@that", false));
    }

    @Test
    public void testFailValidateEmail() {
        assertFalse(ValidateInput.validEmail("thisatthat", false));
    }
}
