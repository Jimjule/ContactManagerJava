import java.util.List;
import java.util.Optional;

public class ContactList implements Storage {

    private List<Contact> contactArray;
    private ConsoleIO consoleIO;

    public ContactList(List<Contact> contactArray, ConsoleIO consoleIO) {
        this.contactArray = contactArray;
        this.consoleIO = consoleIO;
    }

    public void createContact(Contact contact) {
        contactArray.add(contact);
    }

    @Override
    public Optional<List<Contact>> showContacts() {
        if (contactsExist()) {
            return Optional.of(contactArray);
        }
        return Optional.empty();
    }

    public boolean contactsExist() {
        if (contactArray.size() == 0) {
            consoleIO.display("No contacts yet");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void updateContact(Contact contact, int field, String input) {
        contact.updateField(input, field);
    }

    @Override
    public Optional<Contact> getContact(int index) throws IndexOutOfBoundsException {
        Contact contact = contactArray.get(index - 1);
        return Optional.ofNullable(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return contactArray;
    }

    public void deleteContact(int index) {
        consoleIO.clearScreen();
        try {
            contactArray.remove(index - 1);
            consoleIO.display("Contact deleted");
        } catch (Exception e) {
            consoleIO.display("No such contact");
        }
    }
}
