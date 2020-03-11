import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ContactTest {

    Contact contact;
    @Before
    public void initialize() {
       contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
    }

    @Test
    public void canUpdateFirstName() {
        assertEquals("Namey", contact.getFieldValue(1));
        contact.updateField("Elnamo", 1);
        assertEquals("Elnamo", contact.getFieldValue(1));
    }

    @Test
    public void canUpdateLastName() {
        assertEquals("Namerson", contact.getFieldValue(2));
        contact.updateField("Sir", 2);
        assertEquals("Sir", contact.getFieldValue(2));
    }

    @Test
    public void canUpdateAddress() {
        assertEquals("A Palace", contact.getFieldValue(3));
        contact.updateField("7 Palace place", 3);
        assertEquals("7 Palace place", contact.getFieldValue(3));
    }

    @Test
    public void canUpdatePhoneNumber() {
        assertEquals("130077", contact.getFieldValue(4));
        contact.updateField("01",  4);
        assertEquals("01", contact.getFieldValue(4));
    }

    @Test
    public void canUpdateDOB() {
        assertEquals("01/01/1999", contact.getFieldValue(5));
        contact.updateField("30/01/2013", 5);
        assertEquals("30/01/2013", contact.getFieldValue(5));
    }

    @Test
    public void canUpdateEmail() {
        assertEquals("email@email.com", contact.getFieldValue(6));
        contact.updateField("new@email.com", 6);
        assertEquals("new@email.com", contact.getFieldValue(6));
    }
}
