package yukitas.mvvm.views.utils;

public class RequiredFieldValidator {
    public static Boolean validate(String value) {
        return value != null && value.trim().length() > 0;
    }
}
