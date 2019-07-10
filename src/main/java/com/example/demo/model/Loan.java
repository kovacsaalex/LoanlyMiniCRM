package com.example.demo.model;

import java.time.LocalDate;

public class Loan {
    // hiteladatok
    private int loanId;
    private String loanType;
    private int amount;
    private LocalDate loanStart;
    private LocalDate loanEnd;
    private int loanTerm; // honapokban
    private double interestRate;


    // fedezet adatok
    private String idNumber;
    private String city;
    private String address;
    private String postalCode;
    private int size;
    private int value;
    private String status;

    public Loan() {
    }

    public Loan(int loanId, String loanType, int amount, LocalDate loanStart, LocalDate loanEnd, int loanTerm, double interestRate, String idNumber, String city, String address, String postalCode, int size, int value, String status) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.amount = amount;
        this.loanStart = loanStart;
        this.loanEnd = loanEnd;
        this.loanTerm = loanTerm;
        this.interestRate = interestRate;
        this.idNumber = idNumber;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.size = size;
        this.value = value;
        this.status = status;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getLoanStart() {
        return loanStart;
    }

    public void setLoanStart(LocalDate loanStart) {
        this.loanStart = loanStart;
    }

    public LocalDate getLoanEnd() {
        return loanEnd;
    }

    public void setLoanEnd(LocalDate loanEnd) {
        this.loanEnd = loanEnd;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

