import java.io.IOException;
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
            String[] arguments = {"createdb"};
            Process proc = new ProcessBuilder(arguments).start();
            proc.waitFor();

            int exit = proc.exitValue();

            consoleIO.display(String.valueOf(exit));
            consoleIO.display("Database cluster started");
        } catch (InterruptedException | IOException e) {
            consoleIO.display("Database cluster already ready!");
            e.printStackTrace();
        }

        try {
            Process proc = new ProcessBuilder(Constants.CREATEUSER).start();
            proc.waitFor();
            int exit = proc.exitValue();

            consoleIO.display(String.valueOf(exit));
            consoleIO.display("User created");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            consoleIO.display("User already exists!");
        }

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(Constants.LOCALCONNECTION);
            consoleIO.display("Connected to Local Server");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            consoleIO.display("Can't connect to Local Server");
            exit(1);
        }
        try {
            statement = connection.createStatement();
            statement.executeUpdate(Constants.CREATEDB);
            consoleIO.display("Created Database!");
        } catch (SQLException e) {
            consoleIO.display("Cannot create Database OR");
            consoleIO.display("Database already exists!");
            e.printStackTrace();
        } finally {
            try {
                connection = DriverManager.getConnection(Constants.LOCALCONNECTION + Constants.LOCALDATABASE);
            } catch (Exception e) {
                consoleIO.display("Can't connect to Local Database");
//                e.printStackTrace();
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
