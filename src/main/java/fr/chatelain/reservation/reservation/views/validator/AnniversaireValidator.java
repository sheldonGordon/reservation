package fr.chatelain.reservation.reservation.views.validator;

import java.time.LocalDate;
import java.time.Period;

import com.vaadin.flow.function.SerializablePredicate;

public class AnniversaireValidator implements SerializablePredicate<LocalDate> {

    private static final long serialVersionUID = -2593742662615297332L;

    @Override
    public boolean test(LocalDate t) {
        boolean result = true;
        if (t != null) {
            LocalDate today = LocalDate.now();
            LocalDate dateMinus = today.minus(Period.ofYears(18));
            result = t.compareTo(dateMinus) <= 0;
        }
        return result;
    }

}
