package fr.chatelain.reservation.reservation.views.administration.chambre;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.apache.commons.codec.binary.Base64;

import elemental.json.Json;
import elemental.json.JsonObject;
import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.entities.Photos;
import fr.chatelain.reservation.reservation.back.entities.Service;
import fr.chatelain.reservation.reservation.back.service.ChambreService;
import fr.chatelain.reservation.reservation.back.service.PhotosService;
import fr.chatelain.reservation.reservation.back.service.ServiceService;
import fr.chatelain.reservation.reservation.views.MainView;

@Route(value = AjouterChambreFormView.ROUTE, layout = MainView.class)
@CssImport("./styles/views/administration/ajouter-chambre-form-view.css")
@PageTitle("Formulaire d'ajout d'une chambre")
public class AjouterChambreFormView extends Div {

    private static final long serialVersionUID = -301749182574686786L;

    public static final String ROUTE = "Ajouter une chambre";

    private Binder<Chambre> binder = new Binder<>(Chambre.class);

    private TextField nom = new TextField("Nom de la chambre");

    private NumberField nombrePlace = new NumberField("Nombre de place");

    private BigDecimalField prix = new BigDecimalField("Prix de la nuitée");

    private NumberField superficie = new NumberField("Superficie de la chambre");

    private Button annuler = new Button("Annuler");

    private Button enregistrer = new Button("Enregistrer");

    private List<Photos> listPhotos = new ArrayList<Photos>();

    private MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();

    private Upload upload = new Upload(buffer);

    private Button buttonUpload = new Button("Ajouter des photos");

    private List<Service> listServiceSelected = new ArrayList<Service>();

    private MultiSelectListBox<Service> listBoxService = new MultiSelectListBox<Service>();

    private List<Service> listAllService = new ArrayList<Service>();

    public AjouterChambreFormView(ChambreService chambreService, PhotosService photosService,
            ServiceService serviceService) {
        setId("chambre-form-ajout");

        listAllService = serviceService.findAll();

        add(createUploadFile());
        add(createFormInscription());
        add(createListService());
        add(createButtonLayout());

        clearForm();

        annuler.addClickListener(e -> clearForm());
        enregistrer.addClickListener(e -> {
            if (binder.isValid()) {
                listPhotos.forEach(p -> {
                    photosService.save(p);
                });
                binder.getBean().setPhotos(listPhotos);
                binder.getBean().setServices(listServiceSelected);
                chambreService.save(binder.getBean());
                Notification.show("Chambre ajoutée.");
                clearForm();
            } else {
                Notification.show("Erreur de saisie");
                binder.validate();
            }
        });
    }

    private void clearForm() {
        binder.setBean(new Chambre());
        listBoxService.deselectAll();
        listServiceSelected = new ArrayList<Service>(0);
        buffer = new MultiFileMemoryBuffer();
        upload.getElement().setPropertyJson("files", Json.createArray());
        listPhotos = new ArrayList<Photos>(0);
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
        buttonUpload.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Span dropLabel = new Span("Déposer vos images ici.");

        upload.setUploadButton(buttonUpload);
        upload.setDropLabel(dropLabel);
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");

        upload.addSucceededListener(event -> {
            Photos photos = new Photos();
            photos.setNom(event.getFileName());
            InputStream is = buffer.getInputStream(event.getFileName());
            byte[] imageBytes = new byte[(int) event.getContentLength()];
            try {
                is.read(imageBytes, 0, imageBytes.length);
                is.close();

                photos.setData(Base64.encodeBase64String(imageBytes));
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

}
