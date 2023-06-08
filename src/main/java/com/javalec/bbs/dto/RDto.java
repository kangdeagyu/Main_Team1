package com.javalec.bbs.dto;

public class RDto {

	int pid;
	String pname;
    String pfilename;
    String pcontent;
    int pprice;
    
    String f_cid;
    int ftype;
    String ftitle;
    String fcontent;
    int pstock;
    
    int cid;
    String bqty;
    public RDto() {
		// TODO Auto-generated constructor stub
	}



	public RDto(int pid, int cid, String bqty) {
		super();
		this.pid = pid;
		this.cid = cid;
		this.bqty = bqty;
	}



	public String getPname() {
		return pname;
	}



	public void setPname(String pname) {
		this.pname = pname;
	}



	public int getCid() {
		return cid;
	}



	public void setCid(int cid) {
		this.cid = cid;
	}



	public String getBqty() {
		return bqty;
	}



	public void setBqty(String bqty) {
		this.bqty = bqty;
	}



	public RDto(String f_cid, int ftype, String ftitle, String fcontent) {
		super();
		this.f_cid = f_cid;
		this.ftype = ftype;
		this.ftitle = ftitle;
		this.fcontent = fcontent;
	}



	public RDto(int pid, String pname,String pfilename, String pcontent, int pprice, int pstock) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pcontent = pcontent;
		this.pprice = pprice;
		this.pstock = pstock;
	}







	public int getPstock() {
		return pstock;
	}



	public void setPstock(int pstock) {
		this.pstock = pstock;
	}



	public int getPid() {
		return pid;
	}



	public void setPid(int pid) {
		this.pid = pid;
	}



	public String getpname() {
		return pname;
	}



	public void setpname(String pname) {
		this.pname = pname;
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






	public int getFtype() {
		return ftype;
	}



	public void setFtype(int ftype) {
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