package com.att.orders.model;

public class Item {
	
	private Integer id;
	private String name;
	private String skuId;
	private Integer quantity;
	private String status;
	private Boolean complete;
	private DateRange estimatedShipDateRange;
	private DateRange newEstimatedShipDateRange;
	private Boolean userAcceptedDelay;
	private String delayAcceptedDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	public Boolean getUserAcceptedDelay() {
		return userAcceptedDelay;
	}
	public void setUserAcceptedDelay(Boolean userAcceptedDelay) {
		this.userAcceptedDelay = userAcceptedDelay;
	}
	public String getDelayAcceptedDate() {
		return delayAcceptedDate;
	}
	public void setDelayAcceptedDate(String delayAcceptedDate) {
		this.delayAcceptedDate = delayAcceptedDate;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", skuId=" + skuId + ", quantity=" + quantity + ", status="
				+ status + ", complete=" + complete + ", estimatedShipDateRange=" + estimatedShipDateRange
				+ ", newEstimatedShipDateRange=" + newEstimatedShipDateRange + ", userAcceptedDelay="
				+ userAcceptedDelay + ", delayAcceptedDate=" + delayAcceptedDate + "]";
	}
	
	
			
}
