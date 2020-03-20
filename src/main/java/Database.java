import java.sql.*;

public class Database implements Storage {

    ConsoleIO consoleIO;
    Connection connection;
    Statement statement;
    String dbName;

    public Database(ConsoleIO consoleIO, String databaseConnection, String dbName) {
        this.consoleIO = consoleIO;
        this.dbName = dbName;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(databaseConnection, "postgres", "contactManager1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createContact(Contact contact) {
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO " + dbName + " VALUES(DEFAULT, '" +
                    contact.getFieldValue(1) + "', '" + contact.getFieldValue(2) + "', '" + contact.getFieldValue(3) + "', '" + contact.getFieldValue(4) + "', '" + contact.getFieldValue(5) + "', '" + contact.getFieldValue(6) +
                    "');";
            statement.execute(sql);
            statement.close();
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
    public Contact getContact(int index) throws SQLException {
        Contact contact;
            statement = connection.createStatement();
            String getAtIndex = "SELECT * FROM " + dbName + " WHERE id = " + index + ";";
            ResultSet setContact = statement.executeQuery(getAtIndex);
            setContact.next();
            contact = new Contact(
                    setContact.getString("first_name"),
                    setContact.getString("last_name"),
                    setContact.getString("address"),
                    setContact.getString("phone_number"),
                    setContact.getString("dob"),
                    setContact.getString("email"),
                    consoleIO
            );
        statement.close();
        return contact;
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
