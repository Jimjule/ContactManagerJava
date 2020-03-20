public final class Constants {

    private Constants() {}

    public static final String menuOptions = "Welcome to Contact Manager\n" +
            "Please select an option:\n" +
            "1. New Contact\n" +
            "2. Update Contact\n" +
            "3. Delete Contact\n" +
            "4. View Contacts\n" +
            "5. Exit";
    public static final String updateFields = "Select a field to update:\n" +
            "1. First name\n" +
            "2. Last name\n" +
            "3. Address\n" +
            "4. Phone number \n" +
            "5. DOB\n" +
            "6. Email";

    public static final String prodContactManagerDB = "jdbc:postgresql://localhost:5432/contactmanagerdb";

    public static final String testContactManagerDB = "jdbc:postgresql://localhost:5432/testContactManager";

    public static final String DBName = "contactmanagerdb";

}
