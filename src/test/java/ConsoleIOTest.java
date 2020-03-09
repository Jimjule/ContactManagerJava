import org.junit.Before;

public class ConsoleIOTest {

    private ConsoleIO consoleIO;

    @Before
    public void setUp() {
        this.consoleIO = new ConsoleIO(System.in, System.out);
    }


}
