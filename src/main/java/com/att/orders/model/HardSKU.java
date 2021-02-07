package com.att.orders.model;

public class HardSKU extends SKU{
	
	private String model;
	private String manufacturer;
	private String color;
	private String size;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "HardSKU [model=" + model + ", manufacturer=" + manufacturer + ", color=" + color + ", size=" + size
				+ "]";
	}

	
}
