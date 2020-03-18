import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ContactManagerTest {

    ContactManager contactManager;
    ContactListSpy contactList;
    ConsoleIOSpy consoleIO;
    DatabaseSpy database;

    @Before
    public void setUp() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        consoleIO = new ConsoleIOSpy(fixedInput, System.out);
        ArrayList arrayList = new ArrayList<Contact>();
        contactList = new ContactListSpy(arrayList, consoleIO);

        contactManager = new ContactManager(consoleIO, contactList, database);
    }

    @Test
    public void newContactGetsInputs() {
        contactManager.storage = contactList;
        contactManager.newContact();
        assertEquals(true, contactList.createContactHasBeenCalled);
    }

    @Test
    public void updateCallsStorageUpdate() {
        contactManager.storage = contactList;
        contactManager.newContact();
        contactManager.updateContact();
        assertTrue(contactList.updateContactHasBeenCalled);
    }

    @Test
    public void deleteAsksForNumberInput() {
        contactManager.storage = contactList;
        contactManager.newContact();
        contactManager.deleteContact();
        assertEquals(true, contactList.deleteContactHasBeenCalled);
    }

    @Test
    public void viewContactsCallsCorrectMethods() {
        contactManager.storage = contactList;
        contactManager.displayContacts();
        assertEquals(true, contactList.displayContactsHasBeenCalled);
    }
}
