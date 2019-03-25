package model.entities.enums;

/**
 *  <h1>Lifestyle enum</h1>
 *  Enum used for storing different lifestyle ways with the relative AMR double value.
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public enum Lifestyle {
    PASSIVE(1.2), MODERATE(1.375), ORDINARY(1.55), ACTIVE(1.725), SPORTY(1.9);
    /**
     * AMR value
     */
    private double AMR;

    /**
     * Constructor of the Lifestyle enum
     * @param AMR
     */
    Lifestyle(double AMR){
        this.AMR = AMR;
    }

    /**
     * Method used for looking for the relative lifestyle value
     * @param lifestyle String of the lifestyle user has
     * @return Lifestyle which is stored in the enum
     */
    public static Lifestyle lookup(String lifestyle) {
        for (Lifestyle d : Lifestyle.values())
            if (d.name().equalsIgnoreCase(lifestyle))
                return d;
        return null;
    }

    /**
     * Method used to get AMR value
     * @return double AMR
     */
    public double getAMR() {
        return AMR;
    }
}
