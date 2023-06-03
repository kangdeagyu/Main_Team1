package com.javalec.bbs.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class aExtraDto_kkg {

	
	
	
	
	
	//field
	
	
	Timestamp timestamp ;
	int sales ;
	Date date;
	
	
	
	//constructor
	
	
	
	public aExtraDto_kkg(Date date, int sales) {
		super();
		this.date = date;
		this.sales = sales;
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
	
	
	
	
	
	
	
	
	
	
	
}
