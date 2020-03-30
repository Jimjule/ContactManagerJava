import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactManagerTest {

    private ContactManager contactManager;
    private ContactListSpy contactList;
    private ConsoleIOSpy consoleIO;
    private DatabaseSpy database;
    private Contact contact;
    private Connection connection;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public ContactManagerTest() {
        Run.startPostgres();
        Run.startDatabaseCluster();
        connection = Run.getConnection(Constants.CREATETESTDB, Constants.TESTDATABASE);
    }

    @BeforeEach
    public void setUp() {

        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());

        consoleIO = new ConsoleIOSpy(fixedInput, outputStream);
        ArrayList<Contact> arrayList = new ArrayList<Contact>();


        database = new DatabaseSpy(arrayList, consoleIO, connection);
        contactList = new ContactListSpy(arrayList, consoleIO);

        contact = new Contact("Name", "Lastname", "Yep", "812739", "10/11/1987", "first@email");
        arrayList.add(contact);
        contactManager = new ContactManager(consoleIO, contactList, database);
    }

    @Test
    public void newContactGetsInputs() {
        ContactManager contactManager = new ContactManager(consoleIO, contactList, contactList);
        contactManager.selectStorageDestination();
        contactManager.newContact();
        assertEquals(true, contactList.returnCreateContactHasBeenCalled());
    }

    @Test
    public void deleteAsksForNumberInput() {
        ContactManager contactManager = new ContactManager(consoleIO, contactList, contactList);
        contactManager.newContact();
        contactManager.deleteContact();
        assertEquals(true, contactList.returnDeleteContactHasBeenCalled());
    }

    @Test
    public void updateCallsGetNumberInput() {
        contactManager.updateContact();
        assertTrue(consoleIO.returnGetNumberInputHasBeenCalled());
    }

    @Test
    public void viewContactsCallsCorrectMethods() {
        ContactManager contactManager = new ContactManager(consoleIO, contactList, contactList);
        contactManager.showContacts();
        assertEquals(true, contactList.returnDisplayContactsHasBeenCalled());
    }

    @Test
    public void getContact() {
        ContactManager contactManager = new ContactManager(consoleIO, contactList, contactList);
        contactManager.getContact();
        assertEquals(true, contactList.returnGetContactHasBeenCalled());
    }

    @Test
    public void dbGetContact() {
        contactManager.getContact();
        assertEquals(true, database.returnGetContactHasBeenCalled());
    }

    @Test
    public void dbViewContactsCallsCorrectMethods() {
        contactManager.showContacts();
        assertEquals(true, database.returnDisplayContactsHasBeenCalled());
    }

    @Test
    public void dbNewContactGetsInputs() {
        contactManager.newContact();
        assertEquals(true, database.returnCreateContactHasBeenCalled());
    }

    @Test
    public void dbDeleteAsksForNumberInput() {
        contactManager.newContact();
        contactManager.deleteContact();
        assertEquals(true, database.returnDeleteContactHasBeenCalled());
    }
}
