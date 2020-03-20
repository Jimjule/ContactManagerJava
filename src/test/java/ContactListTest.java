import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactListTest {

    ContactList contactList;
    ConsoleIOSpy consoleIO;
    Contact contact;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        consoleIO = new ConsoleIOSpy(fixedInput, outputStream);
        ArrayList arrayList = new ArrayList<Contact>();
        contactList = new ContactList(arrayList, consoleIO);
        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com", consoleIO);
    }

    @Test
    public void savesNewContact() {
        contactList.createContact(contact);
        assertTrue(contactList.contactsExist());
    }

    @Test
    public void updateCallsGetNumberInput() {
        contactList.updateContact();
        assertTrue(consoleIO.getNumberInputHasBeenCalled);
    }

    @Test
    public void deletesNewContact() {
        contactList.createContact(contact);
        contactList.deleteContact(0);
        assertEquals(false, contactList.contactsExist());
    }
}