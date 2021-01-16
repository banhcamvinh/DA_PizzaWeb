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
public class PIZZAOPTION_PRICE implements Serializable{
	@Id
	private Integer IDPizzaOption;
	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date PriceDate;
	private Float Price;
	
	@ManyToOne
	@JoinColumn(name="IDPizzaOption")
	private PIZZASIZE PIZZAOPTION;

	public Integer getIDPizzaOption() {
		return IDPizzaOption;
	}

	public void setIDPizzaOption(Integer iDPizzaOption) {
		IDPizzaOption = iDPizzaOption;
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

	public PIZZASIZE getPIZZAOPTION() {
		return PIZZAOPTION;
	}

	public void setPIZZAOPTION(PIZZASIZE pIZZAOPTION) {
		PIZZAOPTION = pIZZAOPTION;
	}


}
