import java.util.regex.Pattern;

public class Contact {

    public String FirstName;
    public String LastName;
    public String Address;
    public String PhoneNumber;
    public String DOB;
    public String Email;

    public Contact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
       this.FirstName = firstName;
       this.LastName = lastName;
       this.Address = address;
       this.PhoneNumber = phoneNumber;
       this.DOB = dOB;
       this.Email = email;
    }
}
