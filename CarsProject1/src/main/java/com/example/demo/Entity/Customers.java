package com.example.demo.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerid", nullable = false)
    private Long customerId;

    @Column(name = "firstname", length = 50)
    private String firstName;

    @Column(name = "lastname", length = 50)
    private String lastName;

    @Column(name = "contactaddress", length = 400)
    private String contactAddress;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "zipcode", length = 10)
    private String zipcode;

    @Column(name = "contactmobile", length = 15)
    private String contactMobile;

    @Column(name = "emailid", length = 50, unique = true)
    private String emailId;

    @Column(name = "password", length = 50)
    private String password;

   /* @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Cars> cars;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CarSales> carSales;
    
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<AddToCart> carts;
*/
	public Customers() {
		super();
	}

public Customers(Long customerId, String firstName, String lastName, String contactAddress, String city, String state,
		String zipcode, String contactMobile, String emailId, String password) {
	super();
	this.customerId = customerId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.contactAddress = contactAddress;
	this.city = city;
	this.state = state;
	this.zipcode = zipcode;
	this.contactMobile = contactMobile;
	this.emailId = emailId;
	this.password = password;
}

public Long getCustomerId() {
	return customerId;
}

public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getContactAddress() {
	return contactAddress;
}

public void setContactAddress(String contactAddress) {
	this.contactAddress = contactAddress;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getZipcode() {
	return zipcode;
}

public void setZipcode(String zipcode) {
	this.zipcode = zipcode;
}

public String getContactMobile() {
	return contactMobile;
}

public void setContactMobile(String contactMobile) {
	this.contactMobile = contactMobile;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

@Override
public String toString() {
	return "Customers [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
			+ ", contactAddress=" + contactAddress + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
			+ ", contactMobile=" + contactMobile + ", emailId=" + emailId + ", password=" + password + "]";
}

	
}
