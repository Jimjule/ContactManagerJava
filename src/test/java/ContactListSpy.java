import java.util.ArrayList;

public class ContactListSpy extends ContactList {

    private ArrayList contactArray;
    private ConsoleIO consoleIO;
    public boolean displayContactsHasBeenCalled = false;
    public boolean printContactsDetailsHasBeenCalled = false;
    public boolean createContactHasBeenCalled = false;
    public boolean updateContactHasBeenCalled = false;
    public boolean deleteContactHasBeenCalled = false;
    public boolean contactsExistHasBeenCalled;

    public ContactListSpy(ArrayList contactArray, ConsoleIO consoleIO) {
        super(contactArray, consoleIO);
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

    public void showContacts() {
        this.displayContactsHasBeenCalled = true;
    }

    public void printContactDetails(Contact contact) {
        this.printContactsDetailsHasBeenCalled = true;
    }

    public void updateContact() {
        this.updateContactHasBeenCalled = true;
    }

    public void deleteContact(int index) {
        this.deleteContactHasBeenCalled = true;
    }

}
