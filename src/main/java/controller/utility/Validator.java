package controller.utility;

public class Validator implements Regex {
    public boolean isValidName(String str){
        if(str.matches(NAME_REGEX))
            return true;
        return false;
    }
    public boolean isValidInteger(String str){
        if(str.matches(INTEGER_REGEX))
            return true;
        return false;
    }
    public boolean isValidSurname(String str){
        if(str.matches(SURNAME_REGEX))
            return true;
        return false;
    }
    public boolean isValidDate(String str){
        if(str.matches(DATE_REGEX))
            return true;
        return false;
    }
    public boolean isValidDouble(String str){
        if(str.matches(DOUBLE_REGEX))
            return true;
        return false;
    }
    public boolean isValidPhoneNumber(String str){
        if(str.matches(PHONE_NUMBER_REGEX))
            return true;
        return false;
    }
    public boolean isValidEmail(String str){
        if(str.matches(EMAIL_REGEX))
            return true;
        return false;
    }
    public boolean isValidUsername(String str){
        if(str.matches(LOGIN_REGEX))
            return true;
        return false;
    }

    public boolean isValidPassword(String str){
//        if(str.matches(PASSWORD_REGEX))
            return true;
//        return false;
    }

    public boolean isValidPrice(String price){
        if(price.matches(PRICE_REGEX))
            return true;
        return false;
    }
}
