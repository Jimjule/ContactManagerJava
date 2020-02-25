import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainTest {
    @Test
    public void testMainClassIsNotNull() {
        Main main = new Main();
        assertNotNull(main);
    }

    @Test
    public void testInstantiatesOtherClasses() {
        Main main = new Main();
        assertEquals(true, main.input instanceof Input);
        assertEquals(true, main.contact instanceof Contact);
    }
}
