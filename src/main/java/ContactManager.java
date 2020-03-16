public class ContactManager {

    private ConsoleIO consoleIO;
    private ContactList contactList;
    private Database database;
    public ContactList storage;

    public ContactManager(ConsoleIO consoleIO, ContactList contactList, Database database) {
       this.consoleIO = consoleIO;
       this.database = database;
       this.contactList = contactList;
       this.storage = selectStorageDestination();
    }

    public ContactList selectStorageDestination() {
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
                    storage.displayContacts();
                    break;
                }
                default:
                    complete = true;
                    break;
            }
        }
        consoleIO.clearScreen();
    }

    public void newContact() {
        consoleIO.clearScreen();
        storage.newContact(
                consoleIO.getStringInput(1, Contact.getFieldName(1)),
                consoleIO.getStringInput(2, Contact.getFieldName(2)),
                consoleIO.getStringInput(3, Contact.getFieldName(3)),
                consoleIO.getStringInput(4, Contact.getFieldName(4)),
                consoleIO.getStringInput(5, Contact.getFieldName(5)) ,
                consoleIO.getStringInput(6, Contact.getFieldName(6))
        );
        consoleIO.clearScreen();
    }

    public void updateContact() {
        if (storage.contactsExist()) {
            storage.displayContacts();
            consoleIO.display("Please select a contact to update");
            storage.updateContact();
        }
    }

    public void deleteContact() {
        consoleIO.clearScreen();
        if (storage.contactsExist()) {
            storage.displayContacts();
            consoleIO.display("Please select a contact");
            int index = this.consoleIO.getNumberInput();
            storage.deleteContact(index - 1);
        }
    }
}
