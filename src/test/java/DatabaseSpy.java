import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class DatabaseSpy implements Storage {

    private boolean getContactHasBeenCalled;
    private boolean newContactHasBeenCalled;
    private boolean updateContactHasBeenCalled;
    private boolean deleteContactHasBeenCalled;
    private boolean displayContactsHasBeenCalled;

    public DatabaseSpy (Connection connection) {
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

    public Optional<Contact> getContact(int id) {
        getContactHasBeenCalled = true;
        return Optional.empty();
    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }

    @Override
    public Optional<List<Contact>> showContacts() {
        this.displayContactsHasBeenCalled = true;
        return Optional.empty();
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
