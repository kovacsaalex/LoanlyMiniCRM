package com.example.demo.view;

import java.time.LocalDate;

public class Contact {
    String FirstName;
    LocalDate selectedDate;
    public Contact() {
  		
  	}
    
    
    public Contact(String firstName, LocalDate selectedDate) {
		
		FirstName = firstName;
		this.selectedDate = selectedDate;
	}


	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public LocalDate getSelectedDate() {
		return selectedDate;
	}


	public void setSelectedDate(LocalDate selectedDate) {
		this.selectedDate = selectedDate;
	}


    
}
