import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactListTest {

    private ContactList contactList;
    private ConsoleIOSpy consoleIO;
    private Contact contact;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        String testString = "Testing";
        InputStream fixedInput = new ByteArrayInputStream(testString.getBytes());
        consoleIO = new ConsoleIOSpy(fixedInput, outputStream);
        ArrayList<Contact> arrayList = new ArrayList<Contact>();
        contactList = new ContactList(arrayList, consoleIO);
        contact = new Contact("Namey", "Namerson", "A Palace", "130077", "01/01/1999", "email@email.com");
    }

    @Test
    public void savesNewContact() {
        contactList.createContact(contact);
        assertTrue(contactList.contactsExist());
    }

    @Test
    public void getContact() {
        contactList.createContact(contact);
        Contact retrievedContact = contactList.getContact(1).get();
        assertTrue(contact.equals(retrievedContact));
    }

    @Test
    public void deletesNewContact() {
        contactList.createContact(contact);
        contactList.deleteContact(1);
        assertEquals(false, contactList.contactsExist());
    }

    @Test
    public void updateContact() {
        contactList.createContact(contact);
        contactList.updateContact(contact, 1, "Updatedfirstname");
        assertEquals("Updatedfirstname", contact.getFirstName());
    }
}