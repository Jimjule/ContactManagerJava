public final class Constants {

    public static final String MENU_OPTIONS = "Please select an option:\n" +
            "1. New Contact\n" +
            "2. Update Contact\n" +
            "3. Delete Contact\n" +
            "4. View Contacts\n" +
            "5. View Specific Contact\n" +
            "6. Exit";

    public static final String UPDATE_FIELDS = "Select a field to update:\n" +
            "1. First name\n" +
            "2. Last name\n" +
            "3. Address\n" +
            "4. Phone number \n" +
            "5. DOB\n" +
            "6. Email";

    public static final String LOCAL_CONNECTION = "jdbc:postgresql://localhost:5432/";

    public static final String PROD_DATABASE = "prod_contact_manager";

    public static final String TEST_DATABASE = "test_contact_manager";

    public static final String CREATE_DB = "CREATE DATABASE " + Constants.PROD_DATABASE;

    public static final String CREATE_TEST_DB = "CREATE DATABASE " + Constants.TEST_DATABASE;

    public static final String CREATE_TABLE = "CREATE TABLE contactmanagerdb(" +
            "ID SERIAL," +
            "FIRST_NAME TEXT NOT NULL," +
            "LAST_NAME TEXT NOT NULL," +
            "ADDRESS VARCHAR(50) NOT NULL," +
            "PHONE_NUMBER TEXT NOT NULL," +
            "DOB DATE NOT NULL," +
            "EMAIL VARCHAR(50) UNIQUE" +
            ");";
}
