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
import com.accsys.server.models.Item;
import com.accsys.server.models.ItemWM;
import com.accsys.server.services.ItemsService;
import com.accsys.server.util.CustomException;
import com.accsys.server.util.CustomExceptionCodes;
import com.accsys.server.util.ErrorResponse;
import com.accsys.server.util.Mapper;
import com.accsys.server.util.PosException;

@RestController
@CrossOrigin
public class ItemsController {
	
	@RequestMapping(value = "/items/save", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> saveItem(@RequestBody ItemWM item) throws Exception{
		
		HttpStatus httpStatus = null;
		Mapper mapper = new Mapper();
		Item itemToSave = mapper.mapItemToSave(item);
		ItemsService itemService = new ItemsService();
		
		try{
			httpStatus = HttpStatus.CREATED;
			//System.out.println(contact.getId()+"    "+contact.getContactType());
			itemService.saveItem(itemToSave);
		}catch(Exception e){
			throw new CustomException("Item was not saved", CustomExceptionCodes.ITEM_SAVE_ERROR);
		}
		
		return new ResponseEntity<HttpStatus>(httpStatus);
		
		
	}
	
	@RequestMapping(value="/items/all", method = RequestMethod.GET)
	public ResponseEntity<List<ItemWM>> getUsers(@RequestParam("_page") int _page,
			@RequestParam("_limit") int _limit,
			@RequestParam(name="itemCode_like", required = false) String itemCode_like,
			@RequestParam(name="itemDescription_like", required = false) String itemDescription_like,
			@RequestParam(name="sellingPrice_like", required = false) String sellingPrice_like,
			@RequestParam(name="currentStock_like", required = false) String currentStock_like,
			@RequestParam(name="stockPerDate_like", required = false) String stockPerDate_like) throws Exception{
		
		HttpHeaders responseHeaders = new HttpHeaders();
		
		//cilContactsService cs = new ContactsService();
		ItemsService is = new ItemsService();
		List<ItemWM> items = null;
		Mapper mapper = new Mapper();
		
		try{
			items = mapper.mapItemToSend(is.getAllItems(_page, _limit, itemCode_like, itemDescription_like, sellingPrice_like, currentStock_like, stockPerDate_like));
		}catch(Exception e){
			System.out.println("CATCH");
			throw new CustomException(e.getMessage(), CustomExceptionCodes.ITEM_SAVE_ERROR);
		}
		
		int noOfContacts = 0; 
		try{
			//noOfContacts = cs.countContacts(itemCode_like, itemDescription_like, sellingPrice_like, currentStock_like, stockPerDate_like);
		}
		catch(Exception e){
			
		}
		responseHeaders.set("access-control-expose-headers", "X-Total-Count");
		responseHeaders.set("X-Total-Count", Integer.toString(10));
		
		
		/*if(_page == 1){
			throw new ContactException("Invalid employee name requested");
		}*/
		
		return new ResponseEntity<List<ItemWM>>(items, responseHeaders, HttpStatus.OK);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(CustomException exception) {
		
		//This is a new object from ErrorResponse which is goint to be sent to the client within ResponseEntity 
		ErrorResponse error = new ErrorResponse();
		
		//Sets the errorCode of the ErrorResponse as the customized error code which is within 'exception'
		error.setErrorCode(exception.getErrorCode());
		
		//Sets the errorMessage of the ErrorResponse as the customized error message which is within 'exception'
		error.setMessage(exception.getMessage());
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

}
