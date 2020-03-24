import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactManagerTest {

    private ContactManager contactManager;
    private ContactListSpy contactList;
    private ConsoleIOSpy consoleIO;
    private DatabaseSpy database;
    private Contact contact;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());

        consoleIO = new ConsoleIOSpy(fixedInput, outputStream);
        ArrayList<Contact> arrayList = new ArrayList<Contact>();
        database = new DatabaseSpy(arrayList, consoleIO, Constants.testContactManagerDB, Constants.DBName);
        contactList = new ContactListSpy(arrayList, consoleIO);

        contact = new Contact("Name", "Lastname", "Yep", "812739", "10/11/1987", "first@email", consoleIO);
        arrayList.add(contact);
        contactManager = new ContactManager(consoleIO, contactList, database);
    }

    @Test
    public void newContactGetsInputs() {
        contactManager.storage = contactList;
        contactManager.newContact();
        assertEquals(true, contactList.returnCreateContactHasBeenCalled());
    }


    @Test
    public void deleteAsksForNumberInput() {
        contactManager.storage = contactList;
        contactManager.newContact();
        contactManager.deleteContact();
        assertEquals(true, contactList.returnDeleteContactHasBeenCalled());
    }

    @Test
    public void updateCallsUpdateContactList() {
        contactManager.storage = contactList;
        contactManager.updateContact();
        assertTrue(contactList.returnUpdateContactHasBeenCalled());
    }

    @Test
    public void updateCallsGetNumberInput() {
        contactManager.storage = contactList;
        contactManager.updateContact();
        assertTrue(consoleIO.returnGetNumberInputHasBeenCalled());
    }

    @Test
    public void viewContactsCallsCorrectMethods() {
        contactManager.storage = contactList;
        contactManager.showContacts();
        assertEquals(true, contactList.returnDisplayContactsHasBeenCalled());
    }

    @Test
    public void getContact() {
        contactManager.storage = contactList;
        contactManager.getContact();
        assertEquals(true, contactList.returnGetContactHasBeenCalled());
    }

    @Test
    public void dbGetContact() {
        contactManager.storage = database;
        contactManager.getContact();
        assertEquals(true, database.returnGetContactHasBeenCalled());
    }

    @Test
    public void dbViewContactsCallsCorrectMethods() {
        contactManager.storage = database;
        contactManager.showContacts();
        assertEquals(true, database.returnDisplayContactsHasBeenCalled());
    }

    @Test
    public void dbNewContactGetsInputs() {
        contactManager.storage = database;
        contactManager.newContact();
        assertEquals(true, database.returnCreateContactHasBeenCalled());
    }


    @Test
    public void dbDeleteAsksForNumberInput() {
        contactManager.storage = database;
        contactManager.newContact();
        contactManager.deleteContact();
        assertEquals(true, database.returnDeleteContactHasBeenCalled());
    }

    @Test
    public void dbUpdateCallsGetNumberInput() {
        contactManager.storage = database;
        contactManager.updateContact();
        assertTrue(database.returnUpdateContactHasBeenCalled());
    }
}
