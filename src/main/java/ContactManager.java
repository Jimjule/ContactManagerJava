import java.util.ArrayList;

public class ContactManager {

    private ConsoleIO consoleIO;
    private ArrayList<Contact> contactList;

    public ContactManager(ConsoleIO consoleIO, ArrayList<Contact> contactList) {
       this.consoleIO = consoleIO;
       this.contactList = contactList;
    }

    public void showMenu() {
        boolean complete = false;
        while (!complete) {
            consoleIO.printMenuOptions();
            int userInput = consoleIO.getNumberInput();
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

    public String getFieldName(int fieldNumber) {
        switch (fieldNumber) {
            case 1:
                return "First name";
            case 2:
                return "Last name";
            case 3:
                return "Address";
            case 4:
                return "Phone number without spaces";
            case 5:
                return "DOB in dd/mm/yyyy format";
            default:
                return "Email";
        }
    }

    public void updateField(Contact contact, int fieldNumber, String input) {
        switch (fieldNumber) {
            case 1:
                contact.updateFirstName(input);
                break;
            case 2:
                contact.updateLastName(input);
                break;
            case 3:
                contact.updateAddress(input);
                break;
            case 4:
                contact.updatePhoneNumber(input);
                break;
            case 5:
                contact.updateDOB(input);
                break;
            case 6:
                contact.updateEmail(input);
                break;
            default:
                break;
        }
    }

    public void newContact() {
        Contact contact = new Contact(
                consoleIO.confirmInput("first name", false),
                consoleIO.confirmInput("last name", false),
                consoleIO.confirmInput("address", false),
                consoleIO.confirmInput("phone number without spaces", false),
                consoleIO.confirmInput("DOB in dd/mm/yyyy format", false) ,
                consoleIO.confirmInput("email", false
                ));
        contactList.add(contact);
    }

    public void updateExistingContact() {
        if (contactsExist()) {
            displayContacts();
            try {
                updateContactFields(contactList.get(Integer.parseInt(consoleIO.confirmInput("contact choice", true)) - 1));
            } catch (Exception e) {
                consoleIO.display("No such contact");;
            }
        }
    }

    public boolean contactsExist() {
        if (contactList.size() == 0) {
            consoleIO.display("There are no contacts yet");
            return false;
        } else {
            return true;
        }
    }

    public void updateContactFields(Contact contact) {
        consoleIO.display("Please select a field to update: 1. First name 2. Last name 3. Address 4. Phone number 5. DOB, 6. Email");
        int field = Integer.parseInt(consoleIO.confirmInput("field", false));
        consoleIO.display(getFieldName(field) + " is currently: " + contact.returnField(field));
        updateField(contact, field, consoleIO.confirmInput(getFieldName(field), true));
    }

    public void showContact() {
        if (contactsExist()) {
            displayContacts();
        }
    }

    private void deleteContact() {
        if (contactsExist()) {
            displayContacts();
            int index = this.consoleIO.getNumberInput();
            try {
                this.contactList.remove(index - 1);
                consoleIO.display("Contact deleted");
            } catch (Exception e) {
                consoleIO.display("No such contact");
            }
        }
    }

    public void printContactDetails(Contact contact) {
        consoleIO.printer.println("First name is: " + contact.returnFirstName());
        consoleIO.printer.println("Last name is: " + contact.returnLastName());
        consoleIO.printer.println("Address is: " + contact.returnAddress());
        consoleIO.printer.println("Phone number is: " + contact.returnPhoneNumber());
        consoleIO.printer.println("Date of birth is: " + contact.returnDOB());
        consoleIO.printer.println("Email is: " + contact.returnEmail());
    }

    public void displayContacts() {
        consoleIO.printer.println("Please select a contact");
        for (int i = 0; i < contactList.size(); i++) {
            consoleIO.printer.println(i + 1);
            printContactDetails(contactList.get(i));
        }
    }
}
