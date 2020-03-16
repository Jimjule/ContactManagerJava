import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactListTest {

    ContactList contactList;
    ConsoleIOSpy consoleIO;

    @Before
    public void setUp() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        consoleIO = new ConsoleIOSpy(fixedInput, System.out);
        ArrayList arrayList = new ArrayList<Contact>();
        contactList = new ContactListSpy(arrayList, consoleIO);
    }

    @Test
    public void savesNewContact() {
        contactList.newContact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        assertTrue(contactList.contactsExist());
    }

    @Test
    public void updateCallsGetNumberInput() {
        contactList.updateContact();
        assertTrue(consoleIO.getNumberInputHasBeenCalled);
    }

    @Test
    public void deletesNewContact() {
        contactList.newContact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        contactList.deleteContact(0);
        assertEquals(false, contactList.contactsExist());
    }
}