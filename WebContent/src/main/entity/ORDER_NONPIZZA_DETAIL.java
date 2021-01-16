package main.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ORDER_NONPIZZA_DETAIL implements Serializable{
	private Integer Quantity;
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDNONPizza")
	private NONPIZZA NONPIZZA;
	

	@Id
	@ManyToOne
	@JoinColumn(name="IDOrder")
	private ORDERR ORDERR;
	
	
	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public NONPIZZA getNONPIZZA() {
		return NONPIZZA;
	}

	public void setNONPIZZA(NONPIZZA nONPIZZA) {
		NONPIZZA = nONPIZZA;
	}

	public ORDERR getORDERR() {
		return ORDERR;
	}

	public void setORDERR(ORDERR oRDERR) {
		ORDERR = oRDERR;
	}
}
