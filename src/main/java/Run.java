import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        ConsoleInput consoleInput = new ConsoleInput(System.in, System.out);
        ArrayList<Contact> contactList = new ArrayList<>();
        ContactManager contactManager = new ContactManager(consoleInput, contactList);
        contactManager.showMenu();
    }
}
