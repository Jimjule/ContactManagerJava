import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Database implements Storage {

    private Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    private static String getDBColumnName(int field) {
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
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        java.util.Date parsed;
        try {
            parsed = format.parse(contact.getdOB());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            PreparedStatement create = connection.prepareStatement("INSERT INTO contactmanagerdb VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)");
            create.setString(1, contact.getFirstName());
            create.setString(2, contact.getLastName());
            create.setString(3, contact.getAddress());
            create.setString(4, contact.getPhoneNumber());
            create.setDate(5, new Date(parsed.getTime()));
            create.setString(6, contact.getEmail());
            create.execute();
            create.close();
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteContact(int index) throws SQLException {
        PreparedStatement delete = connection.prepareStatement("DELETE FROM contactmanagerdb WHERE id = (SELECT id FROM contactmanagerdb OFFSET ? LIMIT  1)");
        delete.setInt(1, index - 1);
        delete.execute();
        delete.close();
    }

    @Override
    public void updateContact(Contact contact, int field, String input) throws SQLException {
        if(Contact.validateInput(field, input)) {
            PreparedStatement update = connection.prepareStatement("UPDATE contactmanagerdb SET " + getDBColumnName(field) + "  = ? WHERE email = ?");
            update.setString(1, input);
            update.setString(2, contact.getEmail());
            update.execute();
            update.close();
        }
    }

    @Override
    public Optional<Contact> getContact(int index) throws SQLException {
        Contact contact;
        PreparedStatement getSingle = connection.prepareStatement("SELECT * FROM contactmanagerdb OFFSET ? LIMIT 1");
        getSingle.setInt(1, index - 1);
        ResultSet setContact = getSingle.executeQuery();
        boolean next = setContact.next();
        if(next) {
            contact = new Contact(
                    setContact.getString("first_name"),
                    setContact.getString("last_name"),
                    setContact.getString("address"),
                    setContact.getString("phone_number"),
                    setContact.getString("dob"),
                    setContact.getString("email")
            );
            getSingle.close();
            return Optional.of(contact);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Contact>> showContacts() {
        if (getContacts().size() > 0) {
            List<Contact> contacts = getContacts();
            return Optional.of(contacts);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> contactArray = new LinkedList<>();
        try {
            Contact contact;
            PreparedStatement getAll = connection.prepareStatement("SELECT * FROM contactmanagerdb");
            ResultSet allContacts = getAll.executeQuery();
            while(allContacts.next()) {
                contact = new Contact(
                        allContacts.getString("first_name"),
                        allContacts.getString("last_name"),
                        allContacts.getString("address"),
                        allContacts.getString("phone_number"),
                        allContacts.getString("dob"),
                        allContacts.getString("email")
                );
                contactArray.add(contact);
            }
            getAll.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactArray;
    }

    public boolean contactsExist() {
        return true;
    }
}
