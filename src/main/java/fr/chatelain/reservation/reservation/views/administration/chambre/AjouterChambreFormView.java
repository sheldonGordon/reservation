package fr.chatelain.reservation.reservation.views.administration.chambre;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

import org.apache.commons.codec.binary.Base64;

import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.entities.Photos;
import fr.chatelain.reservation.reservation.back.service.ChambreService;
import fr.chatelain.reservation.reservation.back.service.PhotosService;
import fr.chatelain.reservation.reservation.views.MainView;

@Route(value = AjouterChambreFormView.ROUTE, layout = MainView.class)
@PageTitle("Formulaire d'ajout d'une chambre")
public class AjouterChambreFormView extends Div {

    private static final long serialVersionUID = -301749182574686786L;

    public static final String ROUTE = "Ajouter une chambre";

    private Binder<Chambre> binder = new Binder<>(Chambre.class);

    private TextField nom = new TextField("Nom de la chambre");

    private NumberField nombrePlace = new NumberField("Nombre de place");

    private BigDecimalField prix = new BigDecimalField("Prix de la chambre");

    private NumberField superficie = new NumberField("Superficie de la chambre");

    private List<Photos> listePhotos = new ArrayList<Photos>();

    public AjouterChambreFormView(ChambreService chambreService, PhotosService photosService) {
        setId("chambre-form-ajout");

        add(createUploadFile());
        add(createFormInscription());
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
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        MyUpload upload = new MyUpload(buffer);
        upload.setAcceptedFileTypes("image/jpeg", "image/png", "image/gif");
        // Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            Photos photos = new Photos();
            photos.setNom(event.getFileName());
            InputStream is = buffer.getInputStream(event.getFileName());
            byte[] imageBytes = new byte[(int) event.getContentLength()];
            try {
                is.read(imageBytes, 0, imageBytes.length);
                is.close();

                photos.setData(Base64.encodeBase64String(imageBytes));
                listePhotos.add(photos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        upload.addFileRemoveListener(event -> {
            System.err.println("----------------- Suppr" + event.getClass());
        });

        return upload;
    }

    public class MyUpload extends Upload {
        public MyUpload(Receiver receiver) {
            super(receiver);
        }

        public MyUpload() {
        }

        Registration addFileRemoveListener(ComponentEventListener<FileRemoveEvent> listener) {
            return super.addListener(FileRemoveEvent.class, listener);
        }

    }

    @DomEvent("file-remove")
    public static class FileRemoveEvent extends ComponentEvent<Upload> {
        public FileRemoveEvent(Upload source, boolean fromClient) {
            super(source, fromClient);
        }

    }
}
