package com.accsys.server.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.accsys.server.models.Contact;
import com.accsys.server.models.ContactWM;
import com.accsys.server.models.CustomizedDate;
import com.accsys.server.models.Item;
import com.accsys.server.models.ItemWM;

public class Mapper {

	public Contact mapContact(ContactWM contact) {

		Contact mappedContact = new Contact();

		mappedContact.setId(contact.getId());
		mappedContact.setAddress1(contact.getAddress1());
		mappedContact.setAddress2(contact.getAddress2());
		mappedContact.setBusinessName(contact.getBusinessName());
		mappedContact.setCity(contact.getCity());
		mappedContact.setContactName(contact.getContactName());
		mappedContact.setContactType(contact.getContactType());
		mappedContact.setCreatedBy("ADMIN");
		mappedContact.setCreatedDate(contact.getCreatedDate());
		mappedContact.setDeletedBy(contact.getDeletedBy());
		mappedContact.setDeletedDate(contact.getDeletedDate());
		mappedContact.setEmail(contact.getEmail());
		mappedContact.setIsDeleted(contact.getIsDeleted());
		mappedContact.setMobile(contact.getMobile());
		mappedContact.setTax(contact.getTax());
		mappedContact.setTelephone(contact.getTelephone());

		return mappedContact;

	}

	public Contact mapContactToDelete(ContactWM contact) {

		Contact mappedContact = new Contact();

		mappedContact.setId(contact.getId());
		mappedContact.setAddress1(contact.getAddress1());
		mappedContact.setAddress2(contact.getAddress2());
		mappedContact.setBusinessName(contact.getBusinessName());
		mappedContact.setCity(contact.getCity());
		mappedContact.setContactName(contact.getContactName());
		mappedContact.setContactType(contact.getContactType());
		mappedContact.setCreatedBy(contact.getCreatedBy());
		mappedContact.setCreatedDate(contact.getCreatedDate());
		mappedContact.setDeletedBy(contact.getDeletedBy());
		mappedContact.setDeletedDate(contact.getDeletedDate());
		mappedContact.setEmail(contact.getEmail());
		mappedContact.setIsDeleted(1);
		mappedContact.setMobile(contact.getMobile());
		mappedContact.setTax(contact.getTax());
		mappedContact.setTelephone(contact.getTelephone());

		return mappedContact;

	}

	public Item mapItemToSave(ItemWM item) throws ParseException {

		Item mappedItem = new Item();
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		mappedItem.setId(item.getId());
		mappedItem.setItemCode(item.getItemCode());
		mappedItem.setItemDescription(item.getItemDescription());
		mappedItem.setCurrentStock(Integer.parseInt(item.getCurrentStock()));
		mappedItem.setSellingPrice(item.getSellingPrice());
		mappedItem.setStockPerDate(df.parse(item.getStockPerDate().getYear() + "-" + item.getStockPerDate().getMonth()
				+ "-" + item.getStockPerDate().getDay()));
		mappedItem.setCreatedBy("ADMIN");
		mappedItem.setCreatedDate(now);
		mappedItem.setDeletedBy(item.getDeletedBy());
		mappedItem.setDeletedDate(df.parse("0000-00-00"));
		mappedItem.setIsDeleted(item.getIsDeleted());

		return mappedItem;

	}
	
	public List<ItemWM> mapItemToSend(List<Item> items){
		
		List<ItemWM> mappedItems = new ArrayList<ItemWM>();
		
		for (Item item: items){
			
			ItemWM mappedItem = new ItemWM();
			CustomizedDate stockPerDate = new CustomizedDate();
			
			stockPerDate.setYear(item.getStockPerDate().getYear() + 1900);
			stockPerDate.setMonth(item.getStockPerDate().getMonth());
			stockPerDate.setDay(item.getStockPerDate().getDate());
			
			mappedItem.setId(item.getId());
			mappedItem.setItemCode(item.getItemCode());
			mappedItem.setItemDescription(item.getItemDescription());
			mappedItem.setCurrentStock(Integer.toString(item.getCurrentStock()));
			mappedItem.setSellingPrice(item.getSellingPrice());
			mappedItem.setStockPerDate(stockPerDate);
			
			mappedItems.add(mappedItem);
		}
		
		return mappedItems;
		
	}

}
