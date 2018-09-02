package com.accsys.server.models;

import java.util.Date; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tblContacts")
public class Contact {
	
	@Id @GeneratedValue
	@Column (name ="id")
	private int id;
	@Column (name ="businessName")
	private String businessName;
	@Column (name ="contactType")
	private String contactType;
	@Column (name ="contactName")
	private String contactName;
	@Column (name ="mobile")
	private String mobile;
	@Column (name ="telephone")
	private String telephone;
	@Column (name ="email")
	private String email;
	@Column (name ="address1")
	private String address1;
	@Column (name ="address2")
	private String address2;
	@Column (name ="city")
	private String city;
	@Column (name ="tax")
	private String tax;
	@Column (name ="createdBy")
	private String createdBy;
	@Column (name ="deletedBy")
	private String deletedBy;
	@Column (name ="createdDate")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	@Column (name ="deletedDate")
	@Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
	@Column (name ="isDeleted")
	private int isDeleted;
	
	public Contact(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

}
