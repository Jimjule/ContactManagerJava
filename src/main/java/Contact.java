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
                Contact.getFieldName(1) + ": " + getFieldValue(1) + "\n" +
                Contact.getFieldName(2) + ": " + getFieldValue(2) + "\n" +
                Contact.getFieldName(3) + ": " + getFieldValue(3) + "\n" +
                Contact.getFieldName(4) + ": " + getFieldValue(4) + "\n" +
                Contact.getFieldName(5) + ": " + getFieldValue(5) + "\n" +
                Contact.getFieldName(6) + ": " + getFieldValue(6) + "\n" +
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
