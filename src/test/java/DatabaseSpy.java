import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DatabaseSpy extends Database {

    public String fetchNewFirstName;
    public String fetchNewLastName;
    public String fetchNewAddress;
    public String fetchNewPhoneNumber;
    public boolean fetchNewConnectionSuccessful;
    public String fetchNewDOB;
    public String fetchNewEmail;

    public String fetchUpdateFirstName;
    public String fetchUpdateLastName;
    public String fetchUpdateAddress;
    public String fetchUpdatePhoneNumber;
    public boolean fetchUpdateConnectionSuccessful;
    public String fetchUpdateDOB;
    public String fetchUpdateEmail;
    public String fetchUpdateID;
    public boolean displayContactsHasBeenCalled;
    public boolean newContactHasBeenCalled;
    public boolean updateContactHasBeenCalled;
    public boolean deleteContactHasBeenCalled;

    public DatabaseSpy(ArrayList arrayList, ConsoleIO consoleIO) {
        super(arrayList, consoleIO);
    }

    public void newContact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        System.out.println("Made it");
        Connection connection;
        this.newContactHasBeenCalled = true;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            if (connection != null) {
                connection.close();
                this.fetchNewConnectionSuccessful = true;
                this.fetchNewFirstName = firstName;
                this.fetchNewLastName = lastName;
                this.fetchNewAddress = address;
                this.fetchNewPhoneNumber = phoneNumber;
                this.fetchNewDOB = dOB;
                this.fetchNewEmail = email;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContact(String id, String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Connection connection;
        updateContactHasBeenCalled = true;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            if (connection != null) {
                connection.close();
                fetchUpdateConnectionSuccessful = true;
                fetchUpdateID = id;
                fetchUpdateFirstName = firstName;
                fetchUpdateLastName = lastName;
                fetchUpdateAddress = address;
                fetchUpdatePhoneNumber = phoneNumber;
                fetchUpdateDOB = dOB;
                fetchUpdateEmail = email;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteContact() {
        deleteContactHasBeenCalled = true;
    }

    public void displayContacts() {
        displayContactsHasBeenCalled = true;
    }

    public boolean contactsExist() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented");
        return false;
    }
}
