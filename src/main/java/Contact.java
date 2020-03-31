public class Contact {

    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String dOB;
    private String email;

    public static final int NUMBER_OF_FIELDS = 6;
    public static final String firstNameField = "First Name";
    public static final String lastNameField = "Last Name";
    public static final String addressField = "Address";
    public static final String phoneNumberField = "Phone Number (no spaces)";
    public static final String dOBField = "Date of Birth (dd/MM/yyyy)";
    public static final String emailField = "Unique Email Address";

    public Contact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dOB = dOB;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getdOB() {
        return dOB;
    }

    public String getEmail() {
        return email;
    }

    public static String getFieldName(int field) {
        switch (field) {
            case 1: return firstNameField;
            case 2: return lastNameField;
            case 3: return addressField;
            case 4: return phoneNumberField;
            case 5: return dOBField;
            case 6: return emailField;
        }
        return "invalid field";
    }

    public void updateField(String value, int field) {
        if (notBlank(value)) {
        if (ValidateInput.validateInput(field, value))
            switch (field) {
                case 1: firstName = value; break;
                case 2: lastName = value; break;
                case 3: address = value; break;
                case 4: phoneNumber = value; break;
                case 5: dOB = value; break;
                case 6: email = value; break;
                default: break;
            }
        }
    }

    public String printContactDetails() {
        return "-----\n" +
                Contact.firstNameField + ": " + getFirstName() + "\n" +
                Contact.lastNameField + ": " + getLastName() + "\n" +
                Contact.addressField + ": " + getAddress() + "\n" +
                Contact.phoneNumberField + ": " + getPhoneNumber() + "\n" +
                Contact.dOBField + ": " + getdOB() + "\n" +
                Contact.emailField + ": " + getEmail() + "\n" +
                "-----\n";
    }

    public static boolean validateInput(int field, String input) {
        return ValidateInput.validateInput(field, input);
    }

    private boolean notBlank(String value) {
        return !value.matches(String.valueOf(ValidateInput.blankString));
    }
}
