package com.javalec.bbs.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class AdminExtra_Dto_kkg {

	
	
	
	
	
	//field
	
	
	Timestamp timestamp ;
	int sales ;
	Date date;
	int ordercount;
	String month;
	
	int seq ;
	String cid ;
	String cname ;
	String cphone ;
	String cbirthdate ;
	String cgender ;
	String caddress ;
	Date cinsertdate ;
	
	
	
	//constructor
	
	
	
	
	
	public AdminExtra_Dto_kkg(Date date, int sales) {
		super();
		this.date = date;
		this.sales = sales;
	}


	
	public AdminExtra_Dto_kkg(int seq, String cid, String cname, String cphone, String cbirthdate, String cgender,
			String caddress, Date cinsertdate) {
		super();
		this.seq = seq;
		this.cid = cid;
		this.cname = cname;
		this.cphone = cphone;
		this.cbirthdate = cbirthdate;
		this.cgender = cgender;
		this.caddress = caddress;
		this.cinsertdate = cinsertdate;
	}



	public AdminExtra_Dto_kkg(Date date, int sales, int ordercount) {
		super();
		this.date = date;
		this.sales = sales;
		this.ordercount = ordercount;
	}
	
	
	
	public AdminExtra_Dto_kkg(String month, int sales, int ordercount) {
		super();
		this.month = month;
		this.sales = sales;
		this.ordercount = ordercount;
	}

	
	
	

	
	//getter & setter



	public Timestamp getTimestamp() {
		return timestamp;
	}



	public int getSeq() {
		return seq;
	}



	public void setSeq(int seq) {
		this.seq = seq;
	}



	public String getCid() {
		return cid;
	}



	public void setCid(String cid) {
		this.cid = cid;
	}



	public String getCname() {
		return cname;
	}



	public void setCname(String cname) {
		this.cname = cname;
	}



	public String getCphone() {
		return cphone;
	}



	public void setCphone(String cphone) {
		this.cphone = cphone;
	}



	public String getCbirthdate() {
		return cbirthdate;
	}



	public void setCbirthdate(String cbirthdate) {
		this.cbirthdate = cbirthdate;
	}



	public String getCgender() {
		return cgender;
	}



	public void setCgender(String cgender) {
		this.cgender = cgender;
	}



	public String getCaddress() {
		return caddress;
	}



	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}



	public Date getCinsertdate() {
		return cinsertdate;
	}



	public void setCinsertdate(Date cinsertdate) {
		this.cinsertdate = cinsertdate;
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