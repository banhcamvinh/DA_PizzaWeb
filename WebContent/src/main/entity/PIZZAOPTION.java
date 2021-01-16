package main.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PIZZAOPTION {
	@Id
	private Integer IDPizzaOption;
	private String Optionn;
	@OneToMany(mappedBy = "PIZZAOPTION",fetch = FetchType.EAGER)
	private Collection<PIZZAOPTION_PRICE> PIZZAOPTIONPRICES;
	

	@OneToMany(mappedBy = "PIZZAOPTION",fetch = FetchType.LAZY)
	private Collection<ORDERDETAIL> ORDERDETAILS  ;

	
	public String getOptionn() {
		return Optionn;
	}

	public void setOptionn(String optionn) {
		Optionn = optionn;
	}

	public Collection<ORDERDETAIL> getORDERDETAILS() {
		return ORDERDETAILS;
	}

	public void setORDERDETAILS(Collection<ORDERDETAIL> oRDERDETAILS) {
		ORDERDETAILS = oRDERDETAILS;
	}
	public Integer getIDPizzaOption() {
		return IDPizzaOption;
	}

	public void setIDPizzaOption(Integer iDPizzaOption) {
		IDPizzaOption = iDPizzaOption;
	}

	public String getOption() {
		return Optionn;
	}

	public void setOption(String option) {
		Optionn = option;
	}

	public Collection<PIZZAOPTION_PRICE> getPIZZAOPTIONPRICES() {
		return PIZZAOPTIONPRICES;
	}

	public void setPIZZAOPTIONPRICES(Collection<PIZZAOPTION_PRICE> pIZZAOPTIONPRICES) {
		PIZZAOPTIONPRICES = pIZZAOPTIONPRICES;
	}


	

}
