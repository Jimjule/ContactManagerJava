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

    }

    public boolean contactsExist() {
        if (contactArray.size() == 0) {
            consoleIO.display("No contacts yet");
            return false;
        } else {
            return true;
        }
    }

    public void displayContacts() {
        if (contactsExist()) {
            for (int i = 0; i < contactArray.size(); i++) {
                consoleIO.display(String.valueOf(i + 1));
                ((Contact) contactArray.get(i)).printContactDetails();
            }
        }
    }

    public void updateContact() {
        try {
            int contactNumber = consoleIO.getNumberInput();
            Contact contact = (Contact) contactArray.get((contactNumber) - 1);
            updateContactFields(contact);
        } catch (Exception e) {
            consoleIO.display("No such contact");
        }
    }

    @Override
    public Contact getContact(int index) throws Exception {
        Contact contact = null;
        return contact;
    }

    private void updateContactFields(Contact contact) {
        consoleIO.clearScreen();
        consoleIO.display(Constants.updateFields);

        int field = consoleIO.getNumberInput();
        consoleIO.display(Contact.getFieldName(field) + " is currently: " + contact.getFieldValue(field));
        String input = consoleIO.getStringInput(field, Contact.getFieldName(field));
        contact.updateField(input, field);

        consoleIO.clearScreen();
    }

    public void deleteContact(int index) {
        consoleIO.clearScreen();
        try {
            contactArray.remove(index);
            consoleIO.display("Contact deleted");
        } catch (Exception e) {
            consoleIO.display("No such contact");
        }
    }
}
