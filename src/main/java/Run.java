import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        ConsoleIO consoleIO = new ConsoleIO(System.in, System.out);
        ArrayList<Contact> contactList = new ArrayList<>();
        ContactManager contactManager = new ContactManager(consoleIO, contactList);

        consoleIO.clearScreen();
        contactManager.showMenu();
    }
}
