import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ContactTest {

    Contact contact;
    @Before
    public void initialize() {
       contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
    }
    @Test
    public void testContactClassIsNotNull() {
        assertNotNull(contact);
    }

    @Test
    public void testCanUpdateFirstName() {
        assertEquals("Namey", contact.returnFirstName());
        contact.updateFirstName("Elnamo");
        assertEquals("Elnamo", contact.returnFirstName());
    }

    @Test
    public void testCanUpdateLastName() {
        assertEquals("Namerson", contact.returnLastName());
        contact.updateLastName("Sir");
        assertEquals("Sir", contact.returnLastName());
    }

    @Test
    public void testCanUpdateAddress() {
        assertEquals("A Palace", contact.returnAddress());
        contact.updateAddress("7 Palace place");
        assertEquals("7 Palace place", contact.returnAddress());
    }

    @Test
    public void testCanUpdatePhoneNumber() {
        assertEquals("130077", contact.returnPhoneNumber());
        contact.updatePhoneNumber("01");
        assertEquals("01", contact.returnPhoneNumber());
    }

    @Test
    public void testCanUpdateDOB() {
        assertEquals("01/01/1999", contact.returnDOB());
        contact.updateDOB("30/01/13");
        assertEquals("30/01/13", contact.returnDOB());
    }

    @Test
    public void testCanUpdateEmail() {
        assertEquals("email@email.com", contact.returnEmail());
        contact.updateEmail("new@email.com");
        assertEquals("new@email.com", contact.returnEmail());
    }
}
