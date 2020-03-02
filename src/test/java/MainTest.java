import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    Main main;
    Contact contact;
    @Before
    public void initialize() {
        main = new Main();
        main.contactList.add(new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com"));
    }

    @Test
    public void testInstantiatesOtherClasses() {
        assertEquals(true, main.input instanceof Input);
    }

    @Test
    public void testCanCreateNewContact() {
        assertEquals("email@email.com", main.contactList.get(0).returnEmail());
    }

    @Test
    public void testCanUpdateNewContact() {
        main.contactList.get(0).updateFirstName("Updateyupdaterson");
        assertEquals("Updateyupdaterson", main.contactList.get(0).returnFirstName());
    }
}
