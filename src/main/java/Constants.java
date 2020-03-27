public final class Constants {

    private Constants() {}

    public static final String MENUOPTIONS = "Please select an option:\n" +
            "1. New Contact\n" +
            "2. Update Contact\n" +
            "3. Delete Contact\n" +
            "4. View Contacts\n" +
            "5. View Specific Contact\n" +
            "6. Exit";
    public static final String UPDATEFIELDS = "Select a field to update:\n" +
            "1. First name\n" +
            "2. Last name\n" +
            "3. Address\n" +
            "4. Phone number \n" +
            "5. DOB\n" +
            "6. Email";

    public static final String[] CREATEUSER = {"psql", "CREATE ROLE postgres WITH createdb login"};

    public static final String LOCALCONNECTION = "jdbc:postgresql://localhost:5432/";

    public static final String PRODCONTACTMANAGERDB = "jdbc:postgresql://localhost:5432/contactmanagerdb";

    public static final String CREATEDB = "CREATE DATABASE local_contact_manager";

    public static final String LOCALDATABASE = "local_contact_manager";

    public static final String CREATETABLE = "CREATE TABLE contactmanagerdb(" +
            "ID SERIAL," +
            "FIRST_NAME TEXT NOT NULL," +
            "LAST_NAME TEXT NOT NULL," +
            "ADDRESS VARCHAR(50) NOT NULL," +
            "PHONE_NUMBER TEXT NOT NULL," +
            "DOB DATE NOT NULL," +
            "EMAIL VARCHAR(50) UNIQUE" +
            ");";

    public static final String DBNAME = "contactmanagerdb";

    public static final String TESTCONTACTMANAGERDB = "jdbc:postgresql://localhost:5432/testContactManager";

    public static final String USERNAME = "postgres";

    public static final String PASSWORD = "contactManager1";

}
