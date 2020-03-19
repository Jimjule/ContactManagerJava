import java.util.ArrayList;

public class Run {
    public static void main(String[] args) {
        ConsoleIO consoleIO = new ConsoleIO(System.in, System.out);
        ArrayList<Contact> arrayList = new ArrayList<>();
        ContactList contactList = new ContactList(arrayList, consoleIO);
        Database database = new Database(consoleIO, Constants.prodContactManagerDB, Constants.prodDBName);
        ContactManager contactManager = new ContactManager(consoleIO, contactList, database);

        consoleIO.clearScreen();
        contactManager.showMenu();
    }
}
