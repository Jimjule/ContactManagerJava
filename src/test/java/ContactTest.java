import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ContactTest {

    @Test
    public void testContactClassIsNotNull() {
        Contact contact = new Contact();
        assertNotNull(contact);
    }

    @Test
    public void testCanUpdateLastName() {
        Contact contact = new Contact();
        assertEquals(null, contact.returnLastName());
        String input = "Sir";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        contact.updateLastName(input);
        assertEquals("Sir", contact.returnLastName());
    }

    @Test
    public void testCanUpdateFirstName() {
        Contact contact = new Contact();
        assertEquals(null, contact.returnLastName());
        String input = "Name";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        contact.updateFirstName(input);
        assertEquals("Name", contact.returnFirstName());
    }

    @Test
    public void testCanUpdateAddress() {
        Contact contact = new Contact();
        assertEquals(null, contact.returnAddress());
        String input = "7 Palace place";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        contact.updateAddress(input);
        assertEquals("7 Palace place", contact.returnAddress());
    }

    @Test
    public void testCanUpdatePhoneNumber() {
        Contact contact = new Contact();
        assertEquals(null, contact.returnPhoneNumber());
        String input = "01";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        contact.updatePhoneNumber(input);
        assertEquals("01", contact.returnPhoneNumber());
    }

    @Test
    public void testCanUpdateDOB() {
        Contact contact = new Contact();
        assertEquals(null, contact.returnDOB());
        String input = "30/01/13";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        contact.updateDOB(input);
        assertEquals("30/01/13", contact.returnDOB());
    }

    @Test
    public void testCanUpdateEmail() {
        Contact contact = new Contact();
        assertEquals(null, contact.returnEmail());
        String input = "email@email.com";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        contact.updateEmail(input);
        assertEquals("email@email.com", contact.returnEmail());
    }
}
