import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    public int newContact(String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            if (connection != null) {
                statement = connection.createStatement();
                String sql = "INSERT INTO contactmanagerdb VALUES(DEFAULT, '" +
                        firstName + "', '" + lastName + "', '" + address + "', '" + phoneNumber + "', '" + dOB + "', '" + email +
                        "');";
                statement.execute(sql);
                statement.close();
                connection.close();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return - 1;
        }
    }

    public int updateContact(String id, String firstName, String lastName, String address, String phoneNumber, String dOB, String email) {
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactmanagerdb", "postgres", "contactManager1");
            if (connection != null) {
                statement = connection.createStatement();
                String sql = "UPDATE contactmanagerdb " +
                        "SET first_name = " + firstName + ", last_name = " + lastName + ", address = " + address + ", phone_number = " + phoneNumber + ", dob = " + dOB + ", email = " + email + "" +
                        "WHERE id = " + id + ");";
                statement.executeUpdate(sql);
                statement.close();
                connection.close();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return - 1;
        }
    }
}
