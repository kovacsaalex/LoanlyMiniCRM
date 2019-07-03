package com.example.demo.view;

import com.example.demo.pojo.Client;
import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;


public class AddClient extends Composite<VerticalLayout> {

    private Grid<Client> grid = new Grid<>();
    private Client client;
    private final ClientRepository clientRepository;
    Binder<Client> binder = new Binder<>(Client.class);

    private HorizontalLayout personalDataLayout = new HorizontalLayout();
    private VerticalLayout dialogBaseLayout;
    private HorizontalLayout addressDataLayout;
    private HorizontalLayout idDataLayout;
    private HorizontalLayout contactDataLayout;
    private HorizontalLayout buttonLayout;

    private TextField firstname = new TextField("Vezetéknév");
    private TextField lastname = new TextField("Keresztnév");
    private TextField maidenName = new TextField("Leánykori név");
    private TextField motherName = new TextField("Anyja neve");
    private TextField birthPlace = new TextField("Születési hely");
    private DatePicker birthDate = new DatePicker("Születési idő");

    private TextField postalCode = new TextField("Iranyítószám");
    private TextField city = new TextField("Város");
    private TextField address = new TextField("Cím");
    private DatePicker movingTime = new DatePicker("Beköltözési idő");

    private TextField personalID = new TextField("Személyi szám");
    private TextField taxID = new TextField("Adószám");
    private TextField socialSecurityCard = new TextField("Taj szám");
    private TextField cardId = new TextField("Személyi igazolvány szám");
    private TextField addressCardID = new TextField("Lakcímkártya száma");

    private TextField phoneNumber = new TextField("Mobil");
    private TextField email = new TextField("E-mail");

    private H3 dialogLabelText = new H3("Új ügyfél adatai");

    private Button confirmButton = new Button("Mentés");
    private Button create = new Button("Új");

    final String REGEXNAME = "([A-Za-zaáeéiíoóöőuúüűAÁEÉIÍOÓÖŐUÚÜŰ .-]+)";
    final String REGEXPOSTALCODE = "\\d{4}$";
    final String REGEXPID = "\\d{1}-\\d{6}-\\d{4}$";
    final String REGEXTAXNUMBER = "\\d{10}$";
    final String REGEXCARD = "^\\d{6}[A-Z]{2}$";
    final String REGEXTAJ = "^\\d{9}$";
    final String REGEXTEL = "^(\\d{2})(\\d{7})$";


    public AddClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

        dialogBaseLayout = new VerticalLayout();
        addressDataLayout = new HorizontalLayout();
        idDataLayout = new HorizontalLayout();
        buttonLayout = new HorizontalLayout();
        contactDataLayout = new HorizontalLayout();

        personalDataLayout.add(firstname, lastname, maidenName, motherName, birthPlace, birthDate);         //OK
        addressDataLayout.add(postalCode, city, address, movingTime); //OK
        idDataLayout.add(cardId, addressCardID, personalID, taxID, socialSecurityCard); //OK
        contactDataLayout.add(phoneNumber,email);
        buttonLayout.add(confirmButton,create);
        dialogBaseLayout.add(dialogLabelText,personalDataLayout, addressDataLayout,idDataLayout,contactDataLayout);

        firstname.setRequired(true);
        lastname.setRequired(true);


