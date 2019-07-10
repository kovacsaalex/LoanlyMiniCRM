package com.example.demo.view;


import com.example.demo.model.Loan;
import com.example.demo.service.Repository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("loans")
public class LoanView extends Composite<VerticalLayout> {
    private Repository repository;
    private Grid<Loan> grid = new Grid<>();
    private Loan loan;

    Binder<Loan> binder = new Binder<>(Loan.class);

    VerticalLayout content = new VerticalLayout();
    HorizontalLayout beforeFooter = new HorizontalLayout();
    VerticalLayout dialogBaseLayout = new VerticalLayout();
    HorizontalLayout loanDataLayout = new HorizontalLayout();
    HorizontalLayout addressDataLayout = new HorizontalLayout();
    HorizontalLayout houseDataLayout = new HorizontalLayout();
    private HorizontalLayout buttonLayout = new HorizontalLayout();

    private Dialog dialog = new Dialog();
    Dialog alert = new Dialog();

    private H3 dialogLabelText = new H3("Új hitel adatai");

    ComboBox<String> comboBox = new ComboBox<>("Hitel Típusa");
    private TextField amount = new TextField("Hitel Összege");
    private DatePicker loanStart = new DatePicker("Hitel kezdete");
    private DatePicker loanEnd = new DatePicker("Hitel vége");
    private TextField loanTerm = new TextField("Futamidő");
    private TextField interestRate = new TextField("Kamatláb");

    private TextField idNumber = new TextField("Iranyítószám");
    private TextField postalCode = new TextField("Iranyítószám");
    private TextField city = new TextField("Város");
    private TextField address = new TextField("Cím");

    private TextField size = new TextField("Méret");
    private TextField value = new TextField("Értéke");
    ComboBox<String> comboBoxStatus = new ComboBox<>("Állapot");



    public LoanView(Repository repository ) {
        this.repository=repository;

        comboBox.setItems("Ingatlancélú", "Építési", "Kiváltás",
                "Szabad felhasználású");

        comboBoxStatus.setItems("Elkészült", "Építés alatt");

       // loanTerm.setPlaceholder(String.valueOf(loanStart.getValue())); ??

        //Grid Loan
        grid.addColumn(Loan::getLoanId).setHeader("Id").setWidth("60px");
        grid.addColumn(Loan::getLoanType).setHeader("Hitel Típusa").setWidth("120px").setResizable(true);
        grid.addColumn(Loan::getAmount).setHeader("Hitel összege").setWidth("120px").setResizable(true);
        grid.addColumn(Loan::getLoanStart).setHeader("Hitel kezdete").setWidth("120px").setResizable(true);
        grid.addColumn(Loan::getLoanEnd).setHeader("Hitel lejárata").setWidth("120px").setResizable(true);
        grid.addColumn(Loan::getLoanTerm).setHeader("Futamidő").setWidth("100px").setResizable(true);
        grid.addColumn(Loan::getInterestRate).setHeader("Kamatláb (%)").setWidth("100px").setResizable(true);
        grid.addColumn(Loan::getIdNumber).setHeader("HRSZ").setWidth("100px").setResizable(true);
        grid.addColumn(Loan::getPostalCode).setHeader("Irányíószám").setWidth("80px").setResizable(true);
        grid.addColumn(Loan::getCity).setHeader("Város").setWidth("100px").setResizable(true);
        grid.addColumn(Loan::getAddress).setHeader("Cím").setWidth("100px").setResizable(true);
        grid.addColumn(Loan::getSize).setHeader("Méret").setWidth("80px").setResizable(true);
        grid.addColumn(Loan::getValue).setHeader("Értéke").setHeader("100px").setResizable(true);
        grid.addColumn(Loan::getStatus).setHeader("Készültségi foka").setWidth("100px").setResizable(true);
        grid.addSelectionListener(event -> setLoan(grid.asSingleSelect().getValue()));
        updateGrid();

        //Buttons

        Button loanAddButton = new Button("Hitel hozzáadása", new Icon(VaadinIcon.PLUS_CIRCLE));
        loanAddButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button deleteButton = new Button("Hitel Törlése", new Icon(VaadinIcon.MINUS_CIRCLE));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

        Button loanEditButton = new Button("Hitel módosítása", new Icon(VaadinIcon.PENCIL));
        loanEditButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button loanAddClientButton = new Button("Hitel hozzáadása ügyfélhez", new Icon(VaadinIcon.PLUS_CIRCLE));
        loanAddClientButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_SUCCESS);

        Button cancelButton = new Button("Bezárás");

        //Clicklisteners

        loanAddButton.addClickListener(event -> {
           // dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);
            dialog.add(dialogBaseLayout,buttonLayout);
            dialog.open();
            createClicked();
            //   dialog.add(cancelButton);
        });


        // Layouts Adding
        loanDataLayout.add(comboBox,amount,loanStart,loanEnd,loanTerm,interestRate);
        addressDataLayout.add(idNumber,postalCode,city,address);
        houseDataLayout.add(size,value,comboBoxStatus);
        dialogBaseLayout.add(dialogLabelText,loanDataLayout, addressDataLayout,houseDataLayout);
        beforeFooter.add(loanAddButton,deleteButton,loanEditButton,loanAddClientButton);
        content.setSizeFull();
        content.setWidth("100%");
        content.expand();
        content.add(grid);

        getContent().add(content);

    }

    private void setLoan(Loan loan) {
        this.loan=loan;
        loanDataLayout.setEnabled(loan != null);
        addressDataLayout.setEnabled(loan != null);//
        binder.setBean(loan);
    }

    private void updateGrid() {
        try {
            grid.setItems(repository.findAllLoans());
        } catch (NullPointerException e) {
            System.out.println("Loan findAll hiba! "+e.getLocalizedMessage());
        }
    }
    void createClicked() {
        grid.asSingleSelect().clear();
        setLoan(new Loan());
    }

}
