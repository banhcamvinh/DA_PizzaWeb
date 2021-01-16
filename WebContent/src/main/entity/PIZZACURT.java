package main.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PIZZACURT {
	@Id
	private Integer IDPizzaCurt;
	private String Curt;
	
	@OneToMany(mappedBy = "PIZZACURT",fetch = FetchType.LAZY)
	private Collection<ORDERDETAIL> ORDERDETAILS  ;
	
	public Collection<ORDERDETAIL> getORDERDETAILS() {
		return ORDERDETAILS;
	}
	public void setORDERDETAILS(Collection<ORDERDETAIL> oRDERDETAILS) {
		ORDERDETAILS = oRDERDETAILS;
	}
	public Integer getIDPizzaCurt() {
		return IDPizzaCurt;
	}
	public void setIDPizzaCurt(Integer iDPizzaCurt) {
		IDPizzaCurt = iDPizzaCurt;
	}
	public String getCurt() {
		return Curt;
	}
	public void setCurt(String curt) {
		Curt = curt;
	}

}
