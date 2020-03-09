import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactManagerTest {

    ContactManager contactManager;
    ArrayList<Contact> contactList;
    ConsoleIO consoleIO;
    Contact contact;

    @Before
    public void initialize() {
        consoleIO = new ConsoleIO(System.in, System.out);
        contactList = new ArrayList<>();
        contactManager = new ContactManager(consoleIO, contactList);
        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        this.contactList.add(contact);
    }

    @Test
    public void testCanReadNewContact() {
        assertEquals("email@email.com", contactManager.returnFieldValue(contact, 6));
    }

    @Test
    public void testCanUpdateNewContact() {
        contactManager.updateFirstName("Updateyupdaterson", contact);
        assertEquals("Updateyupdaterson", contactManager.returnFieldValue(contact, 1));
    }

    @Test
    public void testCanDeleteNewContact() {
        this.contactList.remove(0);
        assertTrue(this.contactList.size() == 0);
    }


    @Test
    public void testCanUpdateFirstName() {
        assertEquals("Namey", contactManager.returnFieldValue(contact, 1));
        contactManager.updateFirstName("Elnamo", contact);
        assertEquals("Elnamo", contactManager.returnFieldValue(contact, 1));
    }

    @Test
    public void testCanUpdateLastName() {
        assertEquals("Namerson", contactManager.returnFieldValue(contact, 2));
        contactManager.updateLastName("Sir", contact);
        assertEquals("Sir", contactManager.returnFieldValue(contact, 2));
    }

    @Test
    public void testCanUpdateAddress() {
        assertEquals("A Palace", contactManager.returnFieldValue(contact, 3));
        contactManager.updateAddress("7 Palace place", contact);
        assertEquals("7 Palace place", contactManager.returnFieldValue(contact, 3));
    }

    @Test
    public void testCanUpdatePhoneNumber() {
        assertEquals("130077", contactManager.returnFieldValue(contact, 4));
        contactManager.updatePhoneNumber("01", contact);
        assertEquals("01", contactManager.returnFieldValue(contact, 4));
    }

    @Test
    public void testCanUpdateDOB() {
        assertEquals("01/01/1999", contactManager.returnFieldValue(contact, 5));
        contactManager.updateDOB("30/01/13", contact);
        assertEquals("30/01/13", contactManager.returnFieldValue(contact, 5));
    }

    @Test
    public void testCanUpdateEmail() {
        assertEquals("email@email.com", contactManager.returnFieldValue(contact, 6));
        contactManager.updateEmail("new@email.com", contact);
        assertEquals("new@email.com", contactManager.returnFieldValue(contact, 6));
    }
}
