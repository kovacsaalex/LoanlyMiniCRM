package com.example.demo.view;

import com.example.demo.model.Loan;
import com.example.demo.service.LoanRepository;
import com.example.demo.service.Repository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("tt")
public class ttview extends Composite<VerticalLayout> {

    private LoanRepository loanRepository;
    private Repository repository;
    private Grid<Loan> grid = new Grid<>();
    private VerticalLayout form = new VerticalLayout();

  //  public ttview(LoanRepository loanRepository) {
  public ttview(Repository repository) {
     //   this.loanRepository = loanRepository;
      this.repository = repository;

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

        updateGrid();

        getContent().add(grid);
    }
    private void updateGrid() {
        try {
            grid.setItems(repository.findAllLoans());
        } catch (NullPointerException e) {
            System.out.println("Loan findAll hiba! "+e.getLocalizedMessage());
        }
    }


}
