package fr.chatelain.reservation.reservation.views.administration.chambre;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.service.ChambreService;
import fr.chatelain.reservation.reservation.views.MainView;

@Route(value = ListerChambreView.ROUTE, layout = MainView.class)
@CssImport("./styles/views/administration/ajouter-chambre-form-view.css")
@PageTitle("Liste des chambres")
public class ListerChambreView extends Div {

    private static final long serialVersionUID = 7152701805741723212L;

    public static final String ROUTE = "Liste des chambres";

    private List<Chambre> listeChambres = new ArrayList<Chambre>(0);

    private Component detailsChambre = null;

    private String nameTabSelected = "";

    public ListerChambreView(ChambreService chambreService) {
        setId("chambre-list");

        listeChambres = chambreService.findAll();

        add(createTabsChambre());
    }

    private Component createTabsChambre() {
        Div div = new Div();
        Tabs tabs = new Tabs();
        listeChambres.forEach(c -> {
            tabs.add(new Tab(c.getNom()));
        });

        nameTabSelected = tabs.getSelectedTab().getLabel();

        listeChambres.forEach(c -> {
            if (c.getNom().equals(nameTabSelected)) {
                detailsChambre = showInformationsChambre(c);
                div.add(detailsChambre);
            }
        });

        tabs.addSelectedChangeListener(event -> {
            div.remove(detailsChambre);
            nameTabSelected = tabs.getSelectedTab().getLabel();
            listeChambres.forEach(c -> {
                if (c.getNom().equals(nameTabSelected)) {
                    detailsChambre = showInformationsChambre(c);
                    div.add(detailsChambre);
                }
            });
        });
        div.add(tabs);
        div.add(detailsChambre);

        return div;
    }

    private Component showInformationsChambre(Chambre chambre) {
        Div div = new Div();
        div.setId("details-chambre");

        Text nombrePlace = new Text("Nombre de places : " + chambre.getNombrePersonne());
        Text superficie = new Text("Superficie : " + chambre.getSuperficie() + " m²");
        Text prix = new Text("Prix : " + chambre.getPrix() + " €");

        div.add(nombrePlace);
        div.add(superficie);
        div.add(prix);

        return div;
    }
}
