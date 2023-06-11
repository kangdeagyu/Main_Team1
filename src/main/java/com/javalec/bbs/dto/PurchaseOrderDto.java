package com.javalec.bbs.dto;

public class PurchaseOrderDto {

	
    String oid;
    String cname;
    String cphone;
    String caddress1;
    String caddress2;
    String cpostnum;
    String pname;
    String pfilename;
    String pcontent;
    int pid;
    int pstock;
    int pprice;
    

    public PurchaseOrderDto() {
		// TODO Auto-generated constructor stub
	}
    
    

	public PurchaseOrderDto(String oid, String cname, String cphone, String caddress1, String caddress2, String cpostnum,
			String pname, String pfilename, String pcontent, int pid, int pstock, int pprice) {
		super();
		this.oid = oid;
		this.cname = cname;
		this.cphone = cphone;
		this.caddress1 = caddress1;
		this.caddress2 = caddress2;
		this.cpostnum = cpostnum;
		this.pname = pname;
		this.pfilename = pfilename;
		this.pcontent = pcontent;
		this.pid = pid;
		this.pstock = pstock;
	}



	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPhone() {
		return cphone;
	}

	public void setPhone(String cphone) {
		this.cphone = cphone;
	}

	public String getAddress1() {
		return caddress1;
	}

	public void setAddress1(String caddress1) {
		this.caddress1 = caddress1;
	}

	public String getAddress2() {
		return caddress2;
	}

	public void setAddress2(String caddress2) {
		this.caddress2 = caddress2;
	}

	public String getPostnum() {
		return cpostnum;
	}

	public void setPostnum(String cpostnum) {
		this.cpostnum = cpostnum;
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

	public String getPcontent() {
		return pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPstock() {
		return pstock;
	}

	public void setPstock(int pstock) {
		this.pstock = pstock;
	}
    
    
}
