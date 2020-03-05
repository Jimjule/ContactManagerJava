import java.util.ArrayList;

public class ContactManager {

    private ConsoleInput consoleInput;
    private ArrayList<Contact> contactList;

    public ContactManager(ConsoleInput consoleInput, ArrayList<Contact> contactList) {
       this.consoleInput = consoleInput;
       this.contactList = contactList;
    }

    public void showMenu() {
        boolean complete = false;
        while (!complete) {
            consoleInput.printMenuOptions();
            int userInput = consoleInput.getNumberInput();
            switch (userInput) {
                case 1: {
                    newContact();
                    break;
                }
                case 2: {
                    updateExistingContact();
                    break;
                }
                case 3: {
                    deleteContact();
                    break;
                }
                case 4: {
                    showContact();
                    break;
                }
                default:
                    complete = true;
                    break;
            }
        }
    }

    public void newContact() {
        Contact contact = new Contact(
                consoleInput.confirmInput("first name", false),
                consoleInput.confirmInput("last name", false),
                consoleInput.confirmInput("address", false),
                consoleInput.confirmInput("phone number without spaces", false),
                consoleInput.confirmInput("DOB in dd/mm/yyyy format", false) ,
                consoleInput.confirmInput("email", false
                ));
        contactList.add(contact);
    }

    public void updateExistingContact() {
        if (contactsExist()) {
            displayContacts();
            try {
                updateContactFields(contactList.get(Integer.parseInt(consoleInput.confirmInput("contact choice", true)) - 1));
            } catch (Exception e) {
                consoleInput.printer.println("No such contact");
            }
        }
    }

    public boolean contactsExist() {
        if (contactList.size() == 0) {
            consoleInput.printer.println("There are no contacts yet");
            return false;
        } else {
            return true;
        }
    }

    public void updateContactFields(Contact contact) {
        consoleInput.printer.println("Currently: " + contact.returnFirstName());
        contact.updateFirstName(consoleInput.confirmInput("first name", true));
        consoleInput.printer.println("Currently: " + contact.returnLastName());
        contact.updateLastName(consoleInput.confirmInput("last name", true));
        consoleInput.printer.println("Currently: " + contact.returnAddress());
        contact.updateAddress(consoleInput.confirmInput("address", true));
        consoleInput.printer.println("Currently: " + contact.returnPhoneNumber());
        contact.updatePhoneNumber(consoleInput.confirmInput("phone number", true));
        consoleInput.printer.println("Currently: " + contact.returnDOB());
        contact.updateDOB(consoleInput.confirmInput("DOB in dd/mm/yyyy format", true));
        consoleInput.printer.println("Currently: " + contact.returnEmail());
        contact.updateEmail(consoleInput.confirmInput("email", true));
    }

    public void showContact() {
        if (contactsExist()) {
            displayContacts();
        }
    }

    private void deleteContact() {
        if (contactsExist()) {
            displayContacts();
            int index = this.consoleInput.getNumberInput();
            try {
                this.contactList.remove(index - 1);
                System.out.println("Contact deleted");
            } catch (Exception e) {
                System.out.println("No such contact");
            }
        }
    }

    public void printContactDetails(Contact contact) {
        consoleInput.printer.println("First name is: " + contact.returnFirstName());
        consoleInput.printer.println("Last name is: " + contact.returnLastName());
        consoleInput.printer.println("Address is: " + contact.returnAddress());
        consoleInput.printer.println("Phone number is: " + contact.returnPhoneNumber());
        consoleInput.printer.println("Date of birth is: " + contact.returnDOB());
        consoleInput.printer.println("Email is: " + contact.returnEmail());
    }

    public void displayContacts() {
        System.out.println("Please select a contact");
        for (int i = 0; i < contactList.size(); i++) {
            consoleInput.printer.println(i + 1);
            printContactDetails(contactList.get(i));
        }
    }
}
