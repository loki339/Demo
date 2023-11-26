package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @Column(name = "accnumber", nullable = false, length = 20)
    private String accountNumber;

    @Column(name = "fullname", length = 60)
    private String fullName;

    @Column(name = "contactaddress", length = 300)
    private String contactAddress;

    @Column(name = "zipcode", length = 8)
    private String zipCode;

    @Column(name = "contactnumber", length = 15)
    private String contactNumber;

    @Column(name = "cardtype", length = 50)
    private String cardType;

    @Column(name = "cardnumber", length = 20)
    private String cardNumber;

    @Column(name = "expirydate", length = 20)
    private String expiryDate;

    @Column(name = "cvvnumber", length = 5)
    private String cvvNumber;

    @Column(name = "balance")
    private Float balance;

	public Bank() {
		super();
	}

	public Bank(String accountNumber, String fullName, String contactAddress, String zipCode, String contactNumber,
			String cardType, String cardNumber, String expiryDate, String cvvNumber, Float balance) {
		super();
		this.accountNumber = accountNumber;
		this.fullName = fullName;
		this.contactAddress = contactAddress;
		this.zipCode = zipCode;
		this.contactNumber = contactNumber;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.cvvNumber = cvvNumber;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Bank [accountNumber=" + accountNumber + ", fullName=" + fullName + ", contactAddress=" + contactAddress
				+ ", zipCode=" + zipCode + ", contactNumber=" + contactNumber + ", cardType=" + cardType
				+ ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + ", cvvNumber=" + cvvNumber
				+ ", balance=" + balance + "]";
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCvvNumber() {
		return cvvNumber;
	}

	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}
}
