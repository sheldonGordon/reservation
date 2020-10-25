package fr.chatelain.reservation.reservation.views.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.flow.function.SerializablePredicate;

public class PhoneValidator implements SerializablePredicate<String> {

    private static final long serialVersionUID = 1L;

    private static final String regex = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";

    @Override
    public boolean test(String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

}
