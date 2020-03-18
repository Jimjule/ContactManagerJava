import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DatabaseTest {

    Database database;
    Contact contact;

    @Before
    public void setUp() {
       String testString = "Testing";
       InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
       ConsoleIOSpy consoleIO = new ConsoleIOSpy(fixedInput, System.out);

        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
        database = new Database(consoleIO);
    }

    @Test
    public void createsNewContact() {
        database.createContact(contact);

    }

    @Test
    public void updateContact() {
        database.updateContact("1","Update First", "Change Last", "Change of Address", "5550123", "30/12/2015", "much@improved.email");
    }
}