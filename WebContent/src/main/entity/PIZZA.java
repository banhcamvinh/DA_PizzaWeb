package main.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PIZZA {
	@Id
	@GeneratedValue
	private Integer IDPizza;
	private String Name;
	private String Descript;
	private String Img;
	
	@OneToMany(mappedBy = "PIZZA",fetch = FetchType.EAGER)
	private Collection<PIZZA_PRICE> PIZZAPRICES;
	@OneToMany(mappedBy = "PIZZA",fetch = FetchType.LAZY)
	private Collection<ORDERDETAIL> ORDERDETAILS  ;
	
	
	public Integer getIDPizza() {
		return IDPizza;
	}

	public void setIDPizza(Integer iDPizza) {
		IDPizza = iDPizza;
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

	public Collection<PIZZA_PRICE> getPIZZAPRICES() {
		return PIZZAPRICES;
	}

	public void setPIZZAPRICES(Collection<PIZZA_PRICE> pIZZAPRICES) {
		PIZZAPRICES = pIZZAPRICES;
	}


}
