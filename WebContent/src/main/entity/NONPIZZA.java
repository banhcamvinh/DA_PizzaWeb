package main.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class NONPIZZA {
	@Id
	private Integer IDNonPizza;
	private String Name;
	private String Descript;
	private String Img;
	private Integer NonPizzaType;
	
	@OneToMany(mappedBy = "NONPIZZA",fetch = FetchType.EAGER)
	private Collection<NONPIZZA_PRICE> NONPIZZAPRICES;
	@OneToMany(mappedBy = "NONPIZZA",fetch = FetchType.LAZY)
	private Collection<ORDER_NONPIZZA_DETAIL> ORDER_NONPIZZA_DETAILS ;
	
	
	public Collection<ORDER_NONPIZZA_DETAIL> getORDER_NONPIZZA_DETAILS() {
		return ORDER_NONPIZZA_DETAILS;
	}

	public void setORDER_NONPIZZA_DETAILS(Collection<ORDER_NONPIZZA_DETAIL> oRDER_NONPIZZA_DETAILS) {
		ORDER_NONPIZZA_DETAILS = oRDER_NONPIZZA_DETAILS;
	}

	public Integer getIDNonPizza() {
		return IDNonPizza;
	}

	public void setIDNonPizza(Integer iDNonPizza) {
		IDNonPizza = iDNonPizza;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescript() {
		return Descript;
	}

	public void setDescript(String descript) {
		Descript = descript;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public Integer getNonPizzaType() {
		return NonPizzaType;
	}

	public void setNonPizzaType(Integer nonPizzaType) {
		NonPizzaType = nonPizzaType;
	}

	public Collection<NONPIZZA_PRICE> getNONPIZZAPRICES() {
		return NONPIZZAPRICES;
	}

	public void setNONPIZZAPRICES(Collection<NONPIZZA_PRICE> nONPIZZAPRICES) {
		NONPIZZAPRICES = nONPIZZAPRICES;
	}
}
