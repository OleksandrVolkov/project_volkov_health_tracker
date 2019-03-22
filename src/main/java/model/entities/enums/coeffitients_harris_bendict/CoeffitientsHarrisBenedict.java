package model.entities.enums.coeffitients_harris_bendict;

public class CoeffitientsHarrisBenedict {
    protected double initialCoefficient;
    protected double weightCoefficient;
    protected double heightCoefficient;
    protected double ageCoefficient;

    public double getInitialCoefficient() {
        return initialCoefficient;
    }

    public void setInitialCoefficient(double initialCoefficient) {
        this.initialCoefficient = initialCoefficient;
    }

    public double getWeightCoefficient() {
        return weightCoefficient;
    }

    public void setWeightCoefficient(double weightCoefficient) {
        this.weightCoefficient = weightCoefficient;
    }

    public double getHeightCoefficient() {
        return heightCoefficient;
    }

    public void setHeightCoefficient(double heightCoefficient) {
        this.heightCoefficient = heightCoefficient;
    }

    public double getAgeCoefficient() {
        return ageCoefficient;
    }

    public void setAgeCoefficient(double ageCoefficient) {
        this.ageCoefficient = ageCoefficient;
    }
}
