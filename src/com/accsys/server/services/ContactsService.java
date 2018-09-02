package com.accsys.server.services;

import java.util.List;

import com.accsys.server.entity.ContactsFactory;
import com.accsys.server.models.Contact;

public class ContactsService {
	
	public List<Contact> getAllContacts(int page, int limit, String contactType_like, String businessName_like, String contactName_like, String email_like, String telephone_like) throws Exception{
		
		int firstResult = limit * (page - 1);
		ContactsFactory cf = new ContactsFactory();
		List<Contact> contacts = null;
		try {
			contacts = cf.getAllContacts(firstResult, limit, contactType_like, businessName_like, contactName_like, email_like, telephone_like);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contacts;
		
	}
	
	public void saveContact(Contact contact){
		
		ContactsFactory cf = new ContactsFactory();
		cf.saveContact(contact);
		
	}
	
	public int countContacts(String contactType_like, String businessName_like, String contactName_like, String email_like, String telephone_like){
		
		ContactsFactory contactsFactory = new ContactsFactory();
		int noOfContacts = contactsFactory.countContacts(contactType_like, businessName_like, contactName_like, email_like, telephone_like);
		return noOfContacts;
				
	}

}
