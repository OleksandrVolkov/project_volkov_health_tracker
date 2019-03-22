package model.entities.enums;

public enum Lifestyle {
    PASSIVE(1.2), MODERATE(1.375), ORDINARY(1.55), ACTIVE(1.725), SPORTY(1.9);
    private double AMR;

    Lifestyle(double AMR){
        this.AMR = AMR;
    }
    public static Lifestyle lookup(String lifestyle) {
        for (Lifestyle d : Lifestyle.values())
            if (d.name().equalsIgnoreCase(lifestyle))
                return d;
        return null;
    }

    public double getAMR() {
        return AMR;
    }
}
