public class Contact {

    private String FirstName;
    private String LastName;
    private String Address;
    private String PhoneNumber;
    private String DOB;
    private String Email;

    public Contact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
       this.FirstName = firstName;
       this.LastName = lastName;
       this.Address = address;
       this.PhoneNumber = phoneNumber;
       this.DOB = dOB;
       this.Email = email;
    }

    public String getFieldValue(int field) {
        switch (field) {
            case 1: return FirstName;
            case 2: return LastName;
            case 3: return Address;
            case 4: return PhoneNumber;
            case 5: return DOB;
            default: return Email;
        }
    }

    public static String getFieldName(int field) {
        switch (field) {
            case 1: return "First Name";
            case 2: return "Last Name";
            case 3: return "Address";
            case 4: return "Phone Number (no spaces)";
            case 5: return "Date of Birth (dd/MM/yyyy)";
            default: return "Email";
        }
    }

    public void updateField(String value, int field) {
        if (notBlank(value)) {
        if (ValidateInput.validateInput(field, value))
            switch (field) {
                case 1: FirstName = value; break;
                case 2: LastName = value; break;
                case 3: Address = value; break;
                case 4: PhoneNumber = value; break;
                case 5: DOB = value; break;
                default: Email = value; break;
            }
        }
    }

    private boolean notBlank(String value) {
        return !value.matches(String.valueOf(ValidateInput.blankString));
    }
}
