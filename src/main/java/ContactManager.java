import java.util.ArrayList;

public class ContactManager {

    private ConsoleIO consoleIO;
    private ArrayList<Contact> contactList;

    public ContactManager(ConsoleIO consoleIO, ArrayList<Contact> contactList) {
       this.consoleIO = consoleIO;
       this.contactList = contactList;
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
                default:
                    complete = true;
                    break;
            }
        }
        consoleIO.clearScreen();
    }

    public void newContact() {
        consoleIO.clearScreen();
        Contact contact = new Contact(
                consoleIO.getStringInput(1, Contact.getFieldName(1)),
                consoleIO.getStringInput(2, Contact.getFieldName(2)),
                consoleIO.getStringInput(3, Contact.getFieldName(3)),
                consoleIO.getStringInput(4, Contact.getFieldName(4)),
                consoleIO.getStringInput(5, Contact.getFieldName(5)) ,
                consoleIO.getStringInput(6, Contact.getFieldName(6)
                ));
        contactList.add(contact);
        consoleIO.clearScreen();
    }

    public void updateContact() {
        if (contactsExist()) {
            displayContacts();
            consoleIO.display("Please select a contact to update");
            try {
                int contactNumber = consoleIO.getNumberInput();
                Contact contact = contactList.get((contactNumber) - 1);
                updateContactFields(contact);
            } catch (Exception e) {
                consoleIO.clearScreen();
                consoleIO.display("No such contact");
            }
        }
    }

    public void updateContactFields(Contact contact) {
        consoleIO.clearScreen();
        consoleIO.display(Constants.updateFields);

        int field = consoleIO.getNumberInput();
        consoleIO.display(Contact.getFieldName(field) + " is currently: " + contact.getFieldValue(field));
        String input = consoleIO.getStringInput(field, Contact.getFieldName(field));
        contact.updateField(input, field);

        consoleIO.clearScreen();
    }

    public boolean contactsExist() {
        consoleIO.clearScreen();
        if (contactList.size() == 0) {
            consoleIO.display("There are no contacts yet");
            return false;
        } else {
            return true;
        }
    }

    private void deleteContact() {
        if (contactsExist()) {
            displayContacts();
            consoleIO.display("Please select a contact");
            deleteSelectedContact();
        }
    }

    public void deleteSelectedContact() {
        int index = this.consoleIO.getNumberInput();
        consoleIO.clearScreen();
        try {
            this.contactList.remove(index - 1);
            consoleIO.display("Contact deleted");
        } catch (Exception e) {
            consoleIO.display("No such contact");
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

    public void displayContacts() {
        consoleIO.clearScreen();
        if (contactsExist()) {
            displayContactsLoop();
        }
    }

    public void displayContactsLoop() {
        for (int i = 0; i < contactList.size(); i++) {
            consoleIO.display(String.valueOf(i + 1));
            printContactDetails(contactList.get(i));
        }
    }
}
