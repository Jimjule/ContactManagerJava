import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSpy implements Storage {

    private boolean getContactHasBeenCalled;
    private boolean newContactHasBeenCalled;
    private boolean updateContactHasBeenCalled;
    private boolean deleteContactHasBeenCalled;
    private boolean displayContactsHasBeenCalled;

    public DatabaseSpy (ArrayList<Contact> contactList, ConsoleIO consoleIO, String database, Connection connection) {
    }

    @Override
    public void createContact(Contact contact) {
        this.newContactHasBeenCalled = true;
    }

    public void deleteContact(int index) {
        deleteContactHasBeenCalled = true;
    }

    public void updateContact(Contact contact, int field, String input) {
        updateContactHasBeenCalled = true;
    }

    public Contact getContact(int id) {
        getContactHasBeenCalled = true;
        return null;
    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }

    @Override
    public void showContacts() {
        this.displayContactsHasBeenCalled = true;
    }

    public boolean contactsExist() {
        return true;
    }

    public boolean returnGetContactHasBeenCalled() {
        return getContactHasBeenCalled;
    }

    public boolean returnDisplayContactsHasBeenCalled() {
        return displayContactsHasBeenCalled;
    }

    public boolean returnCreateContactHasBeenCalled() {
        return newContactHasBeenCalled;
    }

    public boolean returnDeleteContactHasBeenCalled() {
        return deleteContactHasBeenCalled;
    }

    public boolean returnUpdateContactHasBeenCalled() {
        return updateContactHasBeenCalled;
    }
}
