import controller.utility.LanguageHandler;
import model.entities.enums.Language;

public class Main {
    public static void main(String[] args){
        System.out.println(LanguageHandler.getHashMapOfRegistrationPage(Language.RUSSIAN));
    }
}
