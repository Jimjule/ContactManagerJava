import java.sql.*;
import java.util.ArrayList;

public class Database implements Storage {

    private ConsoleIO consoleIO;
    private Connection connection;
    private Statement statement;
    private String dbName;
    public ArrayList<Contact> contactArray;

    public Database(ArrayList<Contact> contactArray, ConsoleIO consoleIO, String dbName, Connection connection) {
        this.contactArray = contactArray;
        this.consoleIO = consoleIO;
        this.dbName = dbName;
        this.connection = connection;
    }

    public static String getDBColumnName(int field) {
        switch (field) {
            case 1: return "first_name";
            case 2: return "last_name";
            case 3: return "address";
            case 4: return "phone_number";
            case 5: return "dob";
            default: return "email";
        }
    }

    @Override
    public void createContact(Contact contact) {
        try {
            statement = connection.createStatement();
            String addContact = "INSERT INTO " + dbName + " VALUES(DEFAULT, '" +
                    contact.getFirstName() + "', '" + contact.getLastName() + "', '" + contact.getAddress() + "', '" + contact.getPhoneNumber() + "', '" + contact.getdOB() + "', '" + contact.getEmail() +
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
        String deleteAtIndex = "DELETE FROM " + dbName + " WHERE id = (SELECT id FROM " + dbName + " OFFSET " + (index - 1) + " LIMIT 1)";
        statement.execute(deleteAtIndex);
        statement.close();
        consoleIO.clearScreen();
        consoleIO.display("Contact Deleted");
    }

    @Override
    public void updateContact(Contact contact, int field, String input) throws SQLException {
        if(Contact.validateInput(field, input)) {
            statement = connection.createStatement();
            String updateEntry = "UPDATE " + dbName + " SET " + getDBColumnName(field) + " = '" + input + "' WHERE email = '" + contact.getEmail() + "';";
            statement.execute(updateEntry);
        } else {
            consoleIO.display("Invalid input for this field.");
        }
    }

    @Override
    public Contact getContact(int index) throws SQLException {
        Contact contact;
            statement = connection.createStatement();
            String getAtIndex = "SELECT * FROM " + dbName + " OFFSET " + (index - 1) + " LIMIT 1;";
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
        getContacts();
        if (contactsExist()) {
            for (int i = 0; i < contactArray.size(); i++) {
                consoleIO.display(String.valueOf(i + 1));
                contactArray.get(i).printContactDetails();
            }
        }
    }

    public void getContacts() {
        try {
            Contact contact;
            statement = connection.createStatement();
            String getAllContacts = "SELECT * FROM " + dbName + " ;";
            ResultSet allContacts = statement.executeQuery(getAllContacts);
            contactArray.removeAll(contactArray);
            while(allContacts.next()) {
                contact = new Contact(
                        allContacts.getString("first_name"),
                        allContacts.getString("last_name"),
                        allContacts.getString("address"),
                        allContacts.getString("phone_number"),
                        allContacts.getString("dob"),
                        allContacts.getString("email"),
                        consoleIO
                );
                contactArray.add(contact);
            }
        } catch (SQLException e) {
            consoleIO.display("Can't display contacts");
        }
    }

    public boolean contactsExist() {
        return true;
    }
}
