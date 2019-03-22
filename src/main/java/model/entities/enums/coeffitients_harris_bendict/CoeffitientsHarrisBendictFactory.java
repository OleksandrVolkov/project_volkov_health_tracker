package model.entities.enums.coeffitients_harris_bendict;

import model.entities.enums.Sex;

public class CoeffitientsHarrisBendictFactory {
    public static CoeffitientsHarrisBenedict getInstanceBySex(Sex sex){
        if(sex.equals(Sex.FEMALE))
            return new CoeffitientsHarrisBendictFemale();
        if(sex.equals(Sex.MALE))
            return new CoeffitientsHarrisBenedictMale();
        else
            return null;
    }
}
