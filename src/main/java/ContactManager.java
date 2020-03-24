public class ContactManager {

    private ConsoleIO consoleIO;
    private Storage contactList;
    private Storage database;
    public Storage storage;

    public ContactManager(ConsoleIO consoleIO, Storage contactList, Storage database) {
        this.consoleIO = consoleIO;
        this.database = database;
        this.contactList = contactList;
        this.storage = selectStorageDestination();
    }

    public Storage selectStorageDestination() {
        consoleIO.clearScreen();
        consoleIO.display("Welcome to Contact Manager");
        consoleIO.display("Would you like to save to the database? (y/N)");
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

    private void printMenuOptions() {
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
                    showContacts();
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
        consoleIO.clearScreen();
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
                getInputLoop(1, Contact.getFieldName(1)),
                getInputLoop(2, Contact.getFieldName(2)),
                getInputLoop(3, Contact.getFieldName(3)),
                getInputLoop(4, Contact.getFieldName(4)),
                getInputLoop(5, Contact.getFieldName(5)),
                getInputLoop(6, Contact.getFieldName(6)),
                consoleIO
        );
        storage.createContact(contact);
        consoleIO.clearScreen();
        consoleIO.display("New contact created");
    }

    public String getInputLoop(int field, String fieldName) {
        Boolean validInput = false;
        String userInput = null;
        while (!validInput) {
            userInput = consoleIO.getInput(fieldName);
            validInput = Contact.validateInput(field, userInput);
        }
        return userInput;
    }

    public void updateContact() {
        consoleIO.clearScreen();
        if (storage.contactsExist()) {
            storage.showContacts();
            consoleIO.display("Please select a contact to update");
            int contactNumber = consoleIO.getNumberInput();

            try {
                Contact contact = storage.getContact(contactNumber);

                consoleIO.display(Constants.updateFields);
                int field = consoleIO.getNumberInput();
                String input = getInputLoop(field, Contact.getFieldName(field));
                storage.updateContact(contact, field, input);
            } catch (Exception e) {
                consoleIO.display("No such contact");
            }
        }
        consoleIO.clearScreen();
    }

    public void deleteContact() {
        consoleIO.clearScreen();
        if (storage.contactsExist()) {
            storage.showContacts();
            consoleIO.display("Please select a contact to delete");
            int contactNumber = consoleIO.getNumberInput();

            try {
                storage.deleteContact(contactNumber);
            } catch (Exception e) {
                consoleIO.clearScreen();
                consoleIO.display("No such contact: ID = " + contactNumber);
            }
        }
    }

    public void showContacts() {
        consoleIO.clearScreen();
        storage.showContacts();
    }
}
