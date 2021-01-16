package main.entity;

import java.util.Collection;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

@Entity
public class EMPLOYEE {

	@Id
	@GeneratedValue
	private Integer IDEmployee;
	private String Name;
	
	@OneToOne
	@JoinColumn(name = "IDAccount")
	private ACCOUNT Account;
	
	@OneToMany(mappedBy = "EMPLOYEE",fetch = FetchType.EAGER)
	private Collection<ORDERR> ORDERRS;
	
	public Collection<ORDERR> getORDERRS() {
		return ORDERRS;
	}
	public void setORDERRS(Collection<ORDERR> oRDERRS) {
		ORDERRS = oRDERRS;
	}
	public Integer getIDEmployee() {
		return IDEmployee;
	}
	public void setIDEmployee(Integer iDEmployee) {
		IDEmployee = iDEmployee;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public ACCOUNT getAccount() {
		return Account;
	}
	public void setAccount(ACCOUNT account) {
		Account = account;
	}
}
