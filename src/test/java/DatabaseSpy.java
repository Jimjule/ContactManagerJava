import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseSpy {

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

    public int newContact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Connection connection;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            if (connection != null) {
                connection.close();
                fetchNewConnectionSuccessful = true;
                fetchNewFirstName = firstName;
                fetchNewLastName = lastName;
                fetchNewAddress = address;
                fetchNewPhoneNumber = phoneNumber;
                fetchNewDOB = dOB;
                fetchNewEmail = email;
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return - 1;
        }
    }

    public int updateContact(String id, String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Connection connection;
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
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return - 1;
        }

    }

    public int connectionTest() {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            if (connection != null) {
                connection.close();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return - 1;
        }
    }
}
