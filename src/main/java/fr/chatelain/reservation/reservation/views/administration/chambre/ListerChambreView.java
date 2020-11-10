package fr.chatelain.reservation.reservation.views.administration.chambre;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import de.mekaso.vaadin.addons.Carousel;
import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.entities.Photos;
import fr.chatelain.reservation.reservation.back.service.ChambreService;
import fr.chatelain.reservation.reservation.views.MainView;

@Route(value = ListerChambreView.ROUTE, layout = MainView.class)
@CssImport("./styles/views/administration/lister-chambre-view.css")
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
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("details-chambre");

        Div divLeft = new Div();
        divLeft.setId("div-left");
        Div divRight = new Div();

        Paragraph labelNombrePlace = new Paragraph("Nombre de places : ");
        Paragraph labelSuperficie = new Paragraph("Superficie : ");
        Paragraph labelPrix = new Paragraph("Prix : ");

        divLeft.add(labelNombrePlace);
        divLeft.add(labelSuperficie);
        divLeft.add(labelPrix);

        Paragraph nombrePlace = new Paragraph("" + chambre.getNombrePersonne().intValue());
        Paragraph superficie = new Paragraph(chambre.getSuperficie() + " m²");
        Paragraph prix = new Paragraph(chambre.getPrix() + " €");

        divRight.add(nombrePlace);
        divRight.add(superficie);
        divRight.add(prix);

        layout.add(showImagesChambre(chambre.getPhotos()));
        layout.add(divLeft);
        layout.add(divRight);
        return layout;
    }

    private Component showImagesChambre(List<Photos> listePhotos) {
        Carousel carousel = Carousel.create();
        carousel.setMaxHeight("500px");

        listePhotos.forEach(p -> {
            String resource = "data:image/png;base64," + p.getData();
            Image image = new Image(resource, "dummy image");
            carousel.add(image);
        });

        return carousel;
    }
}
