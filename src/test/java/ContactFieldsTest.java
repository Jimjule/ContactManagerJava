import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContactFieldsTest {

    @Test
    public void firstNameValue() {
        assertEquals(ContactFields.FirstName, "First name");
    }

    @Test
    public void lastNameValue() {
        assertEquals(ContactFields.LastName, "Last name");
    }

    @Test
    public void addressValue() {
        assertEquals(ContactFields.Address, "Address");
    }

    @Test
    public void phoneNumberValue() {
        assertEquals(ContactFields.PhoneNumber, "Phone number without spaces");
    }

    @Test
    public void dOBValue() {
        assertEquals(ContactFields.DOB, "DOB in dd/mm/yyyy format");
    }

    @Test
    public void emailValue() {
        assertEquals(ContactFields.Email, "Email");
    }
}
