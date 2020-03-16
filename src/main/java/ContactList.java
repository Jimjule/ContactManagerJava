import java.util.ArrayList;

public class ContactList extends ArrayList<Contact> {

    private ArrayList contactArray;
    private ConsoleIO consoleIO;

    public ContactList(ArrayList contactArray, ConsoleIO consoleIO) {
        this.contactArray = contactArray;
        this.consoleIO = consoleIO;
    }

    public void newContact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Contact contact = new Contact(firstName, lastName, address, phoneNumber, dOB, email);
        contactArray.add(contact);
    }

    public boolean contactsExist() {
        return !(contactArray.size() == 0);
    }

    public void displayContacts() {
        for (int i = 0; i < contactArray.size(); i++) {
            consoleIO.display(String.valueOf(i + 1));
            printContactDetails((Contact) contactArray.get(i));
        }
    }

    public void printContactDetails(Contact contact) {
        consoleIO.display(Contact.getFieldName(1) + ": " + contact.getFieldValue(1) + "\n" +
                Contact.getFieldName(2) + ": " + contact.getFieldValue(2) + "\n" +
                Contact.getFieldName(3) + ": " + contact.getFieldValue(3) + "\n" +
                Contact.getFieldName(4) + ": " + contact.getFieldValue(4) + "\n" +
                Contact.getFieldName(5) + ": " + contact.getFieldValue(5) + "\n" +
                Contact.getFieldName(6) + ": " + contact.getFieldValue(6)
        );
    }

    public void updateContact() {
        try {
            int contactNumber = consoleIO.getNumberInput();
            Contact contact = (Contact) contactArray.get((contactNumber) - 1);
            updateContactFields(contact);
        } catch (Exception e) {
            consoleIO.clearScreen();
            consoleIO.display("No such contact");
        }
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
