import java.util.ArrayList;

public class ContactManager {

    ConsoleInput consoleInput = new ConsoleInput();
    ArrayList<Contact> contactList = new ArrayList<>();

    public void showMenu() {
        boolean complete = false;
        while (!complete) {
            printMenuOptions();
            int userInput = consoleInput.menuChoice();
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
                    showContact();
                    break;
                }
                default:
                    complete = true;
                    break;
            }
        }
    }

    void printMenuOptions() {
        System.out.println("Welcome to Contact Manager");
        System.out.println("Please select an option:");
        System.out.println("1. Create new Contact");
        System.out.println("2. Update Contact");
        System.out.println("3. View Contact");
        System.out.println("4. Exit");
    }

    void newContact() {
        contactList.add(new Contact(consoleInput.confirmInput("first name"), consoleInput.confirmInput("last name"), consoleInput.confirmInput("address"), consoleInput.confirmInput("phone number"), consoleInput.confirmInput("DOB in dd/mm/yyyy format"), consoleInput.confirmInput("email")));
    }

    void updateExistingContact() {
        if (checkForContacts()) {
            displayContacts();
            try {
                updateContactFields(contactList.get(Integer.parseInt(consoleInput.confirmInput("contact choice")) - 1));
            } catch (Exception e) {
                System.out.println("No such contact");
            }
        }
    }

    boolean checkForContacts() {
        if (contactList.size() == 0) {
            System.out.println("There are no contacts yet");
            return false;
        } else {
            return true;
        }
    }

    void updateContactFields(Contact contact) {
        System.out.println("Currently: " + contact.returnFirstName());
        contact.updateFirstName(consoleInput.confirmInput("first name"));
        System.out.println("Currently: " + contact.returnLastName());
        contact.updateLastName(consoleInput.confirmInput("last name"));
        System.out.println("Currently: " + contact.returnAddress());
        contact.updateAddress(consoleInput.confirmInput("address"));
        System.out.println("Currently: " + contact.returnPhoneNumber());
        contact.updatePhoneNumber(consoleInput.confirmInput("phone number"));
        System.out.println("Currently: " + contact.returnDOB());
        contact.updateDOB(consoleInput.confirmInput("DOB in dd/mm/yyyy format"));
        System.out.println("Currently: " + contact.returnEmail());
        contact.updateEmail(consoleInput.confirmInput("email"));
    }

    void showContact() {
        if (checkForContacts()) {
            displayContacts();
            Contact contact = null;
            try {
                contact = contactList.get(consoleInput.contactChoice() - 1);
                printContactDetails(contact);
            } catch (Exception e) {
                System.out.println("No such contact, please try again by selecting one of the contacts shown");
            }
        }
    }

    void printContactDetails(Contact contact) {
        System.out.println("First name is: " + contact.returnFirstName());
        System.out.println("Last name is: " + contact.returnLastName());
        System.out.println("Address is: " + contact.returnAddress());
        System.out.println("Phone number is: " + contact.returnPhoneNumber());
        System.out.println("Date of birth is: " + contact.returnDOB());
        System.out.println("Email is: " + contact.returnEmail());
    }

    void displayContacts() {
        System.out.println("Please select a contact");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(i + 1);
            printContactDetails(contactList.get(i));
        }
    }
}
