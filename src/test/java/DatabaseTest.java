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

    Database database;
    Contact contact;
    Contact secondContact;
    Connection connection;
    Statement statement;
    ArrayList<Contact> contactArray;

    public static String defaultFirstName = "Jamey";

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws SQLException {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        ConsoleIOSpy consoleIO = new ConsoleIOSpy(fixedInput, outputStream);

        contact = new Contact(defaultFirstName, "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com", consoleIO);
        secondContact = new Contact(defaultFirstName, "Namerson", "A Palace", "130077", "01/01/1999", "secondemail@email.com", consoleIO);

        contactArray = new ArrayList<>();
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
        database.deleteContact(0);

        String getCount = "SELECT * FROM contactmanagerdb;";
        ResultSet countAll = statement.executeQuery(getCount);
        countAll.next();

        assertEquals(0, countAll.getRow());
    }

    @Test
    public void getContact() throws Exception {
        database.createContact(contact);

        int contactID = getContactID(defaultFirstName);

        String getContactByID = "SELECT * FROM contactmanagerdb WHERE id = " + contactID + ";";
        ResultSet setContact = statement.executeQuery(getContactByID);
        setContact.next();

        assertEquals(database.getContact(contactID).getFirstName(), "Jamey");
        assertEquals(database.getContact(contactID).getLastName(), "Namerson");
        assertEquals(database.getContact(contactID).getAddress(), "A Palace");
        assertEquals(database.getContact(contactID).getPhoneNumber(), "130077");
        assertEquals(database.getContact(contactID).getEmail(), "email@email.com");
    }

    @Test
    public void showContacts() {
        database.createContact(contact);
        database.createContact(secondContact);
        database.showContacts();
        System.out.println(database.contactArray.size());
        assertEquals(2, database.contactArray.size());
    }

    @Test
    public void updateContact() throws Exception {
        database.createContact(contact);
        database.updateContact(contact, 1, "Updatename");
        Contact updatedContact = database.getContact(getContactID("Updatename"));
        assertEquals("Updatename", updatedContact.getFirstName());
    }

    @Test
    public void updateContactFails() throws Exception {
        database.createContact(contact);
        database.updateContact(contact, 1, "lowercase");
        Contact updatedContact = database.getContact(getContactID(defaultFirstName));
        assertEquals(defaultFirstName, updatedContact.getFirstName());
    }

    public int getContactID(String name) throws Exception {
        String getCreatedContactID = "SELECT id FROM contactmanagerdb WHERE first_name = '"+ name + "'";
        ResultSet getID = statement.executeQuery(getCreatedContactID);
        getID.next();
        int contactID = Integer.parseInt(getID.getString(1));
        return contactID;
    }
}
