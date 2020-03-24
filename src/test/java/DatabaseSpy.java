import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DatabaseSpy implements Storage {

    private boolean getContactHasBeenCalled;
    private boolean newContactHasBeenCalled;
    private boolean updateContactHasBeenCalled;
    private boolean deleteContactHasBeenCalled;
    private boolean displayContactsHasBeenCalled;

    private ConsoleIO consoleIO;
    private Connection connection;

    public DatabaseSpy (ArrayList<Contact> contactList, ConsoleIO consoleIO, String database, String dbName) {
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

    @Override
    public void showContacts() {
        this.displayContactsHasBeenCalled = true;
    }

    public boolean contactsExist() {
        return true;
    }

    public boolean returnGetContactHasBeenCalled() {
        return getContactHasBeenCalled;
    }

    public boolean returnDisplayContactsHasBeenCalled() {
        return displayContactsHasBeenCalled;
    }

    public boolean returnCreateContactHasBeenCalled() {
        return newContactHasBeenCalled;
    }

    public boolean returnDeleteContactHasBeenCalled() {
        return deleteContactHasBeenCalled;
    }

    public boolean returnUpdateContactHasBeenCalled() {
        return updateContactHasBeenCalled;
    }
}
