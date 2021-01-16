package main.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ORDERDETAIL implements Serializable{
	private Integer Quantity;
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDPizzaSize")
	private PIZZASIZE PIZZASIZE;
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDPizzaOption")
	private PIZZAOPTION PIZZAOPTION;

	@Id
	@ManyToOne
	@JoinColumn(name="IDPizzaCurt")
	private PIZZACURT PIZZACURT;
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDOrder")
	private ORDERR ORDERR;
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDPizza")
	private PIZZA PIZZA;
	

	
	public Integer getQuantity() {
		return Quantity;
	}

	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}

	public PIZZASIZE getPIZZASIZE() {
		return PIZZASIZE;
	}

	public void setPIZZASIZE(PIZZASIZE pIZZASIZE) {
		PIZZASIZE = pIZZASIZE;
	}

	public PIZZAOPTION getPIZZAOPTION() {
		return PIZZAOPTION;
	}

	public void setPIZZAOPTION(PIZZAOPTION pIZZAOPTION) {
		PIZZAOPTION = pIZZAOPTION;
	}

	public PIZZACURT getPIZZACURT() {
		return PIZZACURT;
	}

	public void setPIZZACURT(PIZZACURT pIZZACURT) {
		PIZZACURT = pIZZACURT;
	}

	public ORDERR getORDERR() {
		return ORDERR;
	}

	public void setORDERR(ORDERR oRDERR) {
		ORDERR = oRDERR;
	}

	public PIZZA getPIZZA() {
		return PIZZA;
	}

	public void setPIZZA(PIZZA pIZZA) {
		PIZZA = pIZZA;
	}

}
