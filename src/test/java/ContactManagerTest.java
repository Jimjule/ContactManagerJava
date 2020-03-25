import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactManagerTest {

    ContactManager contactManager;
    ContactListSpy contactList;
    ConsoleIOSpy consoleIO;
    DatabaseSpy database;
    Contact contact;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());

        consoleIO = new ConsoleIOSpy(fixedInput, outputStream);
        ArrayList<Contact> arrayList = new ArrayList<Contact>();
        contactList = new ContactListSpy(arrayList, consoleIO);

        contact = new Contact("Name", "Lastname", "Yep", "812739", "10/11/1987", "first@email", consoleIO);
        arrayList.add(contact);
        contactManager = new ContactManager(consoleIO, contactList, database);
    }

    @Test
    public void newContactGetsInputs() {
        contactManager.storage = contactList;
        contactManager.newContact();
        assertEquals(true, contactList.createContactHasBeenCalled);
    }


    @Test
    public void deleteAsksForNumberInput() {
        contactManager.storage = contactList;
        contactManager.newContact();
        contactManager.deleteContact();
        assertEquals(true, contactList.deleteContactHasBeenCalled);
    }

    @Test
    public void updateCallsGetNumberInput() {
        contactManager.storage = contactList;
        contactManager.updateContact();
        assertTrue(consoleIO.getNumberInputHasBeenCalled);
    }

    @Test
    public void viewContactsCallsCorrectMethods() {
        contactManager.storage = contactList;
        contactManager.displayContacts();
        assertEquals(true, contactList.displayContactsHasBeenCalled);
    }

    @Test
    public void getContact() {
        contactManager.storage = contactList;
        contactManager.getContact();
        assertEquals(true, contactList.getContactHasBeenCalled);
    }
}
