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
        contactList = new ArrayList<>();
        contactManager = new ContactManager(consoleIO, contactList);
        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        this.contactList.add(contact);
    }

    @Test
    public void canReadNewContact() {
        assertEquals("email@email.com", contact.getFieldValue(6));
    }

    @Test
    public void canDeleteNewContact() {
        this.contactList.remove(0);
        assertTrue(this.contactList.size() == 0);
    }

}
