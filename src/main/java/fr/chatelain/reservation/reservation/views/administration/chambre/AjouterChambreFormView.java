package fr.chatelain.reservation.reservation.views.administration.chambre;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.service.ChambreService;
import fr.chatelain.reservation.reservation.back.service.PhotosService;
import fr.chatelain.reservation.reservation.views.MainView;

@Route(value = AjouterChambreFormView.ROUTE, layout = MainView.class)
@PageTitle("Formulaire d'ajout d'une chambre")
public class AjouterChambreFormView extends Div {

    private static final long serialVersionUID = -301749182574686786L;

    public static final String ROUTE = "ajouterChambre";

    private Binder<Chambre> binder = new Binder<>(Chambre.class);

    private TextField nom = new TextField("Nom de la chambre");

    private NumberField nombrePlace = new NumberField("Nombre de place");

    private BigDecimalField prix = new BigDecimalField("Prix de la chambre");

    private NumberField superficie = new NumberField("Superficie de la chambre");

    public AjouterChambreFormView(ChambreService chambreService, PhotosService photosService) {
        setId("chambre-form-ajout");

        add(createFormInscription());
    }

    private Component createFormInscription() {
        FormLayout formLayout = new FormLayout();

        nombrePlace.setHasControls(true);
        prix.setPrefixComponent(new Icon(VaadinIcon.EURO));
        superficie.setHasControls(true);

        formLayout.add(nom, nombrePlace, prix, superficie);

        binder.forField(nom).asRequired("La saisie d'un nom est obligatoire.").bind("nom");
        binder.forField(nombrePlace).asRequired("La saisie d'un nom est obligatoire.").bind("nombrePersonne");
        binder.forField(prix).asRequired("La saisie d'un nom est obligatoire.").bind("prix");
        binder.forField(superficie).bind("superficie");

        return formLayout;
    }
}
