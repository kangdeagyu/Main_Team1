package com.javalec.bbs.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class aExtraDto_kkg {

	
	
	
	
	
	//field
	
	
	Timestamp timestamp ;
	int sales ;
	Date date;
	int ordercount;
	String month;
	
	//constructor
	
	
	
	public aExtraDto_kkg(Date date, int sales) {
		super();
		this.date = date;
		this.sales = sales;
	}


	
	public aExtraDto_kkg(Date date, int sales, int ordercount) {
		super();
		this.date = date;
		this.sales = sales;
		this.ordercount = ordercount;
	}
	
	
	
	public aExtraDto_kkg(String month, int sales, int ordercount) {
		super();
		this.month = month;
		this.sales = sales;
		this.ordercount = ordercount;
	}

	
	
	

	
	//getter & setter



	public Timestamp getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}



	public int getSales() {
		return sales;
	}



	public void setSales(int sales) {
		this.sales = sales;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}




	public int getOrdercount() {
		return ordercount;
	}




	public void setOrdercount(int ordercount) {
		this.ordercount = ordercount;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}
	
	
	
	
	
	
	
	
	
	
	
}
