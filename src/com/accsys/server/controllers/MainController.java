package com.accsys.server.controllers;
    
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accsys.server.models.Contact;
import com.accsys.server.models.ContactWM;
import com.accsys.server.models.Version;
import com.accsys.server.services.ContactsService;
import com.accsys.server.util.ErrorResponse;
import com.accsys.server.util.Mapper;
import com.accsys.server.util.PosException;

@RestController
@CrossOrigin
public class MainController {
	
	@RequestMapping(value="/test")
	public Version getVersion(){
		
		Version version = new Version();
		version.setVersion("1.0");
		version.setTitle("Initial Version");
		
		return version;
	}
	
	@RequestMapping(value="/contact", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> getUsers(@RequestParam("_page") int _page,
			@RequestParam("_limit") int _limit,
			@RequestParam(name="email_like", required = false) String email_like,
			@RequestParam(name="contacType_like", required = false) String contacType_like,
			@RequestParam(name="contactName_like", required = false) String contacName_like,
			@RequestParam(name="businessName_like", required = false) String businessName_like,
			@RequestParam(name="telephone_like", required = false) String telephone_like) throws Exception{
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		ContactsService cs = new ContactsService();
		List<Contact> contacts = null;
		
		try{
			contacts = cs.getAllContacts(_page, _limit, contacType_like, businessName_like, contacName_like, email_like, telephone_like);
		}catch(Exception e){
			System.out.println("CATCH");
			throw new PosException("AAA", 502);
		}
		
		int noOfContacts = 0; 
		try{
			noOfContacts = cs.countContacts(contacType_like, businessName_like, contacName_like, email_like, telephone_like);
		}
		catch(Exception e){
			
		}
		responseHeaders.set("access-control-expose-headers", "X-Total-Count");
		responseHeaders.set("X-Total-Count", Integer.toString(noOfContacts));
		
		
		/*if(_page == 1){
			throw new ContactException("Invalid employee name requested");
		}*/
		
		return new ResponseEntity<List<Contact>>(contacts, responseHeaders, HttpStatus.OK);
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> saveContact(@RequestBody ContactWM contact) throws Exception {
		
		HttpStatus httpStatus = null;
		Mapper mapper = new Mapper();
		Contact contactEM = mapper.mapContact(contact);
		ContactsService cs = new ContactsService();
		try{
			httpStatus = HttpStatus.CREATED;
			System.out.println(contact.getId()+"    "+contact.getContactType());
			cs.saveContact(contactEM);
		}catch(Exception e){
			System.out.println("CATCH");
			//throw new EPException("AAA");
		}
		
		return new ResponseEntity<HttpStatus>(httpStatus);
			
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> deleteContact(@RequestBody ContactWM contact) throws Exception {
		
		HttpStatus httpStatus = null;
		Mapper mapper = new Mapper();
		Contact contactEM = mapper.mapContactToDelete(contact);
		ContactsService cs = new ContactsService();
		try{
			httpStatus = HttpStatus.CREATED;
			System.out.println(contact.getId()+"    "+contact.getContactType());
			cs.saveContact(contactEM);
		}catch(Exception e){
			System.out.println("CATCH");
			//throw new EPException("AAA");
		}
		
		return new ResponseEntity<HttpStatus>(httpStatus);
			
	}
	
	@ExceptionHandler(PosException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}


}
