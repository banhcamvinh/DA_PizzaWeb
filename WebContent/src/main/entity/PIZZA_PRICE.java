package main.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class PIZZA_PRICE implements Serializable{
	@Id
	private Integer IDPizza;
	
	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date PriceDate;
	private Float Price;
	
	@ManyToOne
	@JoinColumn(name="IDPizza")
	private PIZZA PIZZA;
	

	
	public Integer getIDPizza() {
		return IDPizza;
	}

	public void setIDPizza(Integer iDPizza) {
		IDPizza = iDPizza;
	}

	public Date getPriceDate() {
		return PriceDate;
	}

	public void setPriceDate(Date priceDate) {
		PriceDate = priceDate;
	}

	public Float getPrice() {
		return Price;
	}

	public void setPrice(Float price) {
		Price = price;
	}

	public PIZZA getPIZZA() {
		return PIZZA;
	}

	public void setPIZZA(PIZZA pIZZA) {
		PIZZA = pIZZA;
	}
}
