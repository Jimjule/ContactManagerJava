import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateInputTest {

    @Test
    public void testValidatesInput() {
        String testInput = "077075643";
        assertTrue(ValidateInput.validNumber(testInput));
    }

    @Test
    public void testInvalidInput() {
        assertFalse(ValidateInput.validNumber("07d7075643"));
    }

    @Test
    public void testValidateNumber() {
        assertTrue(ValidateInput.validNumber("077075643"));
    }

    @Test
    public void testFailValidateNumber() {
        assertFalse(ValidateInput.validNumber("077D75643"));
    }

    @Test
    public void testValidateDOB() {
        assertTrue(ValidateInput.validDOB("10/02/1999"));
    }

    @Test
    public void testDOBInvalidInFuture() {
        assertFalse(ValidateInput.validDOB("10/02/2300"));
    }

    @Test
    public void testFailValidateDOB() {
        assertFalse(ValidateInput.validDOB("10-02-99"));
    }

    @Test
    public void testValidateEmail() {
        assertTrue(ValidateInput.validEmail("this@that"));
    }

    @Test
    public void testFailValidateEmail() {
        assertFalse(ValidateInput.validEmail("thisatthat"));
    }

    @Test
    public void testValidInput() {
        assertTrue(ValidateInput.validateInput(3,"077075643"));
    }
}
