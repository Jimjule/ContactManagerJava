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

    public String returnFirstName() { return FirstName; }

    public void updateFirstName(String firstName) { FirstName = firstName; }

    public String returnLastName() { return LastName; }

    public void updateLastName(String lastName) {
        LastName = lastName;
    }

    public String returnAddress() {
        return Address;
    }

    public void updateAddress(String address) {
        Address = address;
    }

    public String returnPhoneNumber() {
        return PhoneNumber;
    }

    public void updatePhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String returnDOB() {
        return DOB;
    }

    public void updateDOB(String dOB) {
        DOB = dOB;
    }

    public String returnEmail() {
        return Email;
    }

    public void updateEmail(String email) {
        Email = email;
    }
}
