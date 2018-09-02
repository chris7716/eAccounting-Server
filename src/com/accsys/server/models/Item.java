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
@Table(name="tblItems")
public class Item {
	
	@Id @GeneratedValue
	@Column (name ="id")
	private int id;
	@Column (name ="itemCode")
	private String itemCode;
	@Column (name ="itemDescription")
	private String itemDescription;
	@Column (name ="sellingPrice")
	private String sellingPrice;
	@Column (name ="currentStock")
	private int currentStock;
	@Column (name ="stockPerDate")
	private Date stockPerDate;
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
	
	public Item(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public Date getStockPerDate() {
		return stockPerDate;
	}

	public void setStockPerDate(Date stockPerDate) {
		this.stockPerDate = stockPerDate;
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
