package com.example.demo.view;

import com.example.demo.model.Client;
import com.example.demo.service.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.router.Route;

@Route("clients")
public class ClientView extends Composite<VerticalLayout> {
    private Grid<Client> grid = new Grid<>();
    private Client client;
    private final ClientRepository clientRepository;
    Binder<Client> binder = new Binder<>(Client.class);

    private Dialog dialog = new Dialog();
    Dialog alert = new Dialog();

    VerticalLayout content = new VerticalLayout();
    private HorizontalLayout personalDataLayout = new HorizontalLayout();
    private VerticalLayout dialogBaseLayout;
    private HorizontalLayout addressDataLayout;
    private HorizontalLayout idDataLayout;
    private HorizontalLayout contactDataLayout;
    private HorizontalLayout buttonLayout;
    HorizontalLayout beforeFooter = new HorizontalLayout();

    public TextField firstname = new TextField("Vezetéknév");
    public TextField lastname = new TextField("Keresztnév");
    public TextField maidenName = new TextField("Leánykori név");
    public TextField motherName = new TextField("Anyja neve");
    public TextField birthPlace = new TextField("Születési hely");
    public DatePicker birthDate = new DatePicker("Születési idő");

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
    Button editSaveButton = new Button("Módosítás");

    final String REGEXNAME = "([A-Za-zaáeéiíoóöőuúüűAÁEÉIÍOÓÖŐUÚÜŰ .-]+)";
    final String REGEXPOSTALCODE = "\\d{4}$";
    final String REGEXPID = "\\d{1}-\\d{6}-\\d{4}$";
    final String REGEXTAXNUMBER = "\\d{10}$";
    final String REGEXCARD = "^\\d{6}[A-Z]{2}$";
    final String REGEXTAJ = "^\\d{9}$";
    final String REGEXTEL = "^(\\d{2})(\\d{7})$";

