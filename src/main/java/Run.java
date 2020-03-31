import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Run {

    public static void main(String[] args) {
        ConsoleIO consoleIO = new ConsoleIO(System.in, System.out);

        startPostgres();
        runCreateDB();

        Connection connection = getConnection(Constants.CREATE_DB, Constants.PROD_DATABASE);

        ArrayList<Contact> arrayList = new ArrayList<>();
        ContactList contactList = new ContactList(arrayList, consoleIO);
        Database database = new Database(connection);
        ContactManager contactManager = new ContactManager(consoleIO, contactList, database);

        consoleIO.clearScreen();
        contactManager.showMenu();
        closeConnection(connection);
    }

    public static void startPostgres() {
        try {
            String[] arguments = {"brew", "services", "start", "postgresql"};
            Process proc = new ProcessBuilder(arguments).start();
            proc.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void runCreateDB() {
        try {
            String createdb = "createdb";
            Process proc = new ProcessBuilder(createdb).start();
            proc.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String createDB, String databaseName) {
        Connection connection = connectLocalhost();

        createDatabase(connection, createDB);
        connection = connectDatabase(connection, Constants.LOCAL_CONNECTION, databaseName);
        createTable(connection);

        return connection;
    }

    public static Connection connectLocalhost() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(Constants.LOCAL_CONNECTION);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            exit(1);
        }
        return connection;
    }

    public static void createDatabase(Connection connection, String createDB) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(createDB);
        } catch (SQLException e) {
        }
    }

    public static Connection connectDatabase(Connection connection, String host, String dbName) {
        try {
            connection = DriverManager.getConnection(host + dbName);
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }
        return connection;
    }

    public static void createTable(Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(Constants.CREATE_TABLE);
        } catch (SQLException e) {
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
