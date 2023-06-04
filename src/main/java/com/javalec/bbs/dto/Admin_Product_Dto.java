package com.javalec.bbs.dto;

import java.sql.Timestamp;

public class Admin_Product_Dto {

	// Field
		String pfilename;
		String pname;
		int pid;
		int pprice;
		String pcontent;
		Timestamp pinsertdate;
		Timestamp pdeletedate;
		
		
		
		
		
		
		
		
		
		
		// Constructor
		public Admin_Product_Dto(String pfilename, String pname, int pprice, int pid) {
			super();
			this.pfilename = pfilename;
			this.pname = pname;
			this.pprice = pprice;
			this.pid = pid;
		}
		
		
		// Method
		
		public String getPfilename() {
			return pfilename;
		}




		public void setPfilename(String pfilename) {
			this.pfilename = pfilename;
		}





		public String getPname() {
			return pname;
		}




		public void setPname(String pname) {
			this.pname = pname;
		}



		public int getPid() {
			return pid;
		}




		public void setPid(int pid) {
			this.pid = pid;
		}




		public int getPprice() {
			return pprice;
		}




		public void setPprice(int pprice) {
			this.pprice = pprice;
		}





		public String getPcontent() {
			return pcontent;
		}





		public void setPcontent(String pcontent) {
			this.pcontent = pcontent;
		}






		public Timestamp getPinsertdate() {
			return pinsertdate;
		}




		public void setPinsertdate(Timestamp pinsertdate) {
			this.pinsertdate = pinsertdate;
		}


		public Timestamp getPdeletedate() {
			return pdeletedate;
		}



		public void setPdeletedate(Timestamp pdeletedate) {
			this.pdeletedate = pdeletedate;
		}
		
		
		
}
