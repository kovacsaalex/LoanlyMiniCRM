package com.example.demo.view;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;


import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends VerticalLayout {
    Binder<Contact> binder = new Binder<>();
    FormLayout nameLayout = new FormLayout();

    public MainView() {
        //   headerLayout();
        navBarLayout();
        //   formLayout();
        //   nameLayout();
        //   birthAndMotherNameLayout();
        //   addressLayout();



    }

    private void navBarLayout() {
        VerticalLayout header = new VerticalLayout();
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        VerticalLayout navBar = new VerticalLayout();
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout center = new HorizontalLayout();
        setPadding(false);
        setSpacing(false);
        center.setWidth("100%");
        center.getStyle().set("background-color", "lightgrey");
        navBar.setWidth("200px");
        navBar.getStyle().set("background-color", "white");
        navBar.getStyle().set("border", "1px solid #9E9E9E");
        content.setWidth("100%");
        content.getStyle().set("background-color", "lightblue");
        H1 name = new H1("Loan(ly)");
        name.getStyle().set("color", "blue");
        Button homeText = new Button("Dashboard");
        homeText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button clientText = new Button("Clients");
        clientText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        //
        TextField firstNameField = new TextField();
        firstNameField.setLabel("First name!");
        firstNameField.setPlaceholder("John");
        firstNameField.setRequiredIndicatorVisible(true);
        TextField middleNameField = new TextField();
        middleNameField.setLabel("Middle name");
        middleNameField.setPlaceholder(" ");
        TextField lastNameField = new TextField();
        lastNameField.setLabel("Last name");
        lastNameField.setPlaceholder("Doe");

        nameLayout.setSizeFull();

        nameLayout.add(firstNameField,middleNameField, lastNameField);

        nameLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep( "0",1),
                new FormLayout.ResponsiveStep("21em", 2),
                new FormLayout.ResponsiveStep("22em", 3)
        );
        //add(nameLayout);

        //


        Button loanText = new Button("Loans");
        loanText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button ltpText = new Button("LTP", new Icon(VaadinIcon.COINS));
        ltpText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        //clientText.setHeight("200px");
        header.add(navBar);
        navBar.setAlignItems(Alignment.CENTER);
        navBar.add(name,homeText,clientText,loanText,ltpText);
        center.add(navBar);
        //center.setFlexGrow(1, navBar);
        add(center);
        expand(center);
        clientText.addClickListener(event -> center.add(nameLayout));
    }

    private void addressLayout() {
    }

    private void birthAndMotherNameLayout() {
    }

    private void nameLayout() {
    }


    public void headerLayout(){
        VerticalLayout header = new VerticalLayout();
        setSizeFull();
        setPadding(false);
        setSpacing(false);

//        header.getStyle().set("border", "1px solid #9E9E9E");
//        header.getStyle().set("background-color", "white");
//        header.setWidth("100%");
//        header.setHeight("50px");
//        header.setPadding(true);
//        header.setAlignItems(Alignment.CENTER);
//
//        Label title = new Label( "Customer Registration" );
//        title.getStyle().set("color", "black");
//        header.add(title);
        add(header);
    }

    public void formLayout (){

    }
}
