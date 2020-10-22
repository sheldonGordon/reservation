package fr.chatelain.reservation.reservation.views;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.vaadin.flow.function.SerializablePredicate;

public class AnniversairePredicate implements SerializablePredicate<LocalDate> {

    private static final long serialVersionUID = -2593742662615297332L;

    @Override
    public boolean test(LocalDate t) {
        boolean result = true;
        if (t != null) {
            result = t.compareTo(LocalDate.now().minus(18, ChronoUnit.DAYS)) <= 0;
            System.err
                    .println("-----------------------------" + t.compareTo(LocalDate.now().minus(18, ChronoUnit.DAYS)));
        }
        return result;
    }

}
