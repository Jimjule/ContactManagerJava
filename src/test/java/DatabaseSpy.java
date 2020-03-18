import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseSpy extends Database implements Storage {

    public boolean displayContactsHasBeenCalled;
    public boolean newContactHasBeenCalled;
    public boolean updateContactHasBeenCalled;
    public boolean deleteContactHasBeenCalled;

    ConsoleIO consoleIO;
    Connection connection;

    public DatabaseSpy (ConsoleIO consoleIO) {
        super(consoleIO);
        this.consoleIO = consoleIO;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
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

    @Override
    public void showContact(int index) {

    }

    public void updateContact(String id, String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        updateContactHasBeenCalled = true;
    }

    public void showContacts() {
        displayContactsHasBeenCalled = true;
    }

    public boolean contactsExist() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented");
        return false;
    }
}
