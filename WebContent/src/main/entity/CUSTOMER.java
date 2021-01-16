package main.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CUSTOMER {
	@Id
	@GeneratedValue
	private Integer IDCustomer;
	
	private String Name;
	private String Email;
	private Integer Gender;
	private String Phone;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date Birthday;
	@OneToOne
	@JoinColumn(name = "IDAccount")
	private ACCOUNT Account;
	@OneToMany(mappedBy = "CUSTOMER",fetch = FetchType.EAGER)
	private Collection<ORDERR> ORDERRS;
	@OneToMany(mappedBy = "CUSTOMER", fetch = FetchType.LAZY)
	private Collection<CUSTOMER_SHIP> CUSTOMERSHIPS;
	
	public Collection<CUSTOMER_SHIP> getCUSTOMERSHIPS() {
		return CUSTOMERSHIPS;
	}
	public void setCUSTOMERSHIPS(Collection<CUSTOMER_SHIP> cUSTOMERSHIPS) {
		CUSTOMERSHIPS = cUSTOMERSHIPS;
	}
	public Collection<ORDERR> getORDERRS() {
		return ORDERRS;
	}
	public void setORDERRS(Collection<ORDERR> oRDERRS) {
		ORDERRS = oRDERRS;
	}
	public Integer getIDCustomer() {
		return IDCustomer;
	}
	public void setIDCustomer(Integer iDCustomer) {
		IDCustomer = iDCustomer;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Integer getGender() {
		return Gender;
	}
	public void setGender(Integer gender) {
		Gender = gender;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public ACCOUNT getAccount() {
		return Account;
	}
	public void setAccount(ACCOUNT account) {
		Account = account;
	}
	
	
}
