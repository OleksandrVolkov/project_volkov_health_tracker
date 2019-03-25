package model.entities.enums;

/**
 *  <h1>Sex enum</h1>
 *  Enum used for storing two sex values.
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public enum Sex {
    MALE, FEMALE;

    /**
     * Method used for looking for the relative lifestyle value
     * @param sex String of the sex user has
     * @return Sex which is stored in the enum
     */
    public static Sex lookup(String sex) {
        for (Sex s : Sex.values())
            if (s.name().equalsIgnoreCase(sex))
                return s;
        return null;
    }
}
