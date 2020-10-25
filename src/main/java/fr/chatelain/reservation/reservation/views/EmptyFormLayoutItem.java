package fr.chatelain.reservation.reservation.views;

import com.vaadin.flow.component.Html;

public class EmptyFormLayoutItem extends Html {

    private static final long serialVersionUID = -5769401167001988512L;

    public EmptyFormLayoutItem() {
        super("<span>&nbsp;</span>");
        getElement().getStyle().set("height", "36px");
    }

    public EmptyFormLayoutItem(String height) {
        super("<span>&nbsp;</span>");
        getElement().getStyle().set("height", height);
    }

}
