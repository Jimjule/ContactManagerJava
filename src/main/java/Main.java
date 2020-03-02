import java.util.ArrayList;

public class Main {

    Input input = new Input();
    ArrayList<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.showMenu();
    }

    public void showMenu() {
        boolean complete = false;
        while (!complete) {
            printMenuOptions();
            int userInput = input.menuChoice();
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
        contactList.add(new Contact(input.confirmInput("first name"), input.confirmInput("last name"), input.confirmInput("address"), input.confirmInput("phone number"), input.confirmInput("DOB in dd/mm/yyyy format"), input.confirmInput("email")));
    }

    void updateExistingContact() {
        if (checkForContacts()) {
            displayContacts();
            try {
                updateContactFields(contactList.get(Integer.parseInt(input.confirmInput("contact choice")) - 1));
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
        contact.updateFirstName(input.confirmInput("first name"));
        System.out.println("Currently: " + contact.returnLastName());
        contact.updateLastName(input.confirmInput("last name"));
        System.out.println("Currently: " + contact.returnAddress());
        contact.updateAddress(input.confirmInput("address"));
        System.out.println("Currently: " + contact.returnPhoneNumber());
        contact.updatePhoneNumber(input.confirmInput("phone number"));
        System.out.println("Currently: " + contact.returnDOB());
        contact.updateDOB(input.confirmInput("DOB in dd/mm/yyyy format"));
        System.out.println("Currently: " + contact.returnEmail());
        contact.updateEmail(input.confirmInput("email"));
    }

    void showContact() {
        if (checkForContacts()) {
            displayContacts();
            Contact contact = null;
            try {
                contact = contactList.get(input.contactChoice() - 1);
            } catch (Exception e) {
                System.out.println("No such contact");
                showMenu();
            }
            printContactDetails(contact);
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
        }
    }
}
