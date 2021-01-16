package main.entity;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ORDERR {
	@Id
	@GeneratedValue
	private Integer IDOrder;
	
	
	@ManyToOne
	@JoinColumn(name = "IDCustomer")
	private CUSTOMER CUSTOMER;
	

	@ManyToOne
	@JoinColumn(name="IDEmployee")
	private EMPLOYEE EMPLOYEE;
	
	@OneToMany(mappedBy = "ORDERR",fetch = FetchType.LAZY)
	private Collection<ORDER_NONPIZZA_DETAIL> ORDER_NONPIZZA_DETAILS ;
	
	@OneToMany(mappedBy = "ORDERR",fetch = FetchType.LAZY)
	private Collection<ORDERDETAIL> ORDERDETAILS  ;


	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date OrderTime;
	private String Payment;
	private String Delivery;
	private String IDVoucher;
	private Integer Orderstatus;

	
	public Integer getIDOrder() {
		return IDOrder;
	}
	public void setIDOrder(Integer iDOrder) {
		IDOrder = iDOrder;
	}
	public CUSTOMER getCUSTOMER() {
		return CUSTOMER;
	}
	public void setCUSTOMER(CUSTOMER cUSTOMER) {
		CUSTOMER = cUSTOMER;
	}
	public EMPLOYEE getEMPLOYEE() {
		return EMPLOYEE;
	}
	public void setEMPLOYEE(EMPLOYEE eMPLOYEE) {
		EMPLOYEE = eMPLOYEE;
	}
	public Collection<ORDER_NONPIZZA_DETAIL> getORDER_NONPIZZA_DETAILS() {
		return ORDER_NONPIZZA_DETAILS;
	}
	public void setORDER_NONPIZZA_DETAILS(Collection<ORDER_NONPIZZA_DETAIL> oRDER_NONPIZZA_DETAILS) {
		ORDER_NONPIZZA_DETAILS = oRDER_NONPIZZA_DETAILS;
	}
	public Collection<ORDERDETAIL> getORDERDETAILS() {
		return ORDERDETAILS;
	}
	public void setORDERDETAILS(Collection<ORDERDETAIL> oRDERDETAILS) {
		ORDERDETAILS = oRDERDETAILS;
	}
	public Date getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(Date orderTime) {
		OrderTime = orderTime;
	}
	public String getPayment() {
		return Payment;
	}
	public void setPayment(String payment) {
		Payment = payment;
	}
	public String getDelivery() {
		return Delivery;
	}
	public void setDelievery(String delivery) {
		Delivery = delivery;
	}
	public String getIDVoucher() {
		return IDVoucher;
	}
	public void setIDVoucher(String iDVoucher) {
		IDVoucher = iDVoucher;
	}
	public Integer getOrderstatus() {
		return Orderstatus;
	}
	public void setOrderstatus(Integer orderstatus) {
		Orderstatus = orderstatus;
	}
	
}
