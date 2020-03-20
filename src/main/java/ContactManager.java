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
        consoleIO.display("Would you like to save to the database? (y/N)");
        boolean useDatabase = consoleIO.getBoolean();
        consoleIO.clearScreen();
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
        int id = consoleIO.getNumberInput();
        try {
            storage.getContact(id);
        } catch (Exception e) {
            consoleIO.display("No such contact: ID = " + id);
            e.printStackTrace();
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
            int index = this.consoleIO.getNumberInput();
            storage.deleteContact(index - 1);
        }
    }

    public void displayContacts() {
        consoleIO.clearScreen();
        storage.showContacts();
    }
}
