package com.att.orders.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.att.orders.ApiOrdersApplication;
import com.att.orders.Contants;
import com.att.orders.model.Item;
import com.att.orders.model.Order;
import com.att.orders.postModel.AcceptShipDateRequestBody;
import com.att.orders.postModel.CancelRequestBody;

@Component
public class OrderService {
	
	Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	/**
	 * to fetch all the orders
	 * @return
	 */
	public List<Order> buildOrders() {
		List<Order> orders = ApiOrdersApplication.attOrdersDataStore.orderListData;
		return orders;
	}
	
	/**
	 * to find the order by id
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(int orderId) {	
		logger.info("orders loaded already");
		return findOrder(orderId);
	}
	
	/**
	 * @param toBeCancelOrder
	 * @return
	 */
	public int cancelOrder(int orderId,CancelRequestBody requestBody) {
		logger.info(String.format("In tobeCancelItem OrderId :%s", orderId));
		Order order = findOrder(orderId);
		if(order.getStatus().equals(Contants.CANCELLED) || order.getStatus().equals(Contants.SHIPPED)) {
			return 409;
		}
		for (Item item : order.getItems()) {
			if(item.getStatus().equals(Contants.CANCELLED) || item.getStatus().equals(Contants.SHIPPED)) {
				return 409;
			}
		}
		order.setStatus(Contants.CANCELLED);
		return 200;
	}
	
	/**
	 * 
	 * @param orderId
	 * @param itemId
	 * @param requestBody
	 * @return
	 */
	public int cancelItem(int orderId,int itemId,CancelRequestBody requestBody) {
		logger.info(String.format("In tobeCancelItem OrderId :%s Item:%s", orderId,itemId));
		List<Order> orders = ApiOrdersApplication.attOrdersDataStore.orderListData;
		for (Order order : orders) {
			if(order.getOrderId() == orderId) {
				for (Item item : order.getItems()) {
					if(item.getId() == itemId) {
						if(item.getStatus().equals(Contants.CANCELLED) || item.getStatus().equals(Contants.SHIPPED)) {
							return 409;
						}
						item.setStatus(Contants.CANCELLED);
						logger.info(String.format("Successfully cancelled the item %s",item.getId()));
					}else {
						logger.info(String.format("Item is not found %s",itemId));
						return 400;
					}
				}
			}else {
				return 400;
			}
		}
		return 200;			
	}
	
	/**
	 * 
	 * @param orderId
	 * @param itemId
	 * @param requestBody
	 * @return
	 */
	public int acceptShipDate(int orderId,int itemId,AcceptShipDateRequestBody requestBody) {
		logger.info(String.format("In acceptShipDate OrderId :%s Item:%s", orderId,itemId));
		Order foundOrder = findOrder(orderId);
		if(foundOrder == null) {
			return 400;
		}
		Item foundItem = foundOrder.getItems().stream().filter(item -> item.getId() == itemId).findAny().orElse(null);
		if(foundItem == null) {
			return 400;
		}
		if(foundItem.getUserAcceptedDelay()){
			return 409;
		}
		
		foundItem.setEstimatedShipDateRange(requestBody.getEstimatedShipDateRange());
		foundItem.setNewEstimatedShipDateRange(requestBody.
		getNewEstimatedShipDateRange());
		foundItem.setDelayAcceptedDate(requestBody.getDelayAcceptedDate());
		foundItem.setUserAcceptedDelay(true);
		
		return 200;
	}
	
	/**
	 * @param orderId
	 * @return
	 */
	private Order findOrder(int orderId) {
		logger.info(String.format("finding.. order for orderId .. %s", orderId));
		List<Order> orders = ApiOrdersApplication.attOrdersDataStore.orderListData;
		Order order = orders.stream()
				  .filter(or -> or.getOrderId() == orderId)
				  .findAny()
				  .orElse(null);
		return order;
	}
	

}