        //Names binder
        try {
            binder.forField(firstname)
                    .asRequired("Kötelező")
                    .withValidator(
                            name -> name.length() >= 3,
                            "Kérem adja meg a teljes nevét!")
                    .withValidator(
                            name -> name.matches(REGEXNAME),
                            "Csak betű megengedett!")
                    .bind(Client::getFirstname, Client::setFirstname);

            binder.forField(lastname)
                    .asRequired("Kötelező")
                    .withValidator(
                            name -> name.length() >= 3,
                            "Kérem adja meg a teljes nevét!")
                    .withValidator(
                            name -> name.matches(REGEXNAME),
                            "Csak betű megengedett!")
                    .bind(Client::getLastname, Client::setLastname);

            binder.forField(maidenName)
                    .asRequired("Kötelező")
                    .withValidator(
                            name -> name.matches(REGEXNAME),
                            "Csak betű megengedett!")
                    .bind(Client::getMaidenName, Client::setMaidenName);

            binder.forField(motherName)
                    .asRequired("Kötelező")
                    .withValidator(
                            name -> name.matches(REGEXNAME),
                            "Csak betű megengedett!")
                    .bind(Client::getMotherName, Client::setMotherName);

            binder.forField(birthPlace)
                    .withValidator(
                            name -> name.matches(REGEXNAME),
                            "Csak betű megengedett!")
                    .bind(Client::getBirthPlace, Client::setBirthPlace);

        } catch (Exception e) {
            System.out.println("Name&Birth: " + e.getMessage());
        }

        //Address Binder
        try {
            binder.forField(postalCode)
                    .withValidator(
                            name -> name.matches(REGEXPOSTALCODE),
                            "Csak szám megengedett!")
                    .bind(Client::getPostalCode, Client::setPostalCode);

            binder.forField(city)
                    .withValidator(
                            name -> name.matches(REGEXNAME),
                            "Csak betű megengedett!")
                    .bind(Client::getCity, Client::setCity);

        } catch (Exception e) {
            System.out.println("Address: " + e.getMessage());
        }

        //Id's binder
        try {
            binder.forField(personalID)
                    .withValidator(
                            name -> name.matches(REGEXPID),
                            "Formátum X-ÉÉHHNN-1234")
                    .bind(Client::getPersonalID, Client::setPersonalID);

            binder.forField(taxID)
                    .withValidator(
                            name -> name.matches(REGEXTAXNUMBER),
                            "Csak szám megengedett! (10 karakter)")
                    .bind(Client::getTaxID, Client::setTaxID);

            binder.forField(socialSecurityCard)
                    .withValidator(
                            name -> name.matches(REGEXTAJ),
                            "Csak szám megengedett! (9 karakter)")
                    .bind(Client::getSocialSecurityCard, Client::setSocialSecurityCard);

            binder.forField(cardId)
                    .withValidator(
                            name -> name.matches(REGEXCARD),
                            "Formátum 123456AA")
                    .bind(Client::getCardId, Client::setCardId);

            binder.forField(addressCardID)
                    .withValidator(
                            name -> name.matches(REGEXCARD),
                            "Formátum 123456AA")
                    .bind(Client::getAddressCardID, Client::setAddressCardID);

        } catch (Exception e) {
            System.out.println("ID's: " + e.getMessage());
        }

        //Contact info binder
        binder.forField(phoneNumber)
                .withValidator(new RegexpValidator("Formátum XX1234567!",REGEXTEL)
)
                .bind(Client::getPhoneNumber, Client::setPhoneNumber);

        binder.forField(email)
                .withValidator(new EmailValidator(
                        "Nem jó email formátum!"))
                .bind(Client::getEmail, Client::setEmail);




        try {
            binder.bindInstanceFields(this);
            binder.setBean(null);
        } catch (Exception e) {
            System.out.println("Binder: " + e.getMessage());
        }


        //Click!
        create.addClickListener(event -> createClicked());
        confirmButton.addClickListener(event -> saveClicked());

//        cancelButton.addClickListener(event -> createClicked());

        getContent().add(dialogBaseLayout, buttonLayout);
    }

    void saveClicked() {
        try {
            binder.readBean(client);
            clientRepository.create(client);
            Notification.show("Mentve!");
        } catch (Exception e) {
            System.out.println("Bind Hiba" + e.getMessage());
            Notification.show("A Vezetéknév és a Keresztnév nem lehet üres!");
        }
    }

    void createClicked() {
        grid.asSingleSelect().clear();
        setClient(new Client());
    }

    void setClient(Client client) {
        this.client = client;
        personalDataLayout.setEnabled(client != null);
        addressDataLayout.setEnabled(client != null);
        idDataLayout.setEnabled(client != null);
        binder.setBean(client);
    }
}
