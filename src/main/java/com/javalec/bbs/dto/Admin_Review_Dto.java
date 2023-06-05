package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Admin_Review_Dto {

	
	// Field
	int fid;
	String f_cid;
	String f_aid;
	int f_pid;
	int ftype;
	int freforder;
	int fstep;
	String ftitle;
	String fcontent;
	int fmotherid;
	Timestamp finsertdate;
	String cid;
	String cname;
	String pname;
	String pfilename;
	int pprice;
	String t_name;
	
	// Constructor
	
	
	
	
	
	
	


	public Admin_Review_Dto(int fid, Timestamp finsertdate, String cname, String pname, String pfilename, int pprice, String ftitle) {
		super();
		this.fid = fid;
		this.finsertdate = finsertdate;
		this.cname = cname;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pprice = pprice;
		this.ftitle=ftitle;
	}

	
	
	// Method
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getF_cid() {
		return f_cid;
	}

	public void setF_cid(String f_cid) {
		this.f_cid = f_cid;
	}

	public String getF_aid() {
		return f_aid;
	}

	public void setF_aid(String f_aid) {
		this.f_aid = f_aid;
	}

	public int getF_pid() {
		return f_pid;
	}

	public void setF_pid(int f_pid) {
		this.f_pid = f_pid;
	}

	public int getFtype() {
		return ftype;
	}

	public void setFtype(int ftype) {
		this.ftype = ftype;
	}

	public int getFreforder() {
		return freforder;
	}

	public void setFreforder(int freforder) {
		this.freforder = freforder;
	}

	public int getFstep() {
		return fstep;
	}

	public void setFstep(int fstep) {
		this.fstep = fstep;
	}

	public String getFtitle() {
		return ftitle;
	}

	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}

	public String getFcontent() {
		return fcontent;
	}

	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}

	public int getFmotherid() {
		return fmotherid;
	}

	public void setFmotherid(int fmotherid) {
		this.fmotherid = fmotherid;
	}

	public Timestamp getFinsertdate() {
		return finsertdate;
	}

	public void setFinsertdate(Timestamp finsertdate) {
		this.finsertdate = finsertdate;
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPfilename() {
		return pfilename;
	}

	public void setPfilename(String pfilename) {
		this.pfilename = pfilename;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}



	public int getPprice() {
		return pprice;
	}



	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
