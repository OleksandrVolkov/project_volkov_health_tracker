package controller.utility;

import model.entities.enums.Language;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class LanguageHandler {

    /**
     * Empty constructor
     */
    public LanguageHandler(){}

    /**
     * Get value of the language
     * @param request HttpServletRequest
     * @param httpLang Language string
     * @return String language (final value)
     */
    public String getLangValue(HttpServletRequest request, String httpLang){
        String lang = request.getParameter("lang");
        if(lang != null && !lang.equals("")){
            request.getServletContext().setAttribute("lang", lang);
        } else {
            String val = (String)request.getServletContext().getAttribute("lang");
            if(val != null){
                request.getServletContext().setAttribute("lang", val);
                lang = val;
            }else {
                request.getServletContext().setAttribute("lang", "en");
                lang = "en";
            }
        }
        return lang;
    }


    private static Map<String, String> getHashMapOfNormValues(Language language){
        switch(language){
            case ENGLISH:
                return convertResourceBundleToMap(ResourceBundle.getBundle("norm_values_lang", new Locale("en", "UK")));
            case RUSSIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("norm_values_lang", new Locale("ru", "RU")));
            case UKRAINIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("norm_values_lang", new Locale("uk", "UA")));
        }
        return null;
    }


    private static Map<String, String> getHashMapOfCheckDiet(Language language){
        switch(language){
            case ENGLISH:
                return convertResourceBundleToMap(ResourceBundle.getBundle("check_diet_lang", new Locale("en", "UK")));
            case RUSSIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("check_diet_lang", new Locale("ru", "RU")));
            case UKRAINIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("check_diet_lang", new Locale("uk", "UA")));
        }
        return null;
    }


    private static Map<String, String> getHashMapOfAddCustomDish(Language language){
        switch(language){
            case ENGLISH:
                return convertResourceBundleToMap(ResourceBundle.getBundle("add_custom_dish_lang", new Locale("en", "UK")));
            case RUSSIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("add_custom_dish_lang", new Locale("ru", "RU")));
            case UKRAINIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("add_custom_dish_lang", new Locale("uk", "UA")));
        }
        return null;
    }


    private static Map<String, String> getHashMapOfAboutYourselfPage(Language language){
        switch(language){
            case ENGLISH:
                return convertResourceBundleToMap(ResourceBundle.getBundle("about_yourself_lang", new Locale("en", "UK")));
            case RUSSIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("about_yourself_lang", new Locale("ru", "RU")));
            case UKRAINIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("about_yourself_lang", new Locale("uk", "UA")));
        }
        return null;
    }



    private static Map<String, String> getHashMapOfCabinetPage(Language language){
        switch(language){
            case ENGLISH:
                return convertResourceBundleToMap(ResourceBundle.getBundle("cabinet_lang", new Locale("en", "UK")));
            case RUSSIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("cabinet_lang", new Locale("ru", "RU")));
            case UKRAINIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("cabinet_lang", new Locale("uk", "UA")));
        }
        return null;
    }


    private static Map<String, String> getHashMapOfRegistrationPage(Language language){
        switch(language){
            case ENGLISH:
                return convertResourceBundleToMap(ResourceBundle.getBundle("registration_form", new Locale("en", "UK")));
            case RUSSIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("registration_form", new Locale("ru", "RU")));
            case UKRAINIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("registration_form", new Locale("uk", "UA")));
        }
        return null;
    }


    private static Map<String, String> getHashMapOfAuthorizationForm(Language language){
        switch(language){
            case ENGLISH:
                return convertResourceBundleToMap(ResourceBundle.getBundle("login_form", new Locale("en", "UK")));
            case RUSSIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("login_form", new Locale("ru", "RU")));
            case UKRAINIAN:
                return convertResourceBundleToMap(ResourceBundle.getBundle("login_form", new Locale("uk", "UA")));
        }
        return null;
    }


    /**
     * Convert resource bundle to map
     * @param resource Resource Bundle
     * @return Map of the page
     */
    private static Map<String, String> convertResourceBundleToMap(ResourceBundle resource) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, resource.getString(key));
        }
        return map;
    }


    /**
     * Get hash map of values by page url
     * @param pageUrl Url of the page
     * @param lang Language
     * @return Map OfValuesByPageUrl
     */
    public static Map<String, String> getHashMapOfValuesByPageUrl(String pageUrl, Language lang){
        switch (pageUrl){
            case "/view/login_form.jsp":
                return getHashMapOfAuthorizationForm(lang);
            case "/view/registration_form.jsp":
                return getHashMapOfRegistrationPage(lang);
            case "/view/cabinet.jsp":
                return getHashMapOfCabinetPage(lang);
            case "/view/about_yourself.jsp":
                return getHashMapOfAboutYourselfPage(lang);
            case "/view/norm_values.jsp":
                return getHashMapOfNormValues(lang);
            case "/view/add_custom_dish.jsp":
                return getHashMapOfAddCustomDish(lang);
            case "/view/check_diet.jsp":
                return getHashMapOfCheckDiet(lang);
        }
        return null;
    }





//    private Map<String, String> getRelativeHashMap(String pageUrl, String lang){
//        if(pageUrl == null || pageUrl.equals(""))
//            pageUrl = "main.jsp";
//
//        if(lang == null || lang.equals(""))
//            lang = "en";
//
//        Map<String, String> authForm = LanguageHandler.getHashMapOfValuesByPageUrl(pageUrl, Language.getLanguage(lang));
//        authForm.put("lang", lang);
//
//        return authForm;
//    }
}
