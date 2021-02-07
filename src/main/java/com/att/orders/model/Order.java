package com.att.orders.model;

import java.util.List;

public class Order{

	private Integer id;
	private Integer orderId;
	private Integer customerId; 
	private List<Item> items; 
	private String status;
	private Boolean complete; 
	private Address address;
	private DateRange estimatedShipDateRange; 
	private DateRange newEstimatedShipDateRange;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public DateRange getEstimatedShipDateRange() {
		return estimatedShipDateRange;
	}
	public void setEstimatedShipDateRange(DateRange estimatedShipDateRange) {
		this.estimatedShipDateRange = estimatedShipDateRange;
	}
	public DateRange getNewEstimatedShipDateRange() {
		return newEstimatedShipDateRange;
	}
	public void setNewEstimatedShipDateRange(DateRange newEstimatedShipDateRange) {
		this.newEstimatedShipDateRange = newEstimatedShipDateRange;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", customerId=" + customerId + ", items=" + items
				+ ", status=" + status + ", complete=" + complete + ", address=" + address + ", estimatedShipDateRange="
				+ estimatedShipDateRange + ", newEstimatedShipDateRange=" + newEstimatedShipDateRange + "]";
	}
	
	
	

	
	
	

}
