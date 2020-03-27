import java.util.ArrayList;
import java.util.List;

public class ContactList implements Storage {

    private ArrayList<Contact> contactArray;
    private ConsoleIO consoleIO;

    public ContactList(ArrayList<Contact> contactArray, ConsoleIO consoleIO) {
        this.contactArray = contactArray;
        this.consoleIO = consoleIO;
    }

    public void createContact(Contact contact) {
        contactArray.add(contact);
    }

    @Override
    public void showContacts() {
        if (contactsExist()) {
            for (int i = 0; i < contactArray.size(); i++) {
                consoleIO.display(String.valueOf(i + 1));
                contactArray.get(i).printContactDetails();
            }
        }
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
    public Contact getContact(int index) throws IndexOutOfBoundsException {
        Contact contact = contactArray.get(index - 1);
        return contact;
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
