package com.example.demo.view;

import java.awt.Button;
import java.awt.Event;

import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;



@Route("tt")
@StyleSheet("frontend://styles/div-layout-styles.css")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class Layouts extends VerticalLayout{
	
	 private final ClientRepository clientRepository;
	 
	public Layouts(ClientRepository clientRepository) {
		
		 this.clientRepository = clientRepository;
		 AddClient addClient = new AddClient(clientRepository);
	    H1 headerH1 = new H1("Header");
	    H2 headerH2 = new H2("Navbar");
	    H3 headerH3 = new H3("Content");
	    H4 headerH4 = new H4("Footer");
	    H4 headerH5 = new H4("Loanly");
	    Text helloText = new Text("Loanly");
	    //Button client = new Button("Ügyfél");
	    
	    
	    
	 // Instantiate layouts
	    HorizontalLayout header = new HorizontalLayout();
	    VerticalLayout navBar = new VerticalLayout();
	    VerticalLayout content = new VerticalLayout();
	    HorizontalLayout center = new HorizontalLayout();
	    HorizontalLayout footer = new HorizontalLayout();
	    HorizontalLayout headerComponents = new HorizontalLayout();
	    VerticalLayout headerComponentsV = new VerticalLayout();
	    VerticalLayout footerComponentsV = new VerticalLayout();
	    
	   
       

	    // Configure layouts
	    setSizeFull();
	    setPadding(false);
	    setSpacing(false);
	    header.setWidth("100%");
	    header.setPadding(true);
	    header.setHeight("80px");
	    headerComponentsV.add(helloText);
	    headerComponentsV.setAlignItems(Alignment.CENTER);
	    header.add(headerH5,headerComponentsV);
	    //header.setAlignSelf(Alignment.END, headerH2);
	    header.getStyle().set("background-color", "#444444");
	    headerH5.getStyle().set("color", "white");
	    center.setWidth("100%");
	    navBar.setWidth("200px");
	    navBar.add(headerH2);
	    navBar.getStyle().set("background-color", "#00ACED");
	    headerH2.getStyle().set("color", "white");
	    content.setWidth("100%");
	    content.add(headerH3);
	    footer.setWidth("100%");
	    footer.setPadding(true);
	    
	    footerComponentsV.setWidth("100%");
	    footerComponentsV.setPadding(true);
	    footerComponentsV.getStyle().set("background-color", "purple");
	    footerComponentsV.add(headerH4);
	    footerComponentsV.setAlignItems(Alignment.CENTER);
	    
	    footer.add(footerComponentsV);
	    footer.getStyle().set("background-color", "#444444");
	    headerH4.getStyle().set("color", "white");
	    //footer.setAlignSelf(Alignment.CENTER, headerH4);
	    footer.setHeight("80px");

	    // Compose layout
	    center.add(navBar, content);
	    center.setFlexGrow(1, navBar);
	    add(header, center, footer);
	    expand(center);
	  
	

	
	}

}
