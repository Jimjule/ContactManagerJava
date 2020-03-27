import java.sql.SQLException;
import java.util.List;

public interface Storage {

    void createContact(Contact contact);

    void deleteContact(int index) throws SQLException;

    void updateContact(Contact contact, int field, String input) throws SQLException;

    Contact getContact(int index) throws Exception;

    List<Contact> getContacts();

    void showContacts();

    boolean contactsExist();
}
