package fr.chatelain.reservation.reservation.views.inscription;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.Period;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import fr.chatelain.reservation.reservation.back.entities.Civilite;
import fr.chatelain.reservation.reservation.back.entities.Compte;
import fr.chatelain.reservation.reservation.back.entities.Password;
import fr.chatelain.reservation.reservation.back.entities.Personne;
import fr.chatelain.reservation.reservation.back.service.CompteService;
import fr.chatelain.reservation.reservation.back.service.PasswordService;
import fr.chatelain.reservation.reservation.back.service.PersonneService;
import fr.chatelain.reservation.reservation.back.service.RoleService;
import fr.chatelain.reservation.reservation.views.MainView;
import fr.chatelain.reservation.reservation.views.commons.EmptyFormLayoutItem;
import fr.chatelain.reservation.reservation.views.validator.AnniversaireValidator;
import fr.chatelain.reservation.reservation.views.validator.PhoneValidator;

@Route(value = InscriptionFormView.ROUTE, layout = MainView.class)
@PageTitle("Formulaire d'inscription")
public class InscriptionFormView extends Div {

    public static final String ROUTE = "inscription";

    private static final long serialVersionUID = 2745061859487361712L;

    private Binder<Personne> binder = new Binder<>(Personne.class);

    private TextField nom = new TextField("Nom");
    private TextField prenom = new TextField("Prénom");
    private TextField telephone = new TextField("Téléphone");
    private TextField adresse = new TextField("Adresse");
    private PasswordField password = new PasswordField("Mot de passe");

    private Button annuler = new Button("Annuler");
    private Button enregistrer = new Button("Enregistrer");
    private Button test = new Button("Test");

    public InscriptionFormView(PersonneService personneService, PasswordService passwordService,
            RoleService roleService, CompteService compteService) {
        setId("personne-form-inscription");

        add(createTitle());
        add(createFormInscription());
        add(createButtonLayout());

        clearForm();

        annuler.addClickListener(e -> clearForm());
        test.addClickListener(e -> {
            String monPass = "AB8VWaurel=";
            String idPass = "ba5c268d-a68f-472b-9ebf-b83aa98e5ad3";
            Password bddPass = passwordService.findById(idPass);
            byte[] saltDecode = passwordService.getDecode(bddPass.getSalt());
            try {
                String calculPass = passwordService.getEncode(passwordService.getHashPassword(monPass, saltDecode));
                System.err.println("-------- " + bddPass.getHash().equals(calculPass));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
                e1.printStackTrace();
            }
        });
        enregistrer.addClickListener(e -> {
            if (binder.isValid()) {
                Personne personne = personneService.save(binder.getBean());
                try {
                    Password objectPassword = passwordService.save(password.getValue());
                    Compte compte = new Compte();
                    compte.setPassword(objectPassword);
                    compte.setPersonne(personne);
                    compteService.save(compte);
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
                    e1.printStackTrace();
                    Notification.show("Erreur mot de passe");
                }
                Notification.show("Personne enregistrée.");
                clearForm();
            } else {
                Notification.show("Erreur de saisie");
                binder.validate();
            }
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
        EmailField mail = createEmailField("E-mail");
        DatePicker anniversaire = createDatePickerField("Anniversaire");
        ComboBox<Civilite> civilite = createComboBoxField();

        password.setRequired(true);
        password.setErrorMessage("La saisie du mot de passe est obligatoire.");

        formLayout.add(mail, password, new EmptyFormLayoutItem(), new EmptyFormLayoutItem(), civilite,
                new EmptyFormLayoutItem(), nom, prenom, anniversaire, new EmptyFormLayoutItem(), telephone, adresse);
        binder.forField(civilite).asRequired("La saisie d'une civilité est obligatoire.").bind("civilite");
        binder.forField(nom).asRequired("La saisie d'un nom est obligatoire.").bind("nom");
        binder.forField(prenom).asRequired("La saisie d'un prenom est obligatoire.").bind("prenom");
        binder.forField(mail).withValidator(new EmailValidator("L'adresse e-mail n'est pas valide")).bind("mail");
        binder.forField(anniversaire)
                .withValidator(new AnniversaireValidator(), "Vous devez avoir plus de 18 ans pour vous inscrire!")
                .bind("anniversaire");
        binder.forField(telephone).withValidator(new PhoneValidator(), "Numéro de téléphone invalide")
                .bind("telephone");
        binder.forField(adresse).bind("adresse");
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        enregistrer.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(enregistrer);
        buttonLayout.add(annuler);
        buttonLayout.add(test);
        return buttonLayout;
    }

    private EmailField createEmailField(String field) {
        EmailField emailField = new EmailField(field);
        emailField.setClearButtonVisible(true);
        emailField.setErrorMessage("Entré une adresse e-mail valide.");
        return emailField;
    }

    private DatePicker createDatePickerField(String field) {
        LocalDate today = LocalDate.now();
        LocalDate dateMinus = today.minus(Period.ofYears(18));
        DatePicker datePicker = new DatePicker("Date d'anniversaire", dateMinus);
        return datePicker;
    }

    private ComboBox<Civilite> createComboBoxField() {
        ComboBox<Civilite> comboBox = new ComboBox<>();
        comboBox.setLabel("Civilité");
        comboBox.setItemLabelGenerator(Civilite::getLibelle);
        comboBox.setItems(Civilite.values());
        return comboBox;
    }
}
