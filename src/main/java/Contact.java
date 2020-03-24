public class Contact {

    private ConsoleIO consoleIO;

    private String FirstName;
    private String LastName;
    private String Address;
    private String PhoneNumber;
    private String DOB;
    private String Email;

    public static final String FirstNameField = "First Name";
    public static final String LastNameField = "Last Name";
    public static final String AddressField = "Address";
    public static final String PhoneNumberField = "Phone Number (no spaces)";
    public static final String DOBField = "Date of Birth (dd/MM/yyyy)";
    public static final String EmailField = "Unique Email Address";

    public Contact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email, ConsoleIO consoleIO) {
        this.consoleIO = consoleIO;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
        this.DOB = dOB;
        this.Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getDOB() {
        return DOB;
    }

    public String getEmail() {
        return Email;
    }

    public static String getFieldName(int field) {
        switch (field) {
            case 1: return FirstNameField;
            case 2: return LastNameField;
            case 3: return AddressField;
            case 4: return PhoneNumberField;
            case 5: return DOBField;
            default: return EmailField;
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

    public void printContactDetails() {
        consoleIO.display("-----\n" +
                Contact.FirstNameField + ": " + getFirstName() + "\n" +
                Contact.LastNameField + ": " + getLastName() + "\n" +
                Contact.AddressField + ": " + getAddress() + "\n" +
                Contact.PhoneNumberField + ": " + getPhoneNumber() + "\n" +
                Contact.DOBField + ": " + getDOB() + "\n" +
                Contact.EmailField + ": " + getEmail() + "\n" +
                "-----\n"
        );
    }

    public static boolean validateInput(int field, String input) {
        return ValidateInput.validateInput(field, input);
    }

    private boolean notBlank(String value) {
        return !value.matches(String.valueOf(ValidateInput.blankString));
    }
}
