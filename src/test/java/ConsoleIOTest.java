import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConsoleIOTest {

    private ConsoleIO consoleIO;

    @Before
    public void setUp() {
        this.consoleIO = new ConsoleIO(System.in, System.out);
    }

    @Test
    public void testValidInput() {
        assertTrue(consoleIO.validateInput("phone number","077075643", false));
    }
}
