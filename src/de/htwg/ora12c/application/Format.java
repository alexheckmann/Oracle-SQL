package de.htwg.ora12c.application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tandem 21; Philip Schächinger, Alexander Aichholz, Alexander Heckmann
 */
public enum Format {

    BIRTHDAY_DATE("(0[1-9]|(1|2)[0-9]|30|31)[.](0[1-9]|1[0-2])[.][0-9]{4}", "birthday date", 10),

    DATE("(0[1-9]|(1|2)[0-9]|30|31)[.](0[1-9]|1[0-2])[.][0-9]{4}", "date", 10);

    private final int MAX_LENGTH;
    /**
     * The pattern the enum type shall match.
     */
    private final String REGEX;
    /**
     * Name of what the enum type represents.
     */
    private final String NAME;

    Format(String REGEX, String NAME, int maxLength) {

        this.REGEX = REGEX;
        this.NAME = NAME;
        this.MAX_LENGTH = maxLength;
    }

    /**
     * Checks whether {@code text} matches a given format and repeats to read in new values until
     * the text is in the correct format.
     * Cases of incorrect input are:
     * - empty strings / null strings
     * - if the string exceeds the data type limit of the corresponding attribute in the data base
     * - if the string doesn't match the pattern
     *
     * @param text text to be checked
     * @param format format the text shall match
     * @return String matching the requested format
     */
    public static String validate(String text, Format format) throws ParseException {

        while (text.length() == 0) {

            text = Utils.readString("Please enter a correct " + format.getNAME() + ": ");

        }

        while (text.length() > format.MAX_LENGTH) {

            System.out.println();
            text = Utils.readString("Input is too long. " +
                    "Please enter a shortened version of the " + format.getNAME() + ": ");

        }


        if (format.equals(Format.BIRTHDAY_DATE)) {

            while (!text.matches(format.REGEX)) {

                text = Utils.readString("Please enter a correct " + format.getNAME() + ": ");

            }

            DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

            Date currentDate = new Date();
            Date birthDayDate = simpleDateFormat.parse(text);

            // ensure that the birthday is before the current date
            while (!(birthDayDate.before(currentDate))) {

                text = Utils.readString("Please enter a correct " + format.getNAME() + ": ");
                birthDayDate = simpleDateFormat.parse(text);

            }

            text = simpleDateFormat.format(birthDayDate);

        } else if (format.equals(Format.DATE)) {

            while (!text.matches(format.REGEX)) {

                text = Utils.readString("Please enter a correct " + format.getNAME() + ": ");

            }

        }

        return text;

    }

    public String getREGEX() {

        return REGEX;
    }

    public String getNAME() {

        return NAME;
    }
}
