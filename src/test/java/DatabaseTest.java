import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Database database;
    private Contact contact;
    private Contact secondContact;
    private Connection connection;
    private Statement statement;

    public static String defaultFirstName = "Jamey";

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws SQLException {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        ConsoleIOSpy consoleIO = new ConsoleIOSpy(fixedInput, outputStream);

        contact = new Contact(defaultFirstName, "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        secondContact = new Contact(defaultFirstName, "Namerson", "A Palace", "130077", "01/01/1999", "secondemail@email.com");

        connection = Run.getConnection(Constants.CREATE_TEST_DB, Constants.TEST_DATABASE);
        database = new Database(connection);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        String sql = "DELETE FROM contactmanagerdb";
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.execute(sql);
    }

    @Test
    public void createsNewContact() throws SQLException {
        database.createContact(contact);
        String getCount = "SELECT * FROM contactmanagerdb";
        ResultSet countAll = statement.executeQuery(getCount);
        countAll.next();
        assertEquals(1, countAll.getRow());
    }

    @Test
    public void deletesContact() throws Exception {
        database.createContact(contact);
        database.deleteContact(1);

        String getCount = "SELECT * FROM contactmanagerdb;";
        ResultSet countAll = statement.executeQuery(getCount);
        countAll.next();

        assertEquals(0, countAll.getRow());
    }

    @Test
    public void getContact() throws Exception {
        database.createContact(contact);

        Contact contact = database.getContact(1).get();

        assertNotNull(contact);
        assertEquals(contact.getFirstName(), "Jamey");
        assertEquals(contact.getLastName(), "Namerson");
        assertEquals(contact.getAddress(), "A Palace");
        assertEquals(contact.getPhoneNumber(), "130077");
        assertEquals(contact.getEmail(), "email@email.com");
    }

    @Test
    public void showContacts() {
        database.createContact(contact);
        database.createContact(secondContact);
        List<Contact> contacts = database.getContacts();
        assertEquals(2, contacts.size());
    }

    @Test
    public void updateContact() throws Exception {
        database.createContact(contact);
        database.updateContact(contact, 1, "Updatename");
        Contact updatedContact = database.getContact(1).get();
        assertEquals("Updatename", updatedContact.getFirstName());
    }

    @Test
    public void updateContactFails() throws Exception {
        database.createContact(contact);
        database.updateContact(contact, 1, "lowercase");
        Contact updatedContact = database.getContact(1).get();
        assertEquals(defaultFirstName, updatedContact.getFirstName());
    }
}
