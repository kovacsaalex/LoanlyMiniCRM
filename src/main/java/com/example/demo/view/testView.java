package com.example.demo.view;

import java.awt.Event;

import com.example.demo.pojo.Client;
import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
class testView extends Composite<VerticalLayout>{

    private final ClientRepository clientRepository;
    private Dialog dialog = new Dialog();
    Dialog alert = new Dialog();
    private Grid<Client> grid = new Grid<>();
    private Client client;
    Binder<Client> binder = new Binder<>(Client.class);
    private Button cancelButton = new Button("Bezárás");



    testView(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        AddClient addClient = new AddClient(clientRepository);

        //Layouts
        HorizontalLayout header = new HorizontalLayout();
        VerticalLayout navBar = new VerticalLayout();
        VerticalLayout content = new VerticalLayout();
        VerticalLayout contentVerticalLayout = new VerticalLayout();
        HorizontalLayout center = new HorizontalLayout();
        HorizontalLayout headerComponentsH = new HorizontalLayout();
        VerticalLayout headerComponentsV = new VerticalLayout();
        HorizontalLayout footer = new HorizontalLayout();

        //NavBar Buttons
        Button ltpButton = new Button("LTP", new Icon(VaadinIcon.PIGGY_BANK_COIN));
        ltpButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_PRIMARY);
        Button homeButton = new Button("Kezdőlap", new Icon(VaadinIcon.BAR_CHART));
        homeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_PRIMARY);
        Button clientButton = new Button("Ügyfelek", new Icon(VaadinIcon.GROUP));
        clientButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_PRIMARY);
        Button loanButton = new Button("Hitelek", new Icon(VaadinIcon.MONEY));
        loanButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_PRIMARY);

        Button clientAddButton = new Button("Ügyfél hozzáadása", new Icon(VaadinIcon.PLUS_CIRCLE));
        clientAddButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_CONTRAST);
        Button deleteButton = new Button("Törlés",new Icon(VaadinIcon.MINUS_CIRCLE));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        clientButton.getStyle().set("color", "white");
        
        H3 name = new H3("LoanLy");
        H3 headerH3 = new H3("Welcome");
        Text welcomeText = new Text("A program hitelügyintézőknek készült ügyfélnyilvántartó. // \n" + 
        		"The program is a customer registry for financial and loan experts.");
       
        
        Text text = new Text("Az alábbi tervezett funkciói lesznek: // The following planned features will be:");
        Checkbox checkbox1= new Checkbox();
        checkbox1.setLabel("Ügyfelek, a hozzájuk kapcsolódó hitelek és LTP-k nyilvántartása // Registration of clients, related loans and LTPs");
        checkbox1.setValue(false);
        Checkbox checkbox2= new Checkbox();
        checkbox2.setLabel("Adatok importálása egy fomrázott Excel fájlból // Import data from a special Excel file");
        checkbox2.setValue(false);
        Checkbox checkbox3= new Checkbox();
        checkbox3.setLabel("A már bevitt adatok alapján hitelkérelmek kitöltése // \n" + 
        		"Fill out loan applications based on data you have already entered");
        checkbox3.setValue(false);
        Checkbox checkbox4= new Checkbox();
        checkbox4.setLabel("OTP Bank");
        checkbox4.setValue(false);
        Checkbox checkbox5= new Checkbox();
        checkbox5.setLabel("CIB Bank");
        checkbox5.setValue(false);
        
        
        getContent().setSizeFull();
        getContent().setPadding(false);
        getContent().setSpacing(false);
        center.setWidth("100%");
        //center.getStyle().set("background-color", "#DCDCDC");
        
        header.setWidth("100%");
	    header.setPadding(true);
	    header.setHeight("80px");
	    //header.getStyle().set("background-color", "#444444");
	    header.getStyle().set("background-color", "#444444");
	    
	  
	    headerComponentsV.add(headerComponentsH);
	    headerComponentsH.add(homeButton, clientButton, loanButton, ltpButton);
	    headerComponentsV.setAlignItems(Alignment.CENTER);
	  
	   

        navBar.setWidth("200px");
        //navBar.getStyle().set("background-color", "#00ACED");
        //navBar.getStyle().set("background-color", "#DCDCDC");
        // navBar.getStyle().set("border", "1px solid #9E9E9E");
        content.setWidth("100%");
        //content.getStyle().set("background-color", "#DCDCDC");
        //contentVerticalLayout.getStyle().set("background-color", "lightgreen");
        getContent().expand(contentVerticalLayout);

        // Navbar
       
        name.getStyle().set("color", "white");
         // clientText.setHeight("200px");
        // header.add(navBar);
        // navBar.setAlignItems(Alignment.CENTER);
        //navBar.add(homeButton, clientButton, loanButton, ltpButton);
        
        header.add(name,headerComponentsV);
        
        footer.setWidth("100%");
	    footer.setPadding(true);
	    footer.setHeight("50px");
	    footer.getStyle().set("background-color", "#444444");

        

        clientButton.addClickListener(event -> {
            content.removeAll();
            updateGrid();
            content.add(grid,clientAddButton,deleteButton);

        });
     

        content.add(headerH3,welcomeText,text,checkbox1,checkbox2,checkbox3,checkbox4,checkbox5);
        center.add(content);
//	        center.setFlexGrow(1, navBar);
        // content.setAlignSelf(Alignment.END, footer);

        getContent().add(header,center,footer);
        getContent().expand(center);
        
        homeButton.addClickListener(event -> {
            content.removeAll();
            content.add(headerH3,welcomeText,text,checkbox1,checkbox2,checkbox3,checkbox4,checkbox5);

        });

        clientAddButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        clientAddButton.addClickListener(event -> {
            dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);
                    dialog.add(addClient.getContent());
                    dialog.open();
                    addClient.createClicked();
                    dialog.add(cancelButton);

                }

        );
        
        loanButton.addClickListener(event -> {
            content.removeAll();
            updateGrid();
            content.add(clientAddButton,deleteButton);

        });
        
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

        
//        clientButton.addClickListener(event -> {
//            content.add(grid, clientAddButton,deleteButton);
//
//        });

      

        deleteButton.addClickListener(event -> {
           alert.removeAll();
           VerticalLayout alertLayout = new VerticalLayout();
           Text alertText = new Text("A törlés nem vonható vissza!");
           Button alertButton = new Button(" Törlés ",new Icon(VaadinIcon.STOP));
           alertButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
           Button alertcancelButton = new Button(" Mégsem! ",new Icon(VaadinIcon.CHECK));
           alertcancelButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
           alertLayout.add(alertText,alertButton,alertcancelButton);
           alert.add(alertLayout);
           alert.setCloseOnEsc(false);
           alert.setCloseOnOutsideClick(false);
           alert.open();
           alertButton.addClickListener(event1 ->{
                setClient(grid.asSingleSelect().getValue());
                alert.close();
                delete();
                updateGrid();
            } );
            alertcancelButton.addClickListener(event1 -> alert.close());


   });

    }

    void setClient(Client client) {
        this.client = client;
        grid.setEnabled(client != null);
        binder.setBean(client);
    }

    private void updateGrid() {
        grid.setItems(clientRepository.findAll());
    }

    void delete(){
        try {
            //grid.asSingleSelect();
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


}
