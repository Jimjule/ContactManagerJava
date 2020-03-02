import java.util.Hashtable;

public class Contact {

    private String FirstName;
    private String LastName;
    private String Address;
    private String PhoneNumber;
    private String DOB;
    private String Email;

   public Contact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email){
       this.FirstName = firstName;
       this.LastName = lastName;
       this.Address = address;
       this.PhoneNumber = phoneNumber;
       this.DOB = dOB;
       this.Email = email;
   }

    String returnFirstName() { return FirstName; }

    void updateFirstName(String firstName) { FirstName = firstName; }

    String returnLastName() { return LastName; }

    void updateLastName(String lastName) {
        LastName = lastName;
    }

    String returnAddress() {
        return Address;
    }

    void updateAddress(String address) {
        Address = address;
    }

    String returnPhoneNumber() {
        return PhoneNumber;
    }

    void updatePhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    String returnDOB() {
        return DOB;
    }

    void updateDOB(String dOB) {
        DOB = dOB;
    }

    String returnEmail() {
        return Email;
    }

    void updateEmail(String email) {
        Email = email;
    }
}
