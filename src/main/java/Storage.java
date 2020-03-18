public interface Storage {

    void createContact(Contact contact);

    void deleteContact(int index);

    void updateContact();

    void showContact(int index);

    void showContacts();

    boolean contactsExist();
}
