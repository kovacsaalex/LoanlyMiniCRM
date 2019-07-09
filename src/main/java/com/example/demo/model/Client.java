package com.example.demo.model;

 import java.time.LocalDate;


public class Client {

    private Long Id;
    private String firstname;
    private String lastname;
    private String maidenName;
    private String birthPlace;
    public LocalDate birthDate;
    private String motherName;

    private String postalCode;
    private String city;
    private String address;
    private LocalDate movingTime;

    private String personalID;
    private String cardId;
    private String addressCardID;
    private String taxID;
    private String socialSecurityCard;

    private String phoneNumber;
    private String email;


    public Client() {
        // TODO Auto-generated constructor stub
    }

    public Client(Long id, String firstname, String lastname, String maidenName, String birthPlace, LocalDate birthDate, String motherName, String personalID, String cardId, String addressCardID, String taxID, String socialSecurityCard, String postalCode, String city, String address, LocalDate movingTime, String phoneNumber, String email) {
        Id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.maidenName = maidenName;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.motherName = motherName;
        this.personalID = personalID;
        this.cardId = cardId;
        this.addressCardID = addressCardID;
        this.taxID = taxID;
        this.socialSecurityCard = socialSecurityCard;
        this.postalCode = postalCode;
        this.city = city;
        this.address = address;
        this.movingTime = movingTime;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPersonalID() {
        return personalID;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAddressCardID() {
        return addressCardID;
    }

    public void setAddressCardID(String addressCardID) {
        this.addressCardID = addressCardID;
    }

    public String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public String getSocialSecurityCard() {
        return socialSecurityCard;
    }

    public void setSocialSecurityCard(String socialSecurityCard) {
        this.socialSecurityCard = socialSecurityCard;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getMovingTime() {
        return movingTime;
    }

    public void setMovingTime(LocalDate movingTime) {
        this.movingTime = movingTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
