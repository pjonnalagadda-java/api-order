package com.att.orders.postModel;

import com.att.orders.model.DateRange;

public class AcceptShipDateRequestBody  {
	

	
	 private DateRange estimatedShipDateRange; 
	 private DateRange newEstimatedShipDateRange;	 
	 private String delayAcceptedDate;
	 
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
	public String getDelayAcceptedDate() {
		return delayAcceptedDate;
	}
	public void setDelayAcceptedDate(String delayAcceptedDate) {
		this.delayAcceptedDate = delayAcceptedDate;
	}
	
	
	
	
	
	
	

}
