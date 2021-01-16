package main.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PIZZASIZE {
	@Id
	private Integer IDPizzaSize;
	private String Size;
	
	@OneToMany(mappedBy = "PIZZASIZE",fetch = FetchType.EAGER)
	private Collection<PIZZASIZE_PRICE> PIZZASIZEPRICES;
	@OneToMany(mappedBy = "PIZZASIZE",fetch = FetchType.LAZY)
	private Collection<ORDERDETAIL> ORDERDETAILS  ;
	
	public Collection<ORDERDETAIL> getORDERDETAILS() {
		return ORDERDETAILS;
	}

	public void setORDERDETAILS(Collection<ORDERDETAIL> oRDERDETAILS) {
		ORDERDETAILS = oRDERDETAILS;
	}

	public Integer getIDPizzaSize() {
		return IDPizzaSize;
	}

	public void setIDPizzaSize(Integer iDPizzaSize) {
		IDPizzaSize = iDPizzaSize;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public Collection<PIZZASIZE_PRICE> getPIZZASIZEPRICES() {
		return PIZZASIZEPRICES;
	}

	public void setPIZZASIZEPRICES(Collection<PIZZASIZE_PRICE> pIZZASIZEPRICES) {
		PIZZASIZEPRICES = pIZZASIZEPRICES;
	}

}
