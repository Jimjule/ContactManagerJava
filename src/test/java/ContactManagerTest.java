import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactManagerTest {

    ContactManager contactManager;
    ArrayList<Contact> contactList;
    ConsoleInput consoleInput;
    Contact contact;

    @Before
    public void initialize() {
        consoleInput = new ConsoleInput(System.in, System.out);
        contactList = new ArrayList<>();
        contactManager = new ContactManager(consoleInput, contactList);
        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        this.contactList.add(contact);
    }

    @Test
    public void testCanReadNewContact() {
        assertEquals("email@email.com", this.contactList.get(0).returnEmail());
    }

    @Test
    public void testCanUpdateNewContact() {
        this.contactList.get(0).updateFirstName("Updateyupdaterson");
        assertEquals("Updateyupdaterson", this.contactList.get(0).returnFirstName());
    }

    @Test
    public void testCanDeleteNewContact() {
        this.contactList.remove(0);
        assertTrue(this.contactList.size() == 0);
    }
}
