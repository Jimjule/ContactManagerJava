import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ValidateInput {

    public final static Pattern blankString = Pattern.compile("^$");

    public static Boolean validateInput(String detail, String userInput, boolean isAnUpdate) {
        switch (detail) {
            case ContactFields.FirstName:
            case ContactFields.LastName:
                return ValidateInput.validName(userInput, isAnUpdate);
            case ContactFields.PhoneNumber: return ValidateInput.validNumber(userInput, isAnUpdate);
            case ContactFields.DOB: return ValidateInput.validDOB(userInput, isAnUpdate);
            case ContactFields.Email: return ValidateInput.validEmail(userInput, isAnUpdate);
            case "Field": return ValidateInput.validField(userInput);
            default: return true;
        }
    }

    public static Boolean validName(String name, boolean isAnUpdate) {
        Pattern namePattern = Pattern.compile("^[A-Z]'?[- a-zA-Z]+$");
        return name.matches(String.valueOf(namePattern)) || isBlank(name, isAnUpdate);
    }

    public static Boolean validNumber(String phoneNumber, boolean isAnUpdate) {
        Pattern phonePattern = Pattern.compile("^[\\d]+$");
        return (phoneNumber.matches(String.valueOf(phonePattern)) || isBlank(phoneNumber, isAnUpdate));
    }

    public static Boolean validField(String fieldNumber) {
        Pattern fieldPattern = Pattern.compile("^[1-6]$");
        return (fieldNumber.matches(String.valueOf(fieldPattern)));
    }

    public static Boolean validDOB(String dOB, boolean isAnUpdate) {
        try {
            DateTimeFormatter dayMonthYear = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dOB, dayMonthYear);
            LocalDate today = LocalDate.now();
            return (birthDate.isBefore(today) || isBlank(dOB, isAnUpdate));
        } catch (Exception e) {
            return isBlank(dOB, isAnUpdate);
        }
    }

    public static Boolean validEmail(String email, boolean isAnUpdate) {
        Pattern emailPattern = Pattern.compile("^(.+@.+)$");
        return (email.matches(String.valueOf(emailPattern)) || isBlank(email, isAnUpdate));
    }

    public static boolean isBlank(String userInput, boolean isAnUpdate) {
        Pattern blankPattern = Pattern.compile("^$");
        return (userInput.matches(String.valueOf(blankPattern)) && isAnUpdate);
    }
}
