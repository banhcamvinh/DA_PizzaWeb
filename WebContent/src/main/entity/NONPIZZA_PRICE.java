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
public class NONPIZZA_PRICE implements Serializable {
	@Id
	private Integer IDNonPizza;
	
	@Id
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date PriceDate;
	private Float Price;
	
	@ManyToOne
	@JoinColumn(name="IDNonPizza")
	private NONPIZZA NONPIZZA;

	
	
	public Integer getIDNonPizza() {
		return IDNonPizza;
	}
	public void setIDNonPizza(Integer iDNonPizza) {
		IDNonPizza = iDNonPizza;
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
	public NONPIZZA getNONPIZZA() {
		return NONPIZZA;
	}
	public void setNONPIZZA(NONPIZZA nONPIZZA) {
		NONPIZZA = nONPIZZA;
	}

}
