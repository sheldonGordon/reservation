package fr.chatelain.reservation.reservation.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import fr.chatelain.reservation.reservation.entities.Personne;
import fr.chatelain.reservation.reservation.repository.PersonneService;

@Route(value = "inscription", layout = MainView.class)
@PageTitle("inscription")
public class InscriptionFormView extends Div {

    private static final long serialVersionUID = 2745061859487361712L;

    private Binder<Personne> binder = new Binder<>(Personne.class);

    private TextField nom = new TextField("Nom");
    private TextField prenom = new TextField("Prenom");

    private Button annuler = new Button("Annuler");
    private Button enregistrer = new Button("Enregistrer");

    public InscriptionFormView(PersonneService personneService) {
        setId("personne-form-inscription");

        add(createTitle());
        add(createFormInscription());
        add(createButtonLayout());

        binder.bind(nom, "nom");
        binder.bind(prenom, "prenom");
        clearForm();

        annuler.addClickListener(e -> clearForm());
        enregistrer.addClickListener(e -> {
            personneService.save(binder.getBean());
            Notification.show("Personne enregistrée.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new Personne());
    }

    private Component createTitle() {
        return new H3("Informations Personnelles");
    }

    private Component createFormInscription() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(nom, prenom);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(enregistrer);
        buttonLayout.add(annuler);
        return buttonLayout;
    }
}
