package com.example.demo.view;

import com.example.demo.pojo.Client;
import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends Composite<VerticalLayout>{
	
	 private final ClientRepository clientRepository;
	    private Dialog dialog = new Dialog();
	    Dialog alert = new Dialog();
	    private Grid<Client> grid = new Grid<>();
	    private Client client;
	    Binder<Client> binder = new Binder<>(Client.class);
	    private Button cancelButton = new Button("Bezárás");
	    
	    public MainView(ClientRepository clientRepository) {
	    	
	    	  this.clientRepository = clientRepository;
	    	  
	          AddClient addClient = new AddClient(clientRepository);
	          
	        HorizontalLayout header = new HorizontalLayout();
	  	    VerticalLayout navBar = new VerticalLayout();
	  	    VerticalLayout content = new VerticalLayout();
	  	    HorizontalLayout center = new HorizontalLayout();
	  	    HorizontalLayout footer = new HorizontalLayout();
	  	    HorizontalLayout headerComponents = new HorizontalLayout();
	  	    VerticalLayout headerComponentsV = new VerticalLayout();
	  	    VerticalLayout footerComponentsV = new VerticalLayout();
	  	    
	  	   Button ltpButton = new Button("LTP", new Icon(VaadinIcon.PIGGY_BANK_COIN));
	        ltpButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_SUCCESS);
	        Button homeButton = new Button("Kezdőlap", new Icon(VaadinIcon.DASHBOARD));
	        homeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_CONTRAST);
	        Button clientButton = new Button("Ügyfelek", new Icon(VaadinIcon.GROUP));
	        clientButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_SUCCESS);
	        Button loanButton = new Button("Hitelek", new Icon(VaadinIcon.PIGGY_BANK));
	        loanButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY,ButtonVariant.LUMO_SUCCESS);

	        Button clientAddButton = new Button("Ügyfél hozzáadása", new Icon(VaadinIcon.PLUS_CIRCLE));
	        clientAddButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_CONTRAST);
	        Button deleteButton = new Button("Törlés",new Icon(VaadinIcon.MINUS_CIRCLE));
	        deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
	        clientButton.getStyle().set("color", "white");
	        
	        H2 name = new H2("Loan(ly)");
	        
	        getContent().setSizeFull();
	        getContent().setPadding(false);
	        getContent().setSpacing(false);
		    header.setWidth("100%");
		    header.setPadding(true);
		    header.setHeight("80px");
		    headerComponentsV.add(homeButton,clientButton,loanButton,ltpButton);
		    headerComponentsV.setAlignItems(Alignment.CENTER);
		    header.add(name,headerComponentsV);
		    //header.setAlignSelf(Alignment.END, headerH2);
		    header.getStyle().set("background-color", "#444444");
		  //  headerH5.getStyle().set("color", "white");
		    center.setWidth("100%");
		    navBar.setWidth("200px");
		  //  navBar.add(headerH2);
		    navBar.getStyle().set("background-color", "#00ACED");
		  //  headerH2.getStyle().set("color", "white");
		    content.setWidth("100%");
		  //  content.add(headerH3);
		    footer.setWidth("100%");
		    footer.setPadding(true);
		    
		    footerComponentsV.setWidth("100%");
		    footerComponentsV.setPadding(true);
		    footerComponentsV.getStyle().set("background-color", "purple");
		   // footerComponentsV.add(headerH4);
		    footerComponentsV.setAlignItems(Alignment.CENTER);
		    
		    footer.add(footerComponentsV);
		    footer.getStyle().set("background-color", "#444444");
		   // headerH4.getStyle().set("color", "white");
		    //footer.setAlignSelf(Alignment.CENTER, headerH4);
		    footer.setHeight("80px");

		    // Compose layout
		    center.add(navBar, content);
		    center.setFlexGrow(1, navBar);
		    getContent().add(header, center, footer);
		    getContent().expand(center);
		  
		
		}
}
