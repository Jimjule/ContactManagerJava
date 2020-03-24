import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseSpy extends Database implements Storage {

    public boolean getContactHasBeenCalled;
    public boolean newContactHasBeenCalled;
    public boolean updateContactHasBeenCalled;
    public boolean deleteContactHasBeenCalled;

    ConsoleIO consoleIO;
    Connection connection;

    public DatabaseSpy (ConsoleIO consoleIO, String database, String dbName) {
        super(consoleIO, Constants.testContactManagerDB, Constants.DBName);
        this.consoleIO = consoleIO;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(database, "postgres", "contactManager1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createContact(Contact contact) {
        this.newContactHasBeenCalled = true;
    }


    public void deleteContact(int index) {
        deleteContactHasBeenCalled = true;
    }

    @Override
    public void updateContact() {

    }

    public void updateContact(String id, String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        updateContactHasBeenCalled = true;
    }

    public void getContact() {
        getContactHasBeenCalled = true;
    }

    public boolean contactsExist() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented");
        return false;
    }
}
