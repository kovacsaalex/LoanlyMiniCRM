package com.example.demo.view;

import com.example.demo.pojo.Client;
import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
class testView extends Composite<VerticalLayout>{

    private final ClientRepository clientRepository;
    private Dialog dialog = new Dialog();
    private Grid<Client> grid = new Grid<>();
    private Button cancelButton = new Button("Bezárás");

    testView(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        AddClient addClient = new AddClient(clientRepository);

        //Layouts
        VerticalLayout navBar = new VerticalLayout();
        VerticalLayout content = new VerticalLayout();
        VerticalLayout contentVerticalLayout = new VerticalLayout();
        HorizontalLayout center = new HorizontalLayout();

        //NavBar Buttons
        Button ltpButton = new Button("LTP", new Icon(VaadinIcon.PIGGY_BANK_COIN));
        ltpButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button homeButton = new Button("Kezdőlap", new Icon(VaadinIcon.DASHBOARD));
        homeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button clientButton = new Button("Ügyfelek", new Icon(VaadinIcon.USER));
        clientButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button loanButton = new Button("Hitelek", new Icon(VaadinIcon.PIGGY_BANK));
        loanButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button clientAddButton = new Button("Ügyfél hozzáadása", new Icon(VaadinIcon.PLUS_CIRCLE));

        
        getContent().setSizeFull();
        getContent().setPadding(false);
        getContent().setSpacing(false);
        center.setWidth("100%");
        center.getStyle().set("background-color", "#DCDCDC");

        navBar.setWidth("200px");
        navBar.getStyle().set("background-color", "#DCDCDC");
        // navBar.getStyle().set("border", "1px solid #9E9E9E");
        content.setWidth("100%");
        content.getStyle().set("background-color", "#DCDCDC");
        contentVerticalLayout.getStyle().set("background-color", "lightgreen");
        getContent().expand(contentVerticalLayout);

        // Navbar
        H1 name = new H1("Loan(ly)");
        name.getStyle().set("color", "blue");
         // clientText.setHeight("200px");
        // header.add(navBar);
        // navBar.setAlignItems(Alignment.CENTER);
        navBar.add(name, homeButton, clientButton, loanButton, ltpButton);



        clientButton.addClickListener(event -> {
            content.removeAll();
            updateGrid();
            content.add(grid, clientAddButton);

        });
        homeButton.addClickListener(event -> {
            content.removeAll();

        });

        content.add();
        center.add(navBar, content);
//	        center.setFlexGrow(1, navBar);
        // content.setAlignSelf(Alignment.END, footer);

        getContent().add(center);
        getContent().expand(center);

        clientAddButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        clientAddButton.addClickListener(event -> {
                    dialog.add(addClient.getContent());
                    dialog.open();
                    addClient.createClicked();
                    dialog.add(cancelButton);

                }

        );
        cancelButton = new Button("Bezárás!", event -> {

            try {
              dialog.close();
              updateGrid();
         //       messageLabel.setText("Cancelled...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        grid.addColumn(Client::getId).setHeader("Id").setWidth("60px");
        grid.addColumn(Client::getFirstname).setHeader("Vezetéknév")
                .setWidth("110px")
                .setResizable(true);
        grid.addColumn(Client::getLastname).setHeader("Keresztnév")
                .setWidth("110px")
                .setResizable(true);
        grid.addColumn(Client::getPhoneNumber).setHeader("Mobil")
        .setResizable(true);
        grid.addColumn(Client::getEmail).setHeader("E-mail")
        .setResizable(true);
        grid.addColumn(Client::getMaidenName).setHeader("Leánykori Név")
        .setResizable(true);
        grid.addColumn(Client::getMotherName).setHeader("Anyja neve")
        .setResizable(true);
        grid.addColumn(Client::getBirthPlace).setHeader("Születési hely")
        .setResizable(true);
        grid.addColumn(Client::getBirthDate).setHeader("Születési idő")
        .setResizable(true);
        grid.addColumn(Client::getPersonalID).setHeader("Személyi szám")
        .setResizable(true);
        grid.addColumn(Client::getTaxID).setHeader("Adószám")
        .setResizable(true);
        grid.addColumn(Client::getSocialSecurityCard).setHeader("Taj szám")
        .setResizable(true);
        grid.addColumn(Client::getCardId).setHeader("Taj szám")
        .setResizable(true);
        grid.addColumn(Client::getAddressCardID).setHeader("Taj szám")
        .setResizable(true);
        grid.addColumn(Client::getPostalCode).setHeader("Irányítószám")
        .setResizable(true);
        grid.addColumn(Client::getAddress).setHeader("Lakcím")
        .setResizable(true);
        grid.addColumn(Client::getMovingTime).setHeader("Mióta lakik ott")
        .setResizable(true);


        //grid.addSelectionListener(event -> setClient(grid.asSingleSelect().getValue()));
        //grid.setVerticalScrollingEnabled(true);
        //grid.setWidth("1500px");
        updateGrid();

        
        clientButton.addClickListener(event -> {
            content.add(grid, clientAddButton);

        });

        homeButton.addClickListener(event -> {
            content.removeAll();

        });

    }


    private void updateGrid() {
        grid.setItems(clientRepository.findAll());
    }


}
