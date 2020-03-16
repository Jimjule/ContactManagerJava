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
        assertFalse(ValidateInput.validateInput(4,"07d7075643"));
    }

    @Test
    public void testValidateNumber() {
        assertTrue(ValidateInput.validateInput(4,"077075643"));
    }

    @Test
    public void testFailValidateNumber() {
        assertFalse(ValidateInput.validateInput(4, "077D75643"));
    }

    @Test
    public void testValidateDOB() {
        assertTrue(ValidateInput.validateInput(5, "10/02/1999"));
    }

    @Test
    public void testDOBInvalidInFuture() {
        assertFalse(ValidateInput.validateInput(5, "10/02/2300"));
    }

    @Test
    public void testFailValidateDOB() {
        assertFalse(ValidateInput.validateInput(5,"10-02-99"));
    }

    @Test
    public void testValidateEmail() {
        assertTrue(ValidateInput.validateInput(6,"this@that"));
    }

    @Test
    public void testFailValidateEmail() {
        assertFalse(ValidateInput.validateInput(6, "thisatthat"));
    }
}
