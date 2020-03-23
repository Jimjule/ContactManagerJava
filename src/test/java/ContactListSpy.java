import java.util.ArrayList;

public class ContactListSpy extends ContactList {

    private ArrayList contactArray;
    private ConsoleIO consoleIO;
    public boolean displayContactsHasBeenCalled = false;
    public boolean printContactsDetailsHasBeenCalled = false;
    public boolean newContactHasBeenCalled = false;
    public boolean updateContactHasBeenCalled = false;
    public boolean deleteContactHasBeenCalled = false;

    public String firstNameData;
    public String lastNameData;
    public String addressData;
    public String phoneNumberData;
    public String dOBData;
    public String emailData;
    public boolean updateFieldsHasBeenCalled;
    public boolean contactsExistHasBeenCalled;

    public ContactListSpy(ArrayList contactArray, ConsoleIO consoleIO) {
        super(contactArray, consoleIO);
        this.contactArray = contactArray;
        this.consoleIO = consoleIO;
    }

    public void newContact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        this.firstNameData = firstName;
        this.lastNameData = lastName;
        this.addressData = address;
        this.phoneNumberData = phoneNumber;
        this.dOBData = dOB;
        this.emailData = email;
        this.newContactHasBeenCalled = true;
        Contact contact = new Contact(firstName, lastName, address, phoneNumber, dOB, email);
        contactArray.add(contact);
    }

    public boolean contactsExist() {
        this.contactsExistHasBeenCalled = true;
        return !(contactArray.size() == 0);
    }

    public void displayContacts() {
        this.displayContactsHasBeenCalled = true;
        if (contactsExist()) {
            for (int i = 0; i < contactArray.size(); i++) {
                consoleIO.display(String.valueOf(i + 1));
                printContactDetails((Contact) contactArray.get(i));
            }
        }
    }

    public void printContactDetails(Contact contact) {
        this.printContactsDetailsHasBeenCalled = true;
        consoleIO.display(Contact.getFieldName(1) + ": " + contact.getFieldValue(1) + "\n" +
                Contact.getFieldName(2) + ": " + contact.getFieldValue(2) + "\n" +
                Contact.getFieldName(3) + ": " + contact.getFieldValue(3) + "\n" +
                Contact.getFieldName(4) + ": " + contact.getFieldValue(4) + "\n" +
                Contact.getFieldName(5) + ": " + contact.getFieldValue(5) + "\n" +
                Contact.getFieldName(6) + ": " + contact.getFieldValue(6)
        );
    }

    public void updateContact() {
        this.updateContactHasBeenCalled = true;
        try {
            int contactNumber = consoleIO.getNumberInput();
            Contact contact = (Contact) contactArray.get((contactNumber) - 1);
            updateContactFields(contact);
        } catch (Exception e) {
            consoleIO.display("No such contact");
        }
    }

    private void updateContactFields(Contact contact) {
        this.updateFieldsHasBeenCalled = true;
        consoleIO.clearScreen();
        consoleIO.display(Constants.updateFields);

        int field = consoleIO.getNumberInput();
        consoleIO.display(Contact.getFieldName(field) + " is currently: " + contact.getFieldValue(field));
        String input = consoleIO.getStringInput(field, Contact.getFieldName(field));
        contact.updateField(input, field);

        consoleIO.clearScreen();
    }

    public void deleteContact(int index) {
        this.deleteContactHasBeenCalled = true;
        contactArray.remove(index);
    }

}
