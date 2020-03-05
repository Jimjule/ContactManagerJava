import java.util.regex.Pattern;

public class Contact {

    private String FirstName;
    private String LastName;
    private String Address;
    private String PhoneNumber;
    private String DOB;
    private String Email;
    public Pattern blankPattern = Pattern.compile("^$");

    public Contact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
       this.FirstName = firstName;
       this.LastName = lastName;
       this.Address = address;
       this.PhoneNumber = phoneNumber;
       this.DOB = dOB;
       this.Email = email;
    }

    public Object returnField(int field) {
        switch (field) {
            case 1: return FirstName;
            case 2: return LastName;
            case 3: return Address;
            case 4: return PhoneNumber;
            case 5: return DOB;
            default: return Email;
        }
    }

    public String returnFirstName() { return FirstName; }

    public void updateFirstName(String firstName) {
       if (!firstName.matches(String.valueOf(blankPattern))) {
           FirstName = firstName;
       }
   }

    public String returnLastName() { return LastName; }

    public void updateLastName(String lastName) {
        if (!lastName.matches(String.valueOf(blankPattern))) {
            LastName = lastName;
        }
    }

    public String returnAddress() {
        return Address;
    }

    public void updateAddress(String address) {
        if (!address.matches(String.valueOf(blankPattern))) {
            Address = address;
        }
    }

    public String returnPhoneNumber() {
        return PhoneNumber;
    }

    public void updatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches(String.valueOf(blankPattern))) {
            PhoneNumber = phoneNumber;
        }
    }

    public String returnDOB() {
        return DOB;
    }

    public void updateDOB(String dOB) {
        if (!dOB.matches(String.valueOf(blankPattern))) {
            DOB = dOB;
        }
    }

    public String returnEmail() {
        return Email;
    }

    public void updateEmail(String email) {
        if (!email.matches(String.valueOf(blankPattern))) {
            Email = email;
        }
    }
}
