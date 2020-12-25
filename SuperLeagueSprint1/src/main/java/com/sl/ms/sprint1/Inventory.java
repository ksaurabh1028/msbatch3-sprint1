package com.sl.ms.sprint1;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class Inventory {

	private LocalDate dateUpload;
	private String id;
	private String name;
	private int price;
	private int quantity;

	public Inventory(LocalDate dateUpload, String id, String name, int price, int quantity) {
		super();
		this.dateUpload = dateUpload;
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
    @Override
    public String toString() {
        return "\n Inventory{" +
                "dateUpload='" + dateUpload + '\'' +
                ", id=" + id +
                ", name=" + name +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                "}";
    }
    
	public LocalDate getDateUpload() {
		return dateUpload;
	}

	public String getDateUploadMonth() {
		return dateUpload.getMonth() + " " + dateUpload.getYear();
	}
	public void setDateUpload(LocalDate dateUpload) {
		this.dateUpload = dateUpload;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