    public ClientView(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

        dialogBaseLayout = new VerticalLayout();
        addressDataLayout = new HorizontalLayout();
        idDataLayout = new HorizontalLayout();
        buttonLayout = new HorizontalLayout();
        contactDataLayout = new HorizontalLayout();

        firstname.setRequired(true);
        lastname.setRequired(true);

        grid.addColumn(Client::getId).setHeader("Id").setWidth("60px");
        grid.addColumn(Client::getFirstname).setHeader("Vezetéknév").setWidth("110px").setResizable(true);
        grid.addColumn(Client::getLastname).setHeader("Keresztnév").setWidth("110px").setResizable(true);
        grid.addColumn(Client::getPhoneNumber).setHeader("Mobil").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getEmail).setHeader("E-mail").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getMaidenName).setHeader("Leánykori Név").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getMotherName).setHeader("Anyja neve").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getBirthPlace).setHeader("Születési hely").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getBirthDate).setHeader("Születési idő").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getPersonalID).setHeader("Személyi szám").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getTaxID).setHeader("Adószám").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getSocialSecurityCard).setHeader("Taj szám").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getCardId).setHeader("Személyi ig. szám").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getAddressCardID).setHeader("Lakcímkártya szám").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getPostalCode).setHeader("Irányítószám").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getAddress).setHeader("Lakcím").setWidth("150px").setResizable(true);
        grid.addColumn(Client::getMovingTime).setHeader("Mióta lakik ott").setWidth("150px").setResizable(true);

        grid.addSelectionListener(event -> setClient(grid.asSingleSelect().getValue()));
        updateGrid();

        //Buttons

        Button clientAddButton = new Button("Ügyfél hozzáadása", new Icon(VaadinIcon.PLUS_CIRCLE));
        clientAddButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button deleteButton = new Button("Ügyfél Törlése", new Icon(VaadinIcon.MINUS_CIRCLE));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        //deleteButton.getStyle().set("color", "white");

        Button clientEditButton = new Button("Ügyfél módosítása", new Icon(VaadinIcon.PENCIL));
        clientEditButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button cancelButton = new Button("Bezárás");


        // Button ClickListeners

        clientAddButton.addClickListener(event -> {
            dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);
            dialog.add(dialogBaseLayout,buttonLayout);
            dialog.open();
            createClicked();
         //   dialog.add(cancelButton);
        });

        cancelButton.addClickListener(event -> {

            try {
                grid.asSingleSelect().clear();
                updateGrid();
                dialog.close();

                // messageLabel.setText("Cancelled...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        deleteButton.addClickListener(event -> {
            alert.removeAll();
            VerticalLayout alertLayout = new VerticalLayout();
            alertLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            Text alertText = new Text("A törlés nem vonható vissza!");
            Button alertButton = new Button(" Törlés ");
            alertButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
            Button alertcancelButton = new Button(" Mégsem! ");
            alertcancelButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
            alertLayout.add(alertText, alertButton, alertcancelButton);
            alert.add(alertLayout);
            alert.setCloseOnEsc(false);
            alert.setCloseOnOutsideClick(false);
            alert.open();
            alertButton.addClickListener(event1 -> {
                setClient(grid.asSingleSelect().getValue());
                alert.close();
                delete();
                updateGrid();
            });
            alertcancelButton.addClickListener(event1 -> alert.close());

        });

        clientEditButton.addClickListener(event -> {
            update();
        });

        editSaveButton.addClickListener(event -> {
            clientRepository.update(client);
            Notification.show("Módosítva!");
        });


        // Names binder
        try {
            binder.forField(firstname).asRequired("Kötelező")
                    .withValidator(name -> name.length() >= 3, "Kérem adja meg a teljes nevét!")
                    .withValidator(name -> name.matches(REGEXNAME), "Csak betű megengedett!")
                    .bind(Client::getFirstname, Client::setFirstname);

            binder.forField(lastname).asRequired("Kötelező")
                    .withValidator(name -> name.length() >= 3, "Kérem adja meg a teljes nevét!")
                    .withValidator(name -> name.matches(REGEXNAME), "Csak betű megengedett!")
                    .bind(Client::getLastname, Client::setLastname);

            binder.forField(maidenName).asRequired("Kötelező")
                    .withValidator(name -> name.matches(REGEXNAME), "Csak betű megengedett!")
                    .bind(Client::getMaidenName, Client::setMaidenName);

            binder.forField(motherName).asRequired("Kötelező")
                    .withValidator(name -> name.matches(REGEXNAME), "Csak betű megengedett!")
                    .bind(Client::getMotherName, Client::setMotherName);

            binder.forField(birthPlace).withValidator(name -> name.matches(REGEXNAME), "Csak betű megengedett!")
                    .bind(Client::getBirthPlace, Client::setBirthPlace);

        } catch (Exception e) {
            System.out.println("Name&Birth: " + e.getMessage());
        }

        // Address Binder
        try {
            binder.forField(postalCode).withValidator(name -> name.matches(REGEXPOSTALCODE), "Csak szám megengedett!")
                    .bind(Client::getPostalCode, Client::setPostalCode);

            binder.forField(city).withValidator(name -> name.matches(REGEXNAME), "Csak betű megengedett!")
                    .bind(Client::getCity, Client::setCity);

        } catch (Exception e) {
            System.out.println("Address: " + e.getMessage());
        }

        // Id's binder
        try {
            binder.forField(personalID).withValidator(name -> name.matches(REGEXPID), "Formátum X-ÉÉHHNN-1234")
                    .bind(Client::getPersonalID, Client::setPersonalID);

            binder.forField(taxID)
                    .withValidator(name -> name.matches(REGEXTAXNUMBER), "Csak szám megengedett! (10 karakter)")
                    .bind(Client::getTaxID, Client::setTaxID);

            binder.forField(socialSecurityCard)
                    .withValidator(name -> name.matches(REGEXTAJ), "Csak szám megengedett! (9 karakter)")
                    .bind(Client::getSocialSecurityCard, Client::setSocialSecurityCard);

            binder.forField(cardId).withValidator(name -> name.matches(REGEXCARD), "Formátum 123456AA")
                    .bind(Client::getCardId, Client::setCardId);

            binder.forField(addressCardID).withValidator(name -> name.matches(REGEXCARD), "Formátum 123456AA")
                    .bind(Client::getAddressCardID, Client::setAddressCardID);

        } catch (Exception e) {
            System.out.println("ID's: " + e.getMessage());
        }

        // Contact info binder
        binder.forField(phoneNumber).withValidator(new RegexpValidator("Formátum XX1234567", REGEXTEL))
                .bind(Client::getPhoneNumber, Client::setPhoneNumber);

        binder.forField(email).withValidator(new EmailValidator("Nem jó email formátum!")).bind(Client::getEmail,
                Client::setEmail);

        try {
            binder.bindInstanceFields(this);
            binder.setBean(null);
        } catch (Exception e) {
            System.out.println("Binder: " + e.getMessage());
        }


        personalDataLayout.add(firstname, lastname, maidenName, motherName, birthPlace, birthDate); // OK
        addressDataLayout.add(postalCode, city, address, movingTime); // OK
        idDataLayout.add(cardId, addressCardID, personalID, taxID, socialSecurityCard); // OK
        contactDataLayout.add(phoneNumber, email);
        buttonLayout.add(confirmButton,create,cancelButton,editSaveButton);
        dialogBaseLayout.add(dialogLabelText, personalDataLayout, addressDataLayout, idDataLayout, contactDataLayout);

        beforeFooter.add(clientAddButton,clientEditButton,deleteButton);
        content.setSizeFull();
        content.setWidth("100%");
        content.expand();
        //content.getStyle().set("background-color", "lightgrey");
        content.add(grid);

        // Click!
        create.addClickListener(event -> createClicked());
        confirmButton.addClickListener(event -> saveClicked());


        getContent().add(content);
    }

    void saveClicked() {
        binder.readBean(client);
        try {
          if (client.getId()== null){
              clientRepository.create(client);
              Notification.show("Mentve!");
          }else {
         //     clientRepository.update(client);
              Notification.show("Módosítva!");
          }
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
    private void updateGrid() {
        grid.setItems(clientRepository.findAll());
    }
    void delete() {
        try {
            // grid.asSingleSelect();
            binder.readBean(client);
            clientRepository.delete(client);
            Notification.show("Törölve!");
            grid.asSingleSelect().clear();
            binder.setBean(null);
            updateGrid();
        } catch (Exception e) {
            System.out.println("Törlés hiba!" + e.getMessage());

        }
    }

    void update(){
        binder.readBean(client);

        //**********************
        // Names binder
        try {
            binder.forField(firstname).asRequired("Kötelező")

                    .bind(Client::getFirstname, Client::setFirstname);

            binder.forField(lastname).asRequired("Kötelező")

                    .bind(Client::getLastname, Client::setLastname);

            binder.forField(maidenName).asRequired("Kötelező")

                    .bind(Client::getMaidenName, Client::setMaidenName);

            binder.forField(motherName).asRequired("Kötelező")

                    .bind(Client::getMotherName, Client::setMotherName);

            binder.forField(birthPlace)
                    .bind(Client::getBirthPlace, Client::setBirthPlace);

        } catch (Exception e) {
            System.out.println("Name&Birth: " + e.getMessage());
        }


//
//        List<Registration> clientList = new ArrayList<>();
//        clientList.add(grid.addSelectionListener(event -> setClient(grid.asSingleSelect().getValue())));
//
//        for (Registration value:clientList){
//            System.out.println("Hahó!"+value.toString());
//        }
        //dialog.setCloseOnEsc(false);


        dialog.setCloseOnOutsideClick(false);
    //    dialog.add(addClient.getContent());
        dialog.add(dialogBaseLayout,buttonLayout);
        dialog.open();
   //     dialog.add(cancelButton);


    }
}