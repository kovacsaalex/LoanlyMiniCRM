package com.example.demo.view;

import com.example.demo.pojo.Client;
import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
class testView extends Composite<VerticalLayout> {

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

		// Layouts
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

		Button clientAddButton = new Button("Ügyfél hozzáadása", new Icon(VaadinIcon.PLUS_CIRCLE));
		clientAddButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		Button deleteButton = new Button("Ügyfél Törlése", new Icon(VaadinIcon.MINUS_CIRCLE));
		deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
		clientButton.getStyle().set("color", "white");

		Button clientEditButton = new Button("Ügyfél módosítása", new Icon(VaadinIcon.PENCIL));
		clientEditButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		// Button ClickListeners

		clientButton.addClickListener(event -> {
			content.removeAll();
			updateGrid();
			beforeFooter.add(clientAddButton, clientEditButton, deleteButton);
			content.add(grid, beforeFooter);

		});

		homeButton.addClickListener(event -> {
			content.removeAll();
			content.add(headerH3, welcomeText, text, checkbox1, checkbox2, checkbox3, checkbox4, checkbox5);

		});

		clientAddButton.addClickListener(event -> {
			dialog.setCloseOnEsc(false);
			dialog.setCloseOnOutsideClick(false);
			dialog.add(addClient.getContent());
			dialog.open();
			addClient.createClicked();
			dialog.add(cancelButton);
		});

		loanButton.addClickListener(event -> {
			content.removeAll();
			updateGrid();
			content.add(clientAddButton, deleteButton);

		});

		cancelButton = new Button("Bezárás!", event -> {

			try {
				dialog.close();
				updateGrid();
				// messageLabel.setText("Cancelled...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		deleteButton.addClickListener(event -> {
			alert.removeAll();
			VerticalLayout alertLayout = new VerticalLayout();
			alertLayout.setAlignItems(Alignment.CENTER);
			Text alertText = new Text("A törlés nem vonható vissza!");
			Button alertButton = new Button(" Törlés ");
			alertButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
			Button alertcancelButton = new Button(" Mégsem! ");
			alertcancelButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
			alertLayout.add(alertText, alertButton, alertcancelButton);
			alert.add(alertLayout);
			alert.setCloseOnEsc(false);
			alert.setCloseOnOutsideClick(false);
			alert.open();
			alertButton.addClickListener(event1 -> {
				setClient(grid.asSingleSelect().getValue());
				alert.close();
				delete();
				updateGrid();
			});
			alertcancelButton.addClickListener(event1 -> alert.close());

		});

		// Add components
		headerComponentsV.add(headerComponentsH);
		headerComponentsH.add(homeButton, clientButton, loanButton, ltpButton);
		headerComponentsV.setAlignItems(Alignment.CENTER);
		header.add(name, headerComponentsV);
		footer.setWidth("100%");
		footer.setPadding(true);
		footer.setHeight("50px");
		footer.getStyle().set("background-color", "#444444");
		content.add(headerH3, welcomeText, text, checkbox1, checkbox2, checkbox3, checkbox4, checkbox5);
		center.add(content);
		getContent().add(header, center, footer);
		getContent().expand(center);

		grid.addColumn(Client::getId).setHeader("Id").setWidth("60px");
		grid.addColumn(Client::getFirstname).setHeader("Vezetéknév").setWidth("110px").setResizable(true);
		grid.addColumn(Client::getLastname).setHeader("Keresztnév").setWidth("110px").setResizable(true);
		grid.addColumn(Client::getPhoneNumber).setHeader("Mobil").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getEmail).setHeader("E-mail").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getMaidenName).setHeader("Leánykori Név").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getMotherName).setHeader("Anyja neve").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getBirthPlace).setHeader("Születési hely").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getBirthDate).setHeader("Születési idő").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getPersonalID).setHeader("Személyi szám").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getTaxID).setHeader("Adószám").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getSocialSecurityCard).setHeader("Taj szám").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getCardId).setHeader("Személyi ig. szám").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getAddressCardID).setHeader("Lakcímkártya szám").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getPostalCode).setHeader("Irányítószám").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getAddress).setHeader("Lakcím").setWidth("150px").setResizable(true);
		grid.addColumn(Client::getMovingTime).setHeader("Mióta lakik ott").setWidth("150px").setResizable(true);

		updateGrid();

	}

	void setClient(Client client) {
		this.client = client;
		grid.setEnabled(client != null);
		binder.setBean(client);
	}

	private void updateGrid() {
		grid.setItems(clientRepository.findAll());
	}

	void delete() {
		try {
			// grid.asSingleSelect();
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
