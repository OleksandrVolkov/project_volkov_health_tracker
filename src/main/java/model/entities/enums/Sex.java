package model.entities.enums;

public enum Sex {
    MALE, FEMALE;

    public static Sex lookup(String sex) {
        for (Sex s : Sex.values())
            if (s.name().equalsIgnoreCase(sex))
                return s;
        return null;
    }
}
