import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    DatabaseSpy database;

    @Before
    public void setUp() {
        database = new DatabaseSpy();
    }

    @Test
    public void createsNewContact() {
        database.newContact("First1", "Last2", "Addressing", "77787", "01/02/1934", "me@place.org");
        assertTrue(database.fetchNewConnectionSuccessful);
        assertEquals("First1", database.fetchNewFirstName);
        assertEquals("Last2", database.fetchNewLastName);
        assertEquals("Addressing", database.fetchNewAddress);
        assertEquals("77787", database.fetchNewPhoneNumber);
        assertEquals("01/02/1934", database.fetchNewDOB);
        assertEquals("me@place.org", database.fetchNewEmail);
    }

    @Test
    public void updateContact() {
        database.updateContact("1","Update First", "Change Last", "Change of Address", "5550123", "30/12/2015", "much@improved.email");
        assertTrue(database.fetchUpdateConnectionSuccessful);
        assertEquals("1", database.fetchUpdateID);
        assertEquals("Update First", database.fetchUpdateFirstName);
        assertEquals("Change Last", database.fetchUpdateLastName);
        assertEquals("Change of Address", database.fetchUpdateAddress);
        assertEquals("5550123", database.fetchUpdatePhoneNumber);
        assertEquals("30/12/2015", database.fetchUpdateDOB);
        assertEquals("much@improved.email", database.fetchUpdateEmail);
    }
}