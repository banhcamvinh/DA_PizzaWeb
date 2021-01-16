package main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CUSTOMER_SHIP {
	@Id
	@GeneratedValue
	private Integer IDCustomer_Ship;
	private String Customer_Add;
	private String Customer_Phone;
	@ManyToOne
	@JoinColumn(name="IDCustomer")
	private CUSTOMER CUSTOMER;
	public Integer getIDCustomer_Ship() {
		return IDCustomer_Ship;
	}
	public void setIDCustomer_Ship(Integer iDCustomer_Ship) {
		IDCustomer_Ship = iDCustomer_Ship;
	}
	public String getCustomer_Add() {
		return Customer_Add;
	}
	public void setCustomer_Add(String customer_Add) {
		Customer_Add = customer_Add;
	}
	public String getCustomer_Phone() {
		return Customer_Phone;
	}
	public void setCustomer_Phone(String customer_Phone) {
		Customer_Phone = customer_Phone;
	}
	public CUSTOMER getCUSTOMER() {
		return CUSTOMER;
	}
	public void setCUSTOMER(CUSTOMER cUSTOMER) {
		CUSTOMER = cUSTOMER;
	}
}
