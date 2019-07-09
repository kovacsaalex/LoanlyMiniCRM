package com.example.demo.view;

import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)

public class BasicView extends Composite<VerticalLayout> {

    private final ClientRepository clientRepository;


    public BasicView(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        ClientView clientView = new ClientView(clientRepository);

        // Layouts HOME
        HorizontalLayout header = new HorizontalLayout();
        VerticalLayout navBar = new VerticalLayout();
        VerticalLayout content = new VerticalLayout();
        VerticalLayout contentVerticalLayout = new VerticalLayout();
        HorizontalLayout center = new HorizontalLayout();
        HorizontalLayout headerComponentsH = new HorizontalLayout();
        VerticalLayout headerComponentsV = new VerticalLayout();
        HorizontalLayout beforeFooter = new HorizontalLayout();
        HorizontalLayout footer = new HorizontalLayout();

        getContent().setSizeFull();
        getContent().setPadding(false);
        getContent().setSpacing(false);
        center.setWidth("100%");
        header.setWidth("100%");
        header.setPadding(true);
        header.setHeight("80px");
        header.getStyle().set("background-color", "#444444");
        content.setWidth("100%");
        getContent().expand(contentVerticalLayout);
        beforeFooter.setWidth("100%");

        // Texts
        H3 name = new H3("LoanLy");
        name.getStyle().set("color", "white");
        H3 headerH3 = new H3("Welcome");
        Text welcomeText = new Text("A program hitelügyintézőknek készült ügyfélnyilvántartó. // \n"
                + "The program is a customer registry for financial and loan experts.");
        Text text = new Text("Az alábbi tervezett funkciói lesznek: // The following planned features will be:");

        // Checkbox
        Checkbox checkbox1 = new Checkbox();
        checkbox1.setLabel(
                "Ügyfelek, a hozzájuk kapcsolódó hitelek és LTP-k nyilvántartása // Registration of clients, related loans and LTPs");
        checkbox1.setValue(false);
        Checkbox checkbox2 = new Checkbox();
        checkbox2.setLabel("Adatok importálása egy fomrázott Excel fájlból // Import data from a special Excel file");
        checkbox2.setValue(false);
        Checkbox checkbox3 = new Checkbox();
        checkbox3.setLabel("A már bevitt adatok alapján hitelkérelmek kitöltése // \n"
                + "Fill out loan applications based on data you have already entered");
        checkbox3.setValue(false);
        Checkbox checkbox4 = new Checkbox();
        checkbox4.setLabel("OTP Bank");
        checkbox4.setValue(false);
        Checkbox checkbox5 = new Checkbox();
        checkbox5.setLabel("CIB Bank");
        checkbox5.setValue(false);

        // Buttons
        Button ltpButton = new Button("LTP", new Icon(VaadinIcon.PIGGY_BANK_COIN));
        ltpButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_PRIMARY);

        Button homeButton = new Button("Kezdőlap", new Icon(VaadinIcon.BAR_CHART));
        homeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_PRIMARY);

        Button clientButton = new Button("Ügyfelek", new Icon(VaadinIcon.GROUP));
        clientButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_PRIMARY);

        Button loanButton = new Button("Hitelek", new Icon(VaadinIcon.MONEY));
        loanButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_PRIMARY);

        // Button ClickListeners

        clientButton.addClickListener(event -> {
            content.removeAll();
            content.add(clientView);
        //    updateGrid();
         //   beforeFooter.add(clientAddButton, clientEditButton, deleteButton);
        //    content.add( clientView.getContent());
        });

        homeButton.addClickListener(event -> {
            content.removeAll();
            content.add(headerH3, welcomeText, text, checkbox1, checkbox2, checkbox3, checkbox4, checkbox5);
        });

        loanButton.addClickListener(event -> {
            content.removeAll();
        });

        // Add components
        headerComponentsV.add(headerComponentsH);
        headerComponentsH.add(homeButton, clientButton, loanButton, ltpButton);
        headerComponentsV.setAlignItems(FlexComponent.Alignment.CENTER);
        header.add(name, headerComponentsV);
        footer.setWidth("100%");
        footer.setPadding(true);
        footer.setHeight("50px");
        footer.getStyle().set("background-color", "#444444");
        content.add(headerH3, welcomeText, text, checkbox1, checkbox2, checkbox3, checkbox4, checkbox5);
        center.add(content);
        getContent().add(header, center, footer);
        getContent().expand(center);
    }
}
