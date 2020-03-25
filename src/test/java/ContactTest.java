import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContactTest {

    Contact contact;
    ConsoleIO consoleIO;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void initialize() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        consoleIO = new ConsoleIOSpy(fixedInput, outputStream);
        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com", consoleIO);
    }

    @Test
    public void canUpdateFirstName() {
        contact.updateField("Elnamo", 1);
        assertEquals("Elnamo", contact.getFirstName());
    }

    @Test
    public void canUpdateLastName() {
        contact.updateField("Sir", 2);
        assertEquals("Sir", contact.getLastName());
    }

    @Test
    public void canUpdateAddress() {
        contact.updateField("7 Palace place", 3);
        assertEquals("7 Palace place", contact.getAddress());
    }

    @Test
    public void canUpdatePhoneNumber() {
        contact.updateField("01", 4);
        assertEquals("01", contact.getPhoneNumber());
    }

    @Test
    public void canUpdateDOB() {
        contact.updateField("30/01/2013", 5);
        assertEquals("30/01/2013", contact.getDOB());
    }

    @Test
    public void canUpdateEmail() {
        contact.updateField("new@email.com", 6);
        assertEquals("new@email.com", contact.getEmail());
    }

    @Test
    public void firstNameValue() {
        assertEquals(Contact.getFieldName(1), "First Name");
    }

    @Test
    public void lastNameValue() {
        assertEquals(Contact.getFieldName(2), "Last Name");
    }

    @Test
    public void addressValue() {
        assertEquals(Contact.getFieldName(3), "Address");
    }

    @Test
    public void phoneNumberValue() {
        assertEquals(Contact.getFieldName(4), "Phone Number (no spaces)");
    }

    @Test
    public void dOBValue() {
        assertEquals(Contact.getFieldName(5), "Date of Birth (dd/MM/yyyy)");
    }

    @Test
    public void emailValue() {
        assertEquals(Contact.getFieldName(6), "Unique Email Address");

    }
}