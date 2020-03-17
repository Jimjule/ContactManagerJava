import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database extends ContactList {

    ConsoleIO consoleIO;

    public Database(ArrayList arrayList, ConsoleIO consoleIO) {
        super(arrayList, consoleIO);
        this.consoleIO = consoleIO;
    }

    public void newContact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            try {
                if (connection != null) {
                    statement = connection.createStatement();
                    String sql = "INSERT INTO contactmanagerdb VALUES(DEFAULT, '" +
                            firstName + "', '" + lastName + "', '" + address + "', '" + phoneNumber + "', '" + dOB + "', '" + email +
                            "');";
                    statement.execute(sql);
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                consoleIO.clearScreen();
                consoleIO.display("Invalid data, please try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContact(String id, String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            if (connection != null) {
               this.consoleIO.display("Not yet implemented for DB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteContact() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented for DB");
    }

    public void displayContacts() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented for DB");
    }

    public boolean contactsExist() {
        consoleIO.clearScreen();
        consoleIO.display("Not yet implemented");
        return false;
    }
}
