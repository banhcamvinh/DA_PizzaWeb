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
public class PIZZASIZE_PRICE implements Serializable{
	@Id
	private Integer IDPizzaSize;

	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date PriceDate;
	private Float Price;
	
	@ManyToOne
	@JoinColumn(name="IDPizzaSize")
	private PIZZASIZE PIZZASIZE;
	
	public Integer getIDPizzaSize() {
		return IDPizzaSize;
	}

	public void setIDPizzaSize(Integer iDPizzaSize) {
		IDPizzaSize = iDPizzaSize;
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

	public PIZZASIZE getPIZZASIZE() {
		return PIZZASIZE;
	}

	public void setPIZZASIZE(PIZZASIZE pIZZASIZE) {
		PIZZASIZE = pIZZASIZE;
	}
}
