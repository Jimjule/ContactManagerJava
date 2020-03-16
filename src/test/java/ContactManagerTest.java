import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactManagerTest {

    ContactManager contactManager;
    ArrayList<Contact> contactList;
    ConsoleIOSpy consoleIO;
    Contact contact;
    Database database;

    @Before
    public void setUp() {
        contactList = new ArrayList<>();
        String testString = "";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        consoleIO = new ConsoleIOSpy(fixedInput, System.out);

        contactManager = new ContactManager(consoleIO, contactList, database);
        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        this.contactList.add(contact);
    }

    @Test
    public void callsGetConsoleInput() {
        contactManager.newContact();
        assertTrue(consoleIO.getStringInputHasBeenCalled);
        assertTrue(consoleIO.getInputHasBeenCalled);
    }

    @Test
    public void updateCallsGetNumberInput() {
        contactManager.updateContact();
        assertTrue(consoleIO.getNumberInputHasBeenCalled);
        assertTrue(consoleIO.getStringInputHasBeenCalled);
        assertTrue(consoleIO.getInputHasBeenCalled);
    }

    @Test
    public void deleteCallsGetNumberInput() {
        contactManager.deleteContact();
        assertTrue(consoleIO.getNumberInputHasBeenCalled);
    }

    @Test
    public void canReadNewContactInMemory() {
        assertEquals("email@email.com", contact.getFieldValue(6));
    }

    @Test
    public void canDeleteFromMemory() {
        this.contactList.remove(0);
        assertTrue(this.contactList.size() == 0);
    }
}
