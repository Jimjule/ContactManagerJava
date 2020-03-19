import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    Database database;
    Contact contact;
    Connection connection;
    Statement statement;

    @Before
    public void setUp() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        ConsoleIOSpy consoleIO = new ConsoleIOSpy(fixedInput, System.out);

        contact = new Contact("Jamey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        database = new Database(consoleIO, Constants.testContactManagerDB, Constants.prodDBName);
        try {
            connection = DriverManager.getConnection(Constants.testContactManagerDB, "postgres", "contactManager1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            String sql = "DELETE FROM contactmanagerdb";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createsNewContact() {
        database.createContact(contact);
        database.createContact(contact);
        try {
            String sql = "SELECT count(*) FROM contactmanagerdb";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet set = statement.executeQuery(sql);
            set.next();
            int rows = set.getInt(1);
            assertEquals(2, rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateContact() {
        database.updateContact("1","Update First", "Change Last", "Change of Address", "5550123", "30/12/2015", "much@improved.email");
    }
}
