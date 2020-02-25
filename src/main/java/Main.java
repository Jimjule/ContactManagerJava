public class Main {
    static Contact contact = new Contact();
    static Input input = new Input();

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        int menuChoice;
        System.out.println("Welcome to Contact Manager");
        System.out.println("Please select an option:");
        System.out.println("1. Update Contact");
        System.out.println("2. Exit");
        menuChoice = input.menuChoice();
        switch (menuChoice) {
            case 1: updateContact();
            default: break;
        }
    }

    static void updateContact() {
        updateContactFields();
        showContact();
    }

    static void updateContactFields() {
        contact.updateFirstName(input.confirmInput("first name"));
        contact.updateLastName(input.confirmInput("last name"));
        contact.updateAddress(input.confirmInput("address"));
        contact.updatePhoneNumber(input.confirmInput("phone number"));
        contact.updateDOB(input.confirmInput("DOB in dd/mm/yyyy format"));
        contact.updateEmail(input.confirmInput("email"));
    }

    static void showContact() {
        System.out.println("First name is: " + contact.FirstName);
        System.out.println("Last name is: " + contact.LastName);
        System.out.println("Address is: " + contact.Address);
        System.out.println("Phone number is: " + contact.PhoneNumber);
        System.out.println("Date of birth is: " + contact.DOB);
        System.out.println("Email is: " + contact.Email);
        showMenu();
    }
}
