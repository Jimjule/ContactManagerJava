import java.util.ArrayList;

public class ContactManager {

    private ConsoleIO consoleIO;
    private ArrayList<Contact> contactList;

    public ContactManager(ConsoleIO consoleIO, ArrayList<Contact> contactList) {
       this.consoleIO = consoleIO;
       this.contactList = contactList;
    }

    public void printMenuOptions() {
        consoleIO.display("Welcome to Contact Manager\nPlease select an option:\n1. New Contact\n2. Update Contact\n" +
                "3. Delete Contact\n4. View Contacts\n5. Exit");
    }

    public void showMenu() {
        boolean complete = false;
        while (!complete) {
            printMenuOptions();
            int userInput = consoleIO.getMenuInput();
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
                consoleIO.getStringInput(ContactFields.FirstName, false),
                consoleIO.getStringInput(ContactFields.LastName,false),
                consoleIO.getStringInput(ContactFields.Address,false),
                consoleIO.getStringInput(ContactFields.PhoneNumber, false),
                consoleIO.getStringInput(ContactFields.DOB, false) ,
                consoleIO.getStringInput(ContactFields.Email,false
                ));
        contactList.add(contact);
        consoleIO.clearScreen();
    }

    public String getFieldName(int fieldNumber) {
        switch (fieldNumber) {
            case 1: return ContactFields.FirstName;
            case 2: return ContactFields.LastName;
            case 3: return ContactFields.Address;
            case 4: return ContactFields.PhoneNumber;
            case 5: return ContactFields.DOB;
            default: return ContactFields.Email;
        }
    }

    public void updateField(Contact contact, int fieldNumber, String input) {
        switch (fieldNumber) {
            case 1: updateFirstName(input, contact);
                break;
            case 2: updateLastName(input, contact);
                break;
            case 3: updateAddress(input, contact);
                break;
            case 4: updatePhoneNumber(input, contact);
                break;
            case 5: updateDOB(input, contact);
                break;
            case 6: updateEmail(input, contact);
                break;
            default: break;
        }
    }

    public void updateFirstName(String firstName, Contact contact) {
        if (!firstName.matches(String.valueOf(ValidateInput.blankString))) {
            contact.FirstName = firstName;
        }
    }

    public void updateLastName(String lastName, Contact contact) {
        if (!lastName.matches(String.valueOf(ValidateInput.blankString))) {
            contact.LastName = lastName;
        }
    }

    public void updateAddress(String address, Contact contact) {
        if (!address.matches(String.valueOf(ValidateInput.blankString))) {
            contact.Address = address;
        }
    }

    public void updatePhoneNumber(String phoneNumber, Contact contact) {
        if (!phoneNumber.matches(String.valueOf(ValidateInput.blankString))) {
            contact.PhoneNumber = phoneNumber;
        }
    }

    public void updateDOB(String dOB, Contact contact) {
        if (!dOB.matches(String.valueOf(ValidateInput.blankString))) {
            contact.DOB = dOB;
        }
    }

    public void updateEmail(String email, Contact contact) {
        if (!email.matches(String.valueOf(ValidateInput.blankString))) {
            contact.Email = email;
        }
    }

    public void updateContact() {
        if (contactsExist()) {
            displayContacts();
            try {
                updateContactFields(contactList.get(
                        Integer.parseInt(consoleIO.getStringInput("contact choice", true)) - 1));
            } catch (Exception e) {
                consoleIO.display("No such contact");;
            }
        }
    }

    public void updateContactFields(Contact contact) {
        consoleIO.display("Select a field to update: 1. First name 2. Last name " +
                "3. Address 4. Phone number 5. DOB, 6. Email");
        int field = Integer.parseInt(consoleIO.getStringInput("Field", false));
        consoleIO.display(getFieldName(field) + " is currently: " + returnFieldValue(contact, field));
        updateField(contact, field, consoleIO.getStringInput(getFieldName(field), true));
        consoleIO.clearScreen();
    }

    public String returnFieldValue(Contact contact, int field) {
        switch (field) {
            case 1: return contact.FirstName;
            case 2: return contact.LastName;
            case 3: return contact.Address;
            case 4: return contact.PhoneNumber;
            case 5: return contact.DOB;
            default: return contact.Email;
        }
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
        int index = this.consoleIO.getMenuInput();
        consoleIO.clearScreen();
        try {
            this.contactList.remove(index - 1);
            consoleIO.display("Contact deleted");
        } catch (Exception e) {
            consoleIO.display("No such contact");
        }
    }

    public void printContactDetails(Contact contact) {
        consoleIO.display(ContactFields.FirstName + ": " + contact.FirstName + "\n" +
                ContactFields.LastName + ": " + contact.LastName + "\n" +
                ContactFields.Address + ": " + contact.Address + "\n" +
                ContactFields.PhoneNumber + ": " + contact.PhoneNumber + "\n" +
                ContactFields.DOB + ": " + contact.DOB + "\n" +
                ContactFields.Email + ": " + contact.Email
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
