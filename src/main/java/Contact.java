public class Contact {

    static String FirstName;
    static String LastName;
    static String Address;
    static String PhoneNumber;
    static String DOB;
    static String Email;

    public void main(String[] args) {
    }

    String returnFirstName() {
        return FirstName;
    }

    void updateFirstName(String firstName) {
        FirstName = firstName;
    }
    String returnLastName() {
        return LastName;
    }

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