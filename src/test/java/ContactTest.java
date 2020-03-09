import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ContactTest {

    Contact contact;
    @Before
    public void initialize() {
       contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
    }
}
