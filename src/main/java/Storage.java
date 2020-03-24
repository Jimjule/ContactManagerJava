import java.sql.SQLException;

public interface Storage {

    void createContact(Contact contact);

    void deleteContact(int index) throws SQLException;

    void updateContact();

    Contact getContact(int index) throws Exception;

    void showContacts();

    boolean contactsExist();
}
