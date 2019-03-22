package model.entities;

public class Nutrients {
    private double proteins;
    private double carbohydrates;
    private double fats;

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


    @Override
    public String toString() {
        return "Nutrients{" +
                "proteins=" + proteins +
                ", carbohydrates=" + carbohydrates +
                ", fats=" + fats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nutrients nutrients = (Nutrients) o;

        if (Double.compare(nutrients.proteins, proteins) != 0) return false;
        if (Double.compare(nutrients.carbohydrates, carbohydrates) != 0) return false;
        return Double.compare(nutrients.fats, fats) == 0;
    }

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
