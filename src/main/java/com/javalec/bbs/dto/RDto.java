package com.javalec.bbs.dto;

public class RDto {

	int pid;
	String pName;
    String pfilename;
    String pcontent;
    int pprice;
    
    String f_cid;
    String ftype;
    String ftitle;
    String fcontent;

    public RDto() {
		// TODO Auto-generated constructor stub
	}



	public RDto(String f_cid, String ftype, String ftitle, String fcontent) {
		super();
		this.f_cid = f_cid;
		this.ftype = ftype;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
	}



	public RDto(int pid, String pName, String pfilename, String pcontent, int pprice) {
		super();
		this.pid = pid;
		this.pName = pName;
		this.pfilename = pfilename;
		this.pcontent = pcontent;
		this.pprice = pprice;
	}







	public int getPid() {
		return pid;
	}



	public void setPid(int pid) {
		this.pid = pid;
	}



	public String getpName() {
		return pName;
	}



	public void setpName(String pName) {
		this.pName = pName;
	}



	public String getPfilename() {
		return pfilename;
	}



	public void setPfilename(String pfilename) {
		this.pfilename = pfilename;
	}



	public String getPcontent() {
		return pcontent;
	}



	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}



	public int getPprice() {
		return pprice;
	}



	public void setPprice(int pprice) {
		this.pprice = pprice;
	}



	public String getF_cid() {
		return f_cid;
	}



	public void setF_cid(String f_cid) {
		this.f_cid = f_cid;
	}



	public String getFtype() {
		return ftype;
	}



	public void setFtype(String ftype) {
		this.ftype = ftype;
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

	
}