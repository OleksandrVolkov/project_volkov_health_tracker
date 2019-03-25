package model.entities;

/**
 *  <h1>Nutrients class</h1>
 * Nutrients class represents the nutrients are used in order to calculate diet properties.
 * It is not used as a separate entity class which is represented in a database.
 * Nutrients is a part of the dishes are used to make these calculations
 *
 * @author  Oleksandr Volkov
 * @version 1.0
 * @since   2019-03-22
 */
public class Nutrients {
    /**
     *    Proteins in the nutrients.
     */
    private double proteins;
    /**
     *    Carbohydrates in the nutrients.
     */
    private double carbohydrates;
    /**
     *  Fats in the nutrients.
     */
    private double fats;

    /**
     * Constructor of the Nutrients class with parameters
     * @param proteins
     * @param carbohydrates
     * @param fats
     */
    public Nutrients(double proteins, double carbohydrates, double fats){
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    /**
     * Overridden equals method from the superclass Object.
     * @return String The string representation of the instance.
     */
    @Override
    public String toString() {
        return "Nutrients{" +
                "proteins=" + proteins +
                ", carbohydrates=" + carbohydrates +
                ", fats=" + fats +
                '}';
    }

    /**
     * Overridden equals method from the superclass Object.
     * @param o The relative Nutrients instance to compare with
     * @return boolean Whether objects are equivalent.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nutrients nutrients = (Nutrients) o;

        if (Double.compare(nutrients.proteins, proteins) != 0) return false;
        if (Double.compare(nutrients.carbohydrates, carbohydrates) != 0) return false;
        return Double.compare(nutrients.fats, fats) == 0;
    }

    /**
     * Overridden hashCode method from the superclass Object.
     * @return int The hash code produced for the current instance
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(proteins);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(carbohydrates);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fats);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


}
