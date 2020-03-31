import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactListSpy implements Storage {

    private ArrayList<Contact> contactArray;
    private ConsoleIO consoleIO;

    private boolean displayContactsHasBeenCalled = false;
    private boolean printContactsDetailsHasBeenCalled = false;
    private boolean createContactHasBeenCalled = false;
    private boolean updateContactHasBeenCalled = false;
    private boolean deleteContactHasBeenCalled = false;
    private boolean contactsExistHasBeenCalled = false;
    private boolean getContactHasBeenCalled = false;

    public ContactListSpy(ArrayList<Contact> contactArray, ConsoleIO consoleIO) {
        this.contactArray = contactArray;
        this.consoleIO = consoleIO;
    }

    public void createContact(Contact contact) {
        this.createContactHasBeenCalled = true;
    }

    public boolean contactsExist() {
        this.contactsExistHasBeenCalled = true;
        return true;
    }

    public Optional<List<Contact>> showContacts() {
        this.displayContactsHasBeenCalled = true;
        return Optional.empty();
    }

    public void printContactDetails(Contact contact) {
        this.printContactsDetailsHasBeenCalled = true;
    }

    public void updateContact(Contact contact, int field, String input) {
        this.updateContactHasBeenCalled = true;
    }

    public void deleteContact(int index) {
        this.deleteContactHasBeenCalled = true;
    }

    public Optional<Contact> getContact(int index) {
        this.getContactHasBeenCalled = true;
        return Optional.empty();
    }

    @Override
    public List<Contact> getContacts() {
        return contactArray;
    }

    public boolean returnCreateContactHasBeenCalled() {
        return createContactHasBeenCalled;
    }

    public boolean returnDeleteContactHasBeenCalled() {
        return deleteContactHasBeenCalled;
    }

    public boolean returnDisplayContactsHasBeenCalled() {
        return displayContactsHasBeenCalled;
    }

    public boolean returnGetContactHasBeenCalled() {
        return getContactHasBeenCalled;
    }

    public boolean returnUpdateContactHasBeenCalled() {
        return updateContactHasBeenCalled;
    }
}
