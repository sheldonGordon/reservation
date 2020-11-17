package fr.chatelain.reservation.reservation.views.administration.chambre;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.apache.commons.io.IOUtils;

import elemental.json.JsonObject;
import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.entities.Photos;
import fr.chatelain.reservation.reservation.back.entities.Service;
import fr.chatelain.reservation.reservation.back.service.ChambreService;
import fr.chatelain.reservation.reservation.back.service.PhotosService;
import fr.chatelain.reservation.reservation.back.service.ServiceService;
import fr.chatelain.reservation.reservation.views.MainView;

@Route(value = ModifierChambreFormView.ROUTE, layout = MainView.class)
@CssImport("./styles/views/administration/ajouter-chambre-form-view.css")
@PageTitle("Formulaire de modification d'une chambre")
public class ModifierChambreFormView extends Div implements HasUrlParameter<String> {

    private static final long serialVersionUID = -301749182574686786L;

    public static final String ROUTE = "modifier_une_chambre";

    public static final String NOM_TAB = "Modifier une chambre";

    private Chambre chambreSelected;

    private Binder<Chambre> binder = new Binder<>(Chambre.class);

    private TextField nom = new TextField("Nom de la chambre");

    private NumberField nombrePlace = new NumberField("Nombre de place");

    private BigDecimalField prix = new BigDecimalField("Prix de la nuitée");

    private NumberField superficie = new NumberField("Superficie de la chambre");

    private Button annuler = new Button("Annuler");

    private Button enregistrer = new Button("Enregistrer");

    private Set<Photos> listPhotos = new HashSet<Photos>();

    private MultiFileMemoryBuffer buffer;

    private Upload upload;

    private Button buttonUpload = new Button("Ajouter des photos");

    private Set<Service> listServiceSelected = new HashSet<Service>();

    private MultiSelectListBox<Service> listBoxService = new MultiSelectListBox<Service>();

    private Set<Service> listAllService = new HashSet<Service>();

    private ChambreService chambreService;
    private PhotosService photosService;
    private ServiceService serviceService;

    public ModifierChambreFormView(ChambreService cs, PhotosService ps, ServiceService ss) {

        chambreService = cs;
        photosService = ps;
        serviceService = ss;

        setId("chambre-form-ajout");
    }

    private void init() {
        listAllService = serviceService.findAll();

        add(createUploadFile());
        add(createFormInscription());
        add(createListService());
        add(createButtonLayout());

        annuler.addClickListener(e -> redirectLister());
        enregistrer.addClickListener(e -> {
            if (binder.isValid()) {
                listPhotos.forEach(p -> {
                    photosService.save(p);
                });
                binder.getBean().setPhotos(listPhotos);
                binder.getBean().setServices(listServiceSelected);
                chambreService.save(binder.getBean());
                Notification.show("Chambre ajoutée.");
                redirectLister();
            } else {
                Notification.show("Erreur de saisie");
                binder.validate();
            }
        });
    }

    private void redirectLister() {
        UI.getCurrent().getPage().setLocation(ListerChambreView.ROUTE);
    }

    private Component createFormInscription() {
        FormLayout formLayout = new FormLayout();

        nombrePlace.setHasControls(true);
        nombrePlace.setStep(1d);
        prix.setPrefixComponent(new Icon(VaadinIcon.EURO));
        superficie.setHasControls(true);
        superficie.setStep(0.5d);

        formLayout.add(nom, nombrePlace, prix, superficie);

        binder.forField(nom).asRequired("La saisie d'un nom est obligatoire.").bind("nom");
        binder.forField(nombrePlace).asRequired("La saisie d'un nom est obligatoire.").bind("nombrePersonne");
        binder.forField(prix).asRequired("La saisie d'un nom est obligatoire.").bind("prix");
        binder.forField(superficie).bind("superficie");

        return formLayout;
    }

    private Component createUploadFile() {
        buffer = new MultiFileMemoryBuffer();

        upload = new Upload(buffer);

        buttonUpload.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Span dropLabel = new Span("Déposer vos images ici.");

        upload.setUploadButton(buttonUpload);
        upload.setDropLabel(dropLabel);
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");

        upload.addStartedListener(event -> {
            String fileName = event.getFileName();
            if (buffer.getFiles().contains(fileName)) {
                Notification.show("Fichier déjà présent.");
                event.getUpload().interruptUpload();
            }
        });

        upload.addSucceededListener(event -> {
            Photos photos = new Photos();
            photos.setNom(event.getFileName());
            photos.setTypeMime(event.getMIMEType());

            try {
                InputStream is = buffer.getInputStream(event.getFileName());
                byte[] imageBytes = IOUtils.toByteArray(is);
                photos.setData(Base64.getEncoder().encodeToString(imageBytes));
                listPhotos.add(photos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        upload.getElement().addEventListener("file-remove", event -> {
            JsonObject eventData = event.getEventData();
            String namePhotosDeleted = eventData.getString("event.detail.file.name");
            List<Photos> listTmp = new ArrayList<Photos>(listPhotos);
            listTmp.forEach(photos -> {
                if (photos.getNom().equals(namePhotosDeleted)) {
                    listPhotos.remove(photos);
                }
            });
        }).addEventData("event.detail.file.name");

        return upload;
    }

    public Component createListService() {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidthFull();
        H4 titre = new H4("Ajouter des services à la chambre :");
        listBoxService.setItems(listAllService);
        listBoxService.setRenderer(new ComponentRenderer<>((service) -> {
            Div text = new Div();
            text.setText(service.getLibelle());

            return text;
        }));
        listBoxService.setWidthFull();

        listBoxService.addSelectionListener(event -> {
            event.getAddedSelection().forEach(s -> {
                listServiceSelected.add(s);
            });

            event.getRemovedSelection().forEach(s -> {
                listServiceSelected.remove(s);
            });
        });
        layout.add(titre);
        layout.add(listBoxService);

        return layout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(enregistrer);
        buttonLayout.add(annuler);
        return buttonLayout;
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        event.getLocation().getSubLocation().ifPresent(location -> {
            String idChambre = location.getSegments().get(0);
            Optional<Chambre> optionalChambre = chambreService.findById(idChambre);
            if (optionalChambre.isPresent()) {
                chambreSelected = optionalChambre.get();
                binder.setBean(chambreSelected);
            } else {
                redirectLister();
            }
            init();
        });
    }

}
