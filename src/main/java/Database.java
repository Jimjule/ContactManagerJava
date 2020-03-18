import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database implements Storage {

    ConsoleIO consoleIO;
    Connection connection;

    public Database(ConsoleIO consoleIO) {
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
        Statement statement;
        try {
            if (connection != null) {
                statement = connection.createStatement();
                String sql = "INSERT INTO contactmanagerdb VALUES(DEFAULT, '" +
                        contact.getFieldValue(1) + "', '" + contact.getFieldValue(2) + "', '" + contact.getFieldValue(3) + "', '" + contact.getFieldValue(4) + "', '" + contact.getFieldValue(5) + "', '" + contact.getFieldValue(6) +
                        "');";
                statement.execute(sql);
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            consoleIO.clearScreen();
            consoleIO.display("Invalid data, please try again");
        }
    }

    public void updateContact(String id, String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        if (connection != null) {
           this.consoleIO.display("Not yet implemented for DB");
        }
    }

    @Override
    public void deleteContact(int index) {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented for DB");

    }

    @Override
    public void updateContact() {

    }

    @Override
    public void showContact(int index) {

    }

    @Override
    public void showContacts() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented for DB");
    }

    public boolean contactsExist() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented");
        return false;
    }
}
