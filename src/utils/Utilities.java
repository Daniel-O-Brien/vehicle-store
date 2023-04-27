package utils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Float.parseFloat;

public class Utilities {

    /**
     * This method takes in a decimal point number and truncates it to two decimal places.  Note
     * that the method does NOT round when truncating; the numbers after the two decimal places are
     * just removed.
     * <p>
     * The method does the truncating in this manner:
     * - multiply the number by 100 e.g. 16.543235523 * 100 = 1654.3235523
     * - cast the multiplied number as an in e.g. 1654.3235523 = 1654
     * - finally, the multiplied and casted number is divided by 100 and returned e.g. 1654 = 16.54
     *
     * @param number Number to be truncated to two decimal places
     * @return the number, passed as a parameter, truncated to two decimal places (note: not rounded)
     */
    public static double toTwoDecimalPlaces(double number) {
        return (int) (number * 100) / 100.0;
    }

    /**
     * This method returns Y if the booleanToConvert value is true. Returns N otherwise.
     *
     * @param booleanToConvert The boolean value that will be used to determine Y/N
     * @return Returns Y if the booleanToConvert value is true. Returns N otherwise.
     */
    public static char booleanToYN(boolean booleanToConvert) {
        return booleanToConvert ? 'Y' : 'N';
    }

    /**
     * This method returns true if the charToConvert value is Y or y. Returns false in all other cases.
     *
     * @param charToConvert The char value that will be used to determine true/false.
     * @return Returns true if the charToConvert value is Y or y. Returns false otherwise.
     */
    public static boolean YNtoBoolean(char charToConvert) {
        return ((charToConvert == 'y') || (charToConvert == 'Y'));
    }


    /**
     * This method returns true if the numberToCheck is between min and max (both inclusive)
     *
     * @param numberToCheck The number whose range is being checked.
     * @param min The minimum range number to check against (inclusive)
     * @param max The maximum range number to check against (inclusive)
     * @return Returns true if the numberToCheck is between min and max (both inclusive), false otherwise.
     */
    public static boolean validRange(int numberToCheck, int min, int max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

    public static boolean validRange(float numbertoCheck, float min, float max, float delta) {
        return ((numbertoCheck >= (min-delta)) && (numbertoCheck <= (max+delta)));

    }

    public static String truncateString(String stringToTruncate, int length){
        if (stringToTruncate != null) {
            if (stringToTruncate.length() <= length) {
                return stringToTruncate;
            } else {
                return stringToTruncate.substring(0, length);
            }
        }
        else
            return null;
    }

    public static boolean validStringlength(String strToCheck, int maxLength){
        if (strToCheck != null ){
            return strToCheck.length() <= maxLength;
        }
        return false;
    }

    public static boolean isValidIndex(List list, int indexToCheck){
        return ((indexToCheck >= 0) && (indexToCheck < list.size()));
    }

    /**
     * This method returns an int greater than 0 if the String can be parsed to an Int
     *
     * @param str The string to be parsed to int.
     * @return Returns an int greater than 0 if the string can be parsed
     */

    public static int tryParseInt(String str) {
        int parsedInt;
        try {
            parsedInt = parseInt(str);
        }
        catch(NumberFormatException e) {
            parsedInt = -1;
        }
        return parsedInt;
    }

    /**
     * This method returns a float greater than 0 if the String can be parsed to an Int
     *
     * @param str The string to be parsed to int.
     * @return Returns a float greater than 0 if the string can be parsed
     */

    public static float tryParseFloat(String str) {
        float parsedFloat;
        try {
            parsedFloat = parseFloat(str);
        }
        catch(NumberFormatException e) {
            parsedFloat = -1;
        }
        return parsedFloat;
    }

    public static boolean numToBoolean(int intToConvert) {
        return intToConvert == 0;
    }
}
