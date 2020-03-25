import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Run {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement;

        ConsoleIO consoleIO = new ConsoleIO(System.in, System.out);

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(Constants.LOCALCONNECTION, Constants.USERNAME, Constants.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            consoleIO.display("Can't connect to Local Server");
            exit(1);
        }
        try {
            statement = connection.createStatement();
            statement.executeUpdate(Constants.CREATEDB);
            consoleIO.display("Created Database!");
        } catch (SQLException e) {
            consoleIO.display("Database already exists!");
        } finally {
            try {
                connection = DriverManager.getConnection(Constants.LOCALCONNECTION + Constants.LOCALDATABASE, Constants.USERNAME, Constants.PASSWORD);
            } catch (Exception e) {
                consoleIO.display("Can't connect to Local Database");
                exit(1);
            } finally {
                try {
                    statement = connection.createStatement();
                    statement.executeUpdate(Constants.CREATETABLE);
                } catch (SQLException e) {
                    consoleIO.display("Table already exists!");
                } finally {
                    ArrayList<Contact> arrayList = new ArrayList<>();
                    ContactList contactList = new ContactList(arrayList, consoleIO);
                    Database database = new Database(arrayList, consoleIO, Constants.DBNAME, connection);
                    ContactManager contactManager = new ContactManager(consoleIO, contactList, database);

                    consoleIO.clearScreen();
                    contactManager.showMenu();
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
