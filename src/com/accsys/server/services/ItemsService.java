package com.accsys.server.services;

import java.util.List;

import com.accsys.server.entity.ItemsFactory;
import com.accsys.server.models.Item;

public class ItemsService {

	public void saveItem(Item item) {

		ItemsFactory cf = new ItemsFactory();
		cf.saveItem(item);

	}

	public List<Item> getAllItems(int page, int limit, String itemCode_like, String itemDescription_like, String sellingPrice_like, String currentStock_like, String stockPerDate_like) throws Exception {

		int firstResult = limit * (page - 1);
		ItemsFactory cf = new ItemsFactory();
		List<Item> items = null;
		try {
			items = cf.getAllItems(firstResult, limit, itemCode_like, itemDescription_like, sellingPrice_like, currentStock_like, stockPerDate_like);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;

	}

}
