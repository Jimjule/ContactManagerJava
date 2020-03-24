import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    private Database database;
    private Contact contact;
    private Contact secondContact;
    private Connection connection;
    private Statement statement;
    public ArrayList<Contact> contactArray;

    public static String defaultFirstName = "Jamey";

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws SQLException {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        ConsoleIOSpy consoleIO = new ConsoleIOSpy(fixedInput, outputStream);

        contact = new Contact(defaultFirstName, "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com", consoleIO);
        secondContact = new Contact(defaultFirstName, "Namerson", "A Palace", "130077", "01/01/1999", "secondemail@email.com", consoleIO);

        contactArray = new ArrayList<Contact>();
        database = new Database(contactArray, consoleIO, Constants.testContactManagerDB, Constants.DBName);
        connection = DriverManager.getConnection(Constants.testContactManagerDB, "postgres", "contactManager1");
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

        assertEquals(database.getContact(1).getFirstName(), "Jamey");
        assertEquals(database.getContact(1).getLastName(), "Namerson");
        assertEquals(database.getContact(1).getAddress(), "A Palace");
        assertEquals(database.getContact(1).getPhoneNumber(), "130077");
        assertEquals(database.getContact(1).getEmail(), "email@email.com");
    }

    @Test
    public void showContacts() {
        database.createContact(contact);
        database.createContact(secondContact);
        database.showContacts();
        assertEquals(2, database.contactArray.size());
    }

    @Test
    public void updateContact() throws Exception {
        database.createContact(contact);
        database.updateContact(contact, 1, "Updatename");
        Contact updatedContact = database.getContact(1);
        assertEquals("Updatename", updatedContact.getFirstName());
    }

    @Test
    public void updateContactFails() throws Exception {
        database.createContact(contact);
        database.updateContact(contact, 1, "lowercase");
        Contact updatedContact = database.getContact(1);
        assertEquals(defaultFirstName, updatedContact.getFirstName());
    }
}
