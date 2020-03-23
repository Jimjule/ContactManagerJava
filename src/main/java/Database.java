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
            String addContact = "INSERT INTO " + dbName + " VALUES(DEFAULT, '" +
                    contact.getFieldValue(1) + "', '" + contact.getFieldValue(2) + "', '" + contact.getFieldValue(3) + "', '" + contact.getFieldValue(4) + "', '" + contact.getFieldValue(5) + "', '" + contact.getFieldValue(6) +
                    "');";
            statement.execute(addContact);
            statement.close();
        } catch (SQLException e) {
            consoleIO.clearScreen();
            consoleIO.display("Invalid data, please try again");
        }
    }

    @Override
    public void deleteContact(int index) throws SQLException {
        statement = connection.createStatement();
        String deleteAtIndex = "DELETE FROM " + dbName + " WHERE id = " + index + ";";
        statement.execute(deleteAtIndex);
        statement.close();
        consoleIO.display("Contact Deleted");
    }

    @Override
    public void updateContact(Contact contact, int field, String input) {

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
        consoleIO.display("Not yet implemented for DB");
    }

    public boolean contactsExist() {
        return true;
    }
}
