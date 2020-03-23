import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DatabaseSpy extends Database implements Storage {

    public boolean getContactHasBeenCalled;
    public boolean newContactHasBeenCalled;
    public boolean updateContactHasBeenCalled;
    public boolean deleteContactHasBeenCalled;

    ConsoleIO consoleIO;
    Connection connection;

    public DatabaseSpy (ArrayList contactList, ConsoleIO consoleIO, String database, String dbName) {
        super(contactList, consoleIO, Constants.testContactManagerDB, Constants.DBName);
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

    public void updateContact(Contact contact, int field, String input) {
        updateContactHasBeenCalled = true;
    }

    public Contact getContact(int id) {
        getContactHasBeenCalled = true;
        return null;
    }

    public boolean contactsExist() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented");
        return false;
    }
}
