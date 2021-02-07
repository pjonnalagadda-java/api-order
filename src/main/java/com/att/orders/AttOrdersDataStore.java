package com.att.orders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import com.att.orders.model.Catalog;
import com.att.orders.model.DateRange;
import com.att.orders.model.HardSKU;
import com.att.orders.model.Item;
import com.att.orders.model.Order;
import com.att.orders.model.SKU;

@Component
public class AttOrdersDataStore {
	
	public Catalog attCatalogData = new Catalog();
	public List<Order> orderListData = new ArrayList<Order>(); 
	
	
	/**
	 * build the catalog data
	 */
	public void buildCatalogData(){
		List<SKU> lssku = new ArrayList<SKU>();
		List<SKU> lsHardSku = new ArrayList<SKU>();
		for(int i = 1; i <= 10; i++) {
			lssku.add(createSKUData(i+"_SKU"));
			lsHardSku.add(createHardSKUData(i+"_SKU"));
		}
		lssku.addAll(lsHardSku);
		attCatalogData.setSkus(lssku);
	}
	
	/**
	 * @param strSKUIndex
	 * @return
	 */
	private SKU createSKUData(String strSKUIndex) {
		SKU sku = new SKU();
		sku.setId(strSKUIndex+"_id");
		sku.setName(strSKUIndex+"_name");
		sku.setType("SKU");
		return sku;
	}
	
	/**
	 * @param strHardSKUIndex
	 * @return
	 */
	private HardSKU createHardSKUData(String strHardSKUIndex) {
		HardSKU sku = new HardSKU();
		sku.setId(strHardSKUIndex+"_id");
		sku.setName(strHardSKUIndex+"_name");
		sku.setColor(strHardSKUIndex+"_color");
		sku.setManufacturer(strHardSKUIndex+"_manufacture");
		sku.setModel(strHardSKUIndex+"_model");
		sku.setSize(strHardSKUIndex+"_size");
		sku.setType("HardSKU");
		return sku;
	}
	

	/**
	 * build the order data
	 */
	public void buildOrderData() {
		for(int i = 1; i <= 5; i++) {
			//build order
			//Add build order to List 
			orderListData.add(createOrder(i));
		}
	}

	/**
	 * @param orderIndex
	 * @return
	 */
	private Order createOrder(int orderIndex) {
		Order order = new Order();
		order.setId(1000+orderIndex);
		order.setOrderId(2000+orderIndex);
		order.setCustomerId(3000+orderIndex);
		/**Date Range*/
		DateRange estimatedShipDateRange = new DateRange();
		estimatedShipDateRange.setFromDate(getAttOrderDate(orderIndex,orderIndex));
		estimatedShipDateRange.setToDate(getAttOrderDate(orderIndex, orderIndex+5));
		order.setEstimatedShipDateRange(estimatedShipDateRange);
		if(orderIndex%2 == 0) {
			order.setStatus("delivered");
			order.setComplete(true);
		}
		else {
			order.setStatus("Ordered");
			order.setComplete(false);
			DateRange newEstimatedShipDateRange  = new DateRange();
			newEstimatedShipDateRange.setFromDate(getAttOrderDate(orderIndex+1,orderIndex));
			newEstimatedShipDateRange.setToDate(getAttOrderDate(orderIndex+1, orderIndex+5));
			order.setNewEstimatedShipDateRange(newEstimatedShipDateRange);
		}
		order.setItems(createItems(orderIndex));
		return order;
	}
	
	
	/**
	 * To create Items
	 * @param orderIndex
	 * @return
	 */
	private List<Item> createItems(int orderIndex){
		int id=0;
		List<SKU> subSkus = attCatalogData.getSkus().subList(orderIndex, orderIndex+2);
		List<Item> itemList = new ArrayList<Item>();
		for (SKU sku : subSkus) {
			Item item = new Item();
			item.setId(id++);
			item.setSkuId(sku.getId());
			item.setName(sku.getName());
			item.setQuantity(id+orderIndex);
			DateRange estimatedShipDateRange = new DateRange();
			estimatedShipDateRange.setFromDate(getAttOrderDate(orderIndex,orderIndex));
			estimatedShipDateRange.setToDate(getAttOrderDate(orderIndex, orderIndex+5));
			item.setEstimatedShipDateRange(estimatedShipDateRange);
			if(orderIndex%2 == 0) {
				item.setStatus("delivered");
				item.setComplete(true);
			}else {
				item.setStatus("Ordered");
				item.setComplete(false);
				DateRange newEstimatedShipDateRange  = new DateRange();
				
				newEstimatedShipDateRange.setFromDate(getAttOrderDate(orderIndex+1,orderIndex));
				newEstimatedShipDateRange.setToDate(getAttOrderDate(orderIndex+1, orderIndex+5));
				item.setNewEstimatedShipDateRange(newEstimatedShipDateRange);
				item.setUserAcceptedDelay(true);
				item.setDelayAcceptedDate(getAttOrderDate(1,10));
			}
			itemList.add(item);
		}
		
	    return itemList;
	}
	
	private String getAttOrderDate(int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(2021, month, day);
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(cal.getTime());
		return date;
	}
	

}
