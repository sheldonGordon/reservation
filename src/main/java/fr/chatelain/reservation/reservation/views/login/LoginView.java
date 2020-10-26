package fr.chatelain.reservation.reservation.views.login;

import java.util.Collection;
import java.util.Collections;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = 3561671604361741348L;

    public LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);

        setAlignItems(Alignment.CENTER);

        login.setAction("Se connecter");

        add(new H1("Connection"), login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!beforeEnterEvent.getLocation().getQueryParameters().getParameters()
                .getOrDefault("error", Collections.emptyList()).isEmpty()) {
            login.setError(true);
        }
    }

}
