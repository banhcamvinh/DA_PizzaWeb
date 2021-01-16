package main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SENDNEW {
	@Id
	@GeneratedValue
	private Integer IDSendNew;
	private String Email;
	public Integer getIDSendNew() {
		return IDSendNew;
	}
	public void setIDSendNew(Integer iDSendNew) {
		IDSendNew = iDSendNew;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

}
