package com.example.demo.view;

public class Contact {
    String FirstName;
    int year;
    public Contact() {
  		
  	}
    
    
    public Contact(String firstName, int year) {
		
		FirstName = firstName;
		this.year = year;
	}

	public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
    
}
