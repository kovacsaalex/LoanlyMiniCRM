package com.example.demo.view;

import com.example.demo.model.Loan;
import com.example.demo.service.LoanRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("loan")
public class LoanView  extends Composite<VerticalLayout> {
    private Grid<Loan> grid = new Grid<>();
    private Loan loan;
    private LoanRepository loanRepository;
    Binder<Loan> binder = new Binder<>(Loan.class);

    VerticalLayout content = new VerticalLayout();
    HorizontalLayout beforeFooter = new HorizontalLayout();

    public LoanView() {
    }

    public LoanView(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;


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

        //Buttons

        Button loanAddButton = new Button("Hitel hozzáadása", new Icon(VaadinIcon.PLUS_CIRCLE));
        loanAddButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button deleteButton = new Button("Hitel Törlése", new Icon(VaadinIcon.MINUS_CIRCLE));
        deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
        //deleteButton.getStyle().set("color", "white");

        Button loanEditButton = new Button("Hitel módosítása", new Icon(VaadinIcon.PENCIL));
        loanEditButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button loanAddClientButton = new Button("Hitel hozzáadása ügyfélhez", new Icon(VaadinIcon.PLUS_CIRCLE));
        loanAddClientButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_SUCCESS);


        Button cancelButton = new Button("Bezárás");


        beforeFooter.add(loanAddButton,deleteButton,loanEditButton,loanAddClientButton);
        content.setSizeFull();
        content.setWidth("100%");
        content.expand();
        content.add(grid);
        getContent().add(content);

    }

    private void setLoan(Loan loan) {
        this.loan=loan;
//        loanDataLayout.setEnabled(client != null);
//        addressDataLayout.setEnabled(client != null);//
        binder.setBean(loan);
    }

    private void updateGrid() {
        try {
            grid.setItems(loanRepository.findAll());
        } catch (Exception e) {
            System.out.println("Minden hitel listázása hiba!"+e.getLocalizedMessage());
        }
    }
}
