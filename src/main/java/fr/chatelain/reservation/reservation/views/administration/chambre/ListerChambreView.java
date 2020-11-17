package fr.chatelain.reservation.reservation.views.administration.chambre;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.flowingcode.vaadin.addons.carousel.Carousel;
import com.flowingcode.vaadin.addons.carousel.Slide;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.entities.Photos;
import fr.chatelain.reservation.reservation.back.service.ChambreService;
import fr.chatelain.reservation.reservation.views.MainView;

@Route(value = ListerChambreView.ROUTE, layout = MainView.class)
@CssImport("./styles/views/administration/lister-chambre-view.css")
@PageTitle("Liste des chambres")
public class ListerChambreView extends Div {

    private static final long serialVersionUID = 7152701805741723212L;

    public static final String ROUTE = "liste_des_chambres";

    public static final String NOM_TAB = "Liste des chambre";

    private List<Chambre> listeChambres = new ArrayList<Chambre>(0);

    private Button modifier = new Button("Modifier");

    private Button supprimer = new Button("Supprimer");

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
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setId("details-chambre");

        Div divLeft = new Div();
        divLeft.setId("div-left");
        Div divCenter = new Div();
        divCenter.setId("div-center");
        Div divRight = new Div();
        divRight.setId("div-right");

        Paragraph labelNombrePlace = new Paragraph("Nombre de places : ");
        Paragraph labelSuperficie = new Paragraph("Superficie : ");
        Paragraph labelPrix = new Paragraph("Prix : ");

        divLeft.add(labelNombrePlace);
        divLeft.add(labelSuperficie);
        divLeft.add(labelPrix);

        Paragraph nombrePlace = new Paragraph("" + chambre.getNombrePersonne().intValue());
        Paragraph superficie = new Paragraph(chambre.getSuperficie() + " m²");
        Paragraph prix = new Paragraph(chambre.getPrix() + " €");

        divCenter.add(nombrePlace);
        divCenter.add(superficie);
        divCenter.add(prix);

        chambre.getServices().forEach(s -> {
            divRight.add(new Paragraph(s.getLibelle()));
        });

        verticalLayout.add(showImagesChambre(chambre.getPhotos()));
        horizontalLayout.add(divLeft);
        horizontalLayout.add(divCenter);
        horizontalLayout.add(divRight);

        verticalLayout.add(horizontalLayout);
        modifier.addClickListener(event -> {
            String uri = String.format("%s/%s", ModifierChambreFormView.ROUTE, chambre.getId());
            UI.getCurrent().getPage().setLocation(uri);
        });

        verticalLayout.add(createButtonLayout());

        return verticalLayout;
    }

    private Component showImagesChambre(Set<Photos> listePhotos) {
        Slide[] slides = new Slide[listePhotos.size()];

        listePhotos.forEach(p -> {
            String resource = "data:" + p.getTypeMime() + ";base64, " + p.getData();
            Image image = new Image(resource, "Image non trouvée.");
            image.setMaxHeight("350px");
            slides[new ArrayList<>(listePhotos).indexOf(p)] = new Slide(image);
        });

        Carousel carousel = new Carousel();
        carousel.setSlides(slides);
        carousel.setWidth("100%");
        carousel.setHeight("400px");
        return carousel;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        modifier.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        supprimer.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonLayout.add(modifier);
        buttonLayout.add(supprimer);
        return buttonLayout;
    }

}
