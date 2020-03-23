import java.util.ArrayList;

public class ContactList implements Storage {

    private ArrayList contactArray;
    private ConsoleIO consoleIO;

    public ContactList(ArrayList contactArray, ConsoleIO consoleIO) {
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
                ((Contact) contactArray.get(i)).printContactDetails();
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
        Contact contact = (Contact) contactArray.get(index - 1);
        return contact;
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
