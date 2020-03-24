import java.sql.SQLException;

public class ContactManager {

    private ConsoleIO consoleIO;
    private ContactList contactList;
    private Database database;
    public Storage storage;

    public ContactManager(ConsoleIO consoleIO, ContactList contactList, Database database) {
        this.consoleIO = consoleIO;
        this.database = database;
        this.contactList = contactList;
        this.storage = selectStorageDestination();
    }

    public Storage selectStorageDestination() {
        consoleIO.clearScreen();
        consoleIO.display("Welcome to Contact Manager");
        consoleIO.display("Would you like to save to the database? (y/N)");
        consoleIO.clearScreen();
        return getStorage();
    }

    public Storage getStorage() {
        boolean useDatabase = consoleIO.getBoolean();
        if(useDatabase == true) {
            return database;
        } else {
            return contactList;
        }
    }

    public void printMenuOptions() {
        consoleIO.display(Constants.menuOptions);
    }

    public void showMenu() {
        boolean complete = false;
        while (!complete) {
            printMenuOptions();
            int userInput = consoleIO.getNumberInput();
            switch (userInput) {
                case 1: {
                    newContact();
                    break;
                }
                case 2: {
                    updateContact();
                    break;
                }
                case 3: {
                    deleteContact();
                    break;
                }
                case 4: {
                    displayContacts();
                    break;
                }
                case 5: {
                    getContact();
                    break;
                }
                default:
                    complete = true;
                    break;
            }
        }
        consoleIO.clearScreen();
    }

    public void getContact() {
        consoleIO.display("Please enter an id to search");
        int id = consoleIO.getNumberInput();
        try {
            Contact contact = storage.getContact(id);
            contact.printContactDetails();
        } catch (Exception e) {
            consoleIO.clearScreen();
            consoleIO.display("No such contact: ID = " + id);
        }
    }

    public void newContact() {
        consoleIO.clearScreen();
        Contact contact = new Contact(
                consoleIO.getStringInput(1, Contact.getFieldName(1)),
                consoleIO.getStringInput(2, Contact.getFieldName(2)),
                consoleIO.getStringInput(3, Contact.getFieldName(3)),
                consoleIO.getStringInput(4, Contact.getFieldName(4)),
                consoleIO.getStringInput(5, Contact.getFieldName(5)),
                consoleIO.getStringInput(6, Contact.getFieldName(6)),
                consoleIO
        );
        storage.createContact(contact);
    }

    public void updateContact() {
        consoleIO.clearScreen();
        if (storage.contactsExist()) {
            storage.showContacts();
            consoleIO.display("Please select a contact to update");
            storage.updateContact();
        }
    }

    public void deleteContact() {
        consoleIO.clearScreen();
        if (storage.contactsExist()) {
            storage.showContacts();
            consoleIO.display("Please select a contact");
            int id = this.consoleIO.getNumberInput();
            try {
                storage.deleteContact(id - 1);
            } catch (SQLException e) {
                consoleIO.clearScreen();
                consoleIO.display("No such contact: ID = " + id);
            }
        }
    }

    public void displayContacts() {
        consoleIO.clearScreen();
        storage.showContacts();
    }
}
