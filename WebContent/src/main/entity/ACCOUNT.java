package main.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ACCOUNT {
	@Id
	@GeneratedValue
	private Integer IDAccount;
	private String Username;
	private String Userpass;
	private Integer Userrole;
	
	@OneToOne(mappedBy = "Account")
	private CUSTOMER Customer;
	
	@OneToOne(mappedBy = "Account")
	private EMPLOYEE Employee;
	
	public CUSTOMER getCustomer() {
		return Customer;
	}
	public void setCustomer(CUSTOMER customer) {
		Customer = customer;
	}
	public Integer getIDAccount() {
		return IDAccount;
	}
	public void setIDAccount(Integer iDAccount) {
		IDAccount = iDAccount;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getUserpass() {
		return Userpass;
	}
	public void setUserpass(String userpass) {
		Userpass = userpass;
	}
	public Integer getUserrole() {
		return Userrole;
	}
	public void setUserrole(Integer userrole) {
		Userrole = userrole;
	}

}
