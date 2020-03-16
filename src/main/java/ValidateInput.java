import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ValidateInput {

    public final static Pattern blankString = Pattern.compile("^$");

    public static Boolean validateInput(int field, String userInput) {
        switch (field) {
            case 1:
            case 2:
                return validName(userInput);
            case 4: return validNumber(userInput);
            case 5: return validDOB(userInput);
            case 6: return validEmail(userInput);
            default: return true;
        }
    }

    public static Boolean validName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Z]'?[- a-zA-Z]+$");
        return name.matches(String.valueOf(namePattern)) || isBlank(name);
    }

    public static Boolean validNumber(String phoneNumber) {
        Pattern phonePattern = Pattern.compile("^[\\d]+$");
        return (phoneNumber.matches(String.valueOf(phonePattern)) || isBlank(phoneNumber));
    }

    public static Boolean validDOB(String dOB) {
        try {
            DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dOB, dayMonthYear);
            LocalDate today = LocalDate.now();
            return (birthDate.isBefore(today) || isBlank(dOB));
        } catch (Exception e) {
            return isBlank(dOB);
        }
    }

    public static Boolean validEmail(String email) {
        Pattern emailPattern = Pattern.compile("^(.+@.+)$");
        return (email.matches(String.valueOf(emailPattern)) || isBlank(email));
    }

    public static boolean isBlank(String userInput) {
        Pattern blankPattern = Pattern.compile("^$");
        return (userInput.matches(String.valueOf(blankPattern)));
    }
}
