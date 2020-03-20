public interface Storage {

    void createContact(Contact contact);

    void deleteContact(int index);

    void updateContact();

    Contact getContact(int index) throws Exception;

    void showContacts();

    boolean contactsExist();
}
