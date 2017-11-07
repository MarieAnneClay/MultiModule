package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/** Class of validation method for user entries. */
public class ValidationComputer {
    /** Function test if the String null or empty.
     * @param name the string to be tested
     * @throws Exception */
    public static String validationName(final String name) {
        if (name == null || name.trim().length() == 0) {
            return "The name of the computer can't be empty.";
        }
        if (name.matches("^[0-9a-zA-Zàâéèëêïîôùüç -_]{1,60}$") == false) {
            return "The name of the computer is invalid. It can contains number, uppercase, space and accent with maximum 60 characters.";
        }
        return null;
    }

    /** Function test if the date is valid.
     * @param date string
     * @throws Exception */
    public static String validationDate(String date) {
        SimpleDateFormat fmtEN = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat fmtFR = new SimpleDateFormat("dd/mm/yyyy");
        if (date != null && date.trim().length() != 0) {
            if (date.contains("-")) {
                try {
                    fmtEN.parse(date);
                } catch (ParseException e) {
                    return "The format of the date is invalid.";

                }
            } else {
                try {
                    fmtFR.parse(date);
                } catch (ParseException ex) {
                    return "Le format de la date n'est pas valide.";
                }
            }

        }
        return null;
    }

    /** Function test if the introduced date enter is before the discontinued date.
     * @param introducedDate LocalDate
     * @param discontinuedDate LocalDate
     * @throws Exception */
    public static String validationIntroducedBeforeDiscontinued(LocalDate introducedDate, LocalDate discontinuedDate) throws ValidatorException {
        if (introducedDate != null && discontinuedDate != null) {
            if (introducedDate.isAfter(discontinuedDate)) {
                return "Introduced date later then discontinued date.";
            }
        }
        return null;
    }

    public static void validationComputer(String name, LocalDate introduced, LocalDate discontinued) throws ValidatorException {
        ValidationComputer.validationName(name);
        ValidationComputer.validationIntroducedBeforeDiscontinued(introduced, discontinued);

    }

}
