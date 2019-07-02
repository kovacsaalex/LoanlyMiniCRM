package com.example.demo.view;


import java.awt.Event;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.logging.log4j.message.Message;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;



@Route("old")
public class MainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	Binder<Contact> binder = new Binder<>();
//    FormLayout nameLayout = new FormLayout();
	LocalDate selectedDate = LocalDate.now();
 DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

    public MainView() {
   
    	mainLayout();
    	
    }

    private void mainLayout() {
        //VerticalLayout header = new VerticalLayout();
       
      

        VerticalLayout navBar = new VerticalLayout();
        VerticalLayout content = new VerticalLayout();
        //HorizontalLayout contentNameData = new HorizontalLayout();
        //HorizontalLayout contentAdressData = new HorizontalLayout();
        
        VerticalLayout contentVerticalLayout = new VerticalLayout();
        HorizontalLayout center = new HorizontalLayout();
        HorizontalLayout footer = new HorizontalLayout();
              
    
      
    
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        center.setWidth("100%");
        center.getStyle().set("background-color", "lightyellow");
        navBar.setWidth("200px");
        navBar.getStyle().set("background-color", "#DCDCDC");
        navBar.getStyle().set("border", "1px solid #9E9E9E");
        content.setWidth("100%");
        content.getStyle().set("background-color", "white");
//        content.setAlignItems(Alignment.END);
//        contentVerticalLayout.setWidth("50%");
        contentVerticalLayout.getStyle().set("background-color", "lightgreen");
        expand(contentVerticalLayout);
        footer.getStyle().set("background-color", "green");
        footer.getStyle().set("border", "1px solid #9E9E9E");
        footer.setWidth("100%");
        footer.setPadding(true);
        
        
        
        
        //Navbar
        
		        H1 name = new H1("Loan(ly)");
		        name.getStyle().set("color", "blue");
		        Button homeText = new Button("Kezdőlap");
		        homeText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		        Button clientText = new Button("Ügyfelek");
		        clientText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		    
		        Button loanText = new Button("Hitelek");
		        loanText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		        Button ltpText = new Button("LTP", new Icon(VaadinIcon.COINS));
		        ltpText.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		        //clientText.setHeight("200px");
		        //header.add(navBar);
		        navBar.setAlignItems(Alignment.CENTER);
		        navBar.add(name,homeText,clientText,loanText,ltpText);
        
        
		        // Create a grid bound to the list
		        Grid<Contact> grid = new Grid<>();
		        grid.setItems(new Contact("Alex",selectedDate));
		        grid.addColumn(Contact::getFirstName).setHeader("Name");
		        grid.addColumn(person -> person.selectedDate.format(formatter))
		                .setHeader("Year of birth");

        
        
        
        
      
        
//        content.setAlignItems(Alignment.CENTER);
//        Text centerText = new Text("centerText");
//        content.setSpacing(true);
//        incontent.add(centerText);
        
        // Client Add fields

		        TextField firstNameField = new TextField();
		        firstNameField.setLabel("Vezetéknév");
		        firstNameField.setRequiredIndicatorVisible(true);
		      
		       
		        
		//        firstNameField.setMaxWidth("200px");
		        
		        TextField lastNameField = new TextField();
		        lastNameField.setLabel("Keresztnév");
		        lastNameField.setRequiredIndicatorVisible(true);
		//        lastNameField.setMaxWidth("200px");
		        //lastNameField.getStyle().set("margin_left", "auto");
		        
		        TextField motherNameField = new TextField();
		        motherNameField.setLabel("Anyja neve");
		        
		        TextField birthPlaceField = new TextField();
		        birthPlaceField.setLabel("Születési hely");
		        
		        DatePicker birthDatePicker = new DatePicker();
		        birthDatePicker.setLabel("Születési idő");
		        birthDatePicker.setLocale(new Locale("hu"));
		        birthDatePicker.addValueChangeListener(event -> {
		            selectedDate = event.getValue();
		        });
		        
		        NumberField postalCode = new NumberField("Iranyítószám");
		        TextField city = new TextField();
		        city.setLabel("Város");
		        TextField address = new TextField();
		        address.setMinWidth("300px");
		        address.setLabel("Cím");
		        
		        TextField personalID = new TextField("Személyi szám");
		        personalID.setWidth("150px");
		        
		        TextField taxID = new TextField("Adószám");
		        taxID.setWidth("150px");
		        TextField socialSecurityCard = new TextField("Taj szám");
		        socialSecurityCard.setWidth("150px");		        
		        TextField cardId = new TextField("Személyi igazolvány szám");
		        cardId.setWidth("200px");
		        TextField addressCardID = new TextField("Lakcímkártya száma");
		        addressCardID.setWidth("150px");
		        
		        
		        
		        
		       
		        
		   //Dialog
		        Dialog dialog = new Dialog();
		        Div dialogText1 = new Div();
		        dialogText1.addClassName("my-style");
		        
		        Text dialogLabelText = new Text("Új ügyfél adatai");
		        //Text personalDataText = new Text("Személyes adatok");
		        //Text addressText = new Text("Lakhely adatok");
		        


		        dialog.setCloseOnEsc(false);
		        dialog.setCloseOnOutsideClick(false);  
		        
		        Label messageLabel = new Label();
		        
		        Button confirmButton = new Button("Mentés", event -> {
		        	
		        	grid.setItems(new Contact(firstNameField.getValue(),selectedDate));
				        Notification.show(firstNameField.getValue());
		        	
		            messageLabel.setText("Confirmed!");
		            dialog.close();
		        });
		        Button cancelButton = new Button("Mégsem", event -> {
		        	
		            messageLabel.setText("Cancelled...");
		            dialog.close();
		        });
		        VerticalLayout dialogBaseLayout = new VerticalLayout();
		        HorizontalLayout personalDataLayout = new HorizontalLayout();
		        HorizontalLayout addressDataLayout = new HorizontalLayout();
		        HorizontalLayout idDataLayout = new HorizontalLayout();
		        HorizontalLayout buttonLayout = new HorizontalLayout();
		        //dialogBaseLayout.setAlignItems(Alignment.CENTER);
		        personalDataLayout.add(firstNameField,lastNameField,birthPlaceField,birthDatePicker);
		        addressDataLayout.add(postalCode,city,address);
		        idDataLayout.add(cardId,addressCardID,personalID,taxID,socialSecurityCard);
		        buttonLayout.add(confirmButton,cancelButton);
		       
		     
		        dialogBaseLayout.add(dialogLabelText,personalDataLayout,addressDataLayout,idDataLayout,buttonLayout );
		      
		        dialog.add(dialogBaseLayout);
		        dialog.setWidth("900px");
		        dialog.setHeight("500px");
		        
		        Button dialogButton = new Button("Új ügyfél hozzáadása");
		        
		        dialogButton.addClickListener(event -> dialog.open());
		        
		       
		        
		        
		        
		        
		   //
		        
       
        
        clientText.addClickListener(event -> {        
        	content.add(grid,dialogButton);
        	
        	
        	});
        
        
        homeText.addClickListener(event -> {        
        	content.removeAll();
        
        	
        	
        	});
//	    
		       

		    
		        
        
        content.add();
        center.add(navBar,content);
//        center.setFlexGrow(1, navBar);
        //content.setAlignSelf(Alignment.END, footer);
        add(center);
        
        expand(center);
       
    }

	
	

    
    

   
       
      
}
