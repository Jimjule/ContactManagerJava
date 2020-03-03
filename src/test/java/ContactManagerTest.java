import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactManagerTest {

    ContactManager contactManager;
    Contact contact;
    @Before
    public void initialize() {
        contactManager = new ContactManager();
        contactManager.contactList.add(new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com"));
    }

    @Test
    public void testInstantiatesOtherClasses() {
        assertEquals(true, contactManager.consoleInput instanceof ConsoleInput);
    }

    @Test
    public void testCanCreateNewContact() {
        assertEquals("email@email.com", contactManager.contactList.get(0).returnEmail());
    }

    @Test
    public void testCanUpdateNewContact() {
        contactManager.contactList.get(0).updateFirstName("Updateyupdaterson");
        assertEquals("Updateyupdaterson", contactManager.contactList.get(0).returnFirstName());
    }
}
