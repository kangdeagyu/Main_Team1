package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.javalec.bbs.dto.MDto;
import com.javalec.bbs.dto.OrderDto;



public class MDao {

	DataSource dataSource;
	public MDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 체크
	public String authenticate(String username, String password) {
		String result = null;
		int count = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select count(cname), cname, cdeletedate from customer where cid = ? and cpassword = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, username);
		      ps.setString(2, password);
		      rs = ps.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);	
	                if (rs.getString(3) != null) {
	                    // cdeletedate가 존재하는 경우, 탈퇴한 회원으로 처리
	                    result = "mdraw";
	                } else {
	                    // cdeletedate가 존재하지 않는 경우, 등록된 회원
	                    result = rs.getString(2);
	                }
				}

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
	}
	
	public String adminCheck(String username, String password) {
		String result = null;
		int count = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select count(aid), adeletedate from admin where aid = ? and apw = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, username);
		      ps.setString(2, password);
		      rs = ps.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);	
	                if (rs.getString(2) != null) {
	                    // cdeletedate가 존재하는 경우, 탈퇴한 관리자로 처리
	                    result = "mdraw";
	                } else {
	                    // cdeletedate가 존재하지 않는 경우, 등록된 관리자
	                    result = "admin";
	                }
				}

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
	}
	
// 이메일 체크
	public String emailcheak(String email) {
		String result = "email";
		int count = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select count(cid) from customer where cid = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, email);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					count = rs.getInt(1);

				}
				if(count > 0) {
					result =  "duplicate";	// 등록된 회원
				}				

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
	}
	
	// 회원가입
	public int join(String cid, String cpassword, String cname, String cphone, String cbirthdate, int cgender, String cpostnum,
			String caddress1, String caddress2) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result;
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "insert into customer (cid, cpassword, cname, cphone, cbirthdate, cgender, cpostnum, caddress1, caddress2, cinsertdate) values (?,?,?,?,?,?,?,?,?,now())";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cid);
			preparedStatement.setString(2, cpassword);
			preparedStatement.setString(3, cname);
			preparedStatement.setString(4, cphone);
			preparedStatement.setString(5, cbirthdate);
			preparedStatement.setInt(6, cgender);
			preparedStatement.setString(7, cpostnum);
			preparedStatement.setString(8, caddress1);
			preparedStatement.setString(9, caddress2);
			
			preparedStatement.executeUpdate();
			result = 1;

		}catch (Exception e) {
			result = 0;
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// Id찾기
	public String Id(String cname, String cphone) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String result = null;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select cid from customer where cname = ? and cphone = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, cname);
		      ps.setString(2, cphone);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					result =  rs.getString(1);	// 등록된 회원	
				}
			

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
		
	}
	
	// 비밀번호 찾기
	public String Pw(String cid, String cname) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String result = null;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select cpassword from customer where cid = ? and cname = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, cid);
		      ps.setString(2, cname);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					result =  rs.getString(1);	// 등록된 회원	
				}
			

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
		
	}
	
	
	//회원 정보 불러오기
	public ArrayList<MDto> list(String mcid){
		ArrayList<MDto> dtos = new ArrayList<MDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String gender;
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select cid, cpassword, cname, cphone,cbirthdate, cgender, cpostnum, caddress1, caddress2 from customer where cid = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, mcid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String cid = rs.getString("cid");
				String cpassword = rs.getString("cpassword");
				String cname = rs.getString("cname");
				String cphone = rs.getString("cphone");
				String cbirthdate = rs.getString("cbirthdate");
				int cgender = rs.getInt("cgender");
				String cpostnum = rs.getString("cpostnum");
				String caddress1 = rs.getString("caddress1");
				String caddress2 = rs.getString("caddress2");
					// 성별
					if(cgender == 0) {
						gender = "female";
					}else {
						gender = "male";
					}
					
					// birth

				
				MDto dto = new MDto(cid, cpassword, cname, cphone, cbirthdate, gender, cpostnum, caddress1, caddress2);
				dtos.add(dto);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	// 회원 정보 수정
	public boolean memberUpdate(String cid, String cname, String cphone, String cbirthdate, int cgender, String cpostnum,
			String caddress1, String caddress2) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		boolean result = false;
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "update customer set cname = ?, cphone = ?, cbirthdate = ?, cgender = ?, cpostnum = ?, caddress1 = ?, caddress2 = ? where cid = ?";
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, cname);
			preparedStatement.setString(2, cphone);
			preparedStatement.setString(3, cbirthdate);
			preparedStatement.setInt(4, cgender);
			preparedStatement.setString(5, cpostnum);
			preparedStatement.setString(6, caddress1);
			preparedStatement.setString(7, caddress2);
			preparedStatement.setString(8, cid);
			preparedStatement.executeUpdate();
			result = true;

		}catch (Exception e) {
			result = false;
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 비밀번호 변경
	public boolean pwchange(String cid, String pw, String newpw) {
		Connection connection = null;
		PreparedStatement ps = null;


		boolean result = false;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "update customer set cpassword = ? where cid = ? and cpassword = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, newpw);
		      ps.setString(2, cid);
		      ps.setString(3, pw);
		      int rowsUpsated = ps.executeUpdate();
		      
		      if (rowsUpsated > 0) {
		            result = true; // 변경 작업이 성공한 경우
		        }

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
		
		
	}
	
	//회원 탈퇴
	public boolean memberDraw(String cid) {
		boolean result = false;
		
		Connection connection = null;
		PreparedStatement ps = null;

		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "update customer set cdeletedate = now() where cid = ?";
			ps = connection.prepareStatement(query);
		    ps.setString(1, cid);
		    int rowsUpsated = ps.executeUpdate();
		      
		      if (rowsUpsated > 0) {
		            result = true; // 변경 작업이 성공한 경우
		        }
		      
			

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
	}
	
	// 카카오 로그인 확인
	public int kakaoLogin(String cid) {
		int result = 0; // 아이디가 존재 하지 않을때
		int idcount = 0; // 아이디
	    int pwcount = 0; // 비밀번호
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select count(cid), count(cpassword), cdeletedate from customer where cid = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, cid);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					idcount = rs.getInt(1);
					pwcount = rs.getInt(2);

					
	                if (rs.getString(3) != null && idcount > 0 && pwcount > 0) {
	                	result = 2; // 탈퇴한회원
	                }else if(rs.getString(3) == null && idcount > 0 && pwcount > 0) { 
	                    result = 1; // 등록된 회원
	                }else {
	                	result = 0;
	                }
				}

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
		
		
	}
	
	// 장바구니 선택 삭제
	public boolean selectionDelete(String[] bids) {
	    boolean result = false;
	    Connection connection = null;
	    PreparedStatement ps = null;

	    try {
	        connection = dataSource.getConnection(); // SQL 연결
	        String query = "DELETE FROM basket WHERE bid = ?";
	        ps = connection.prepareStatement(query);
	        
	        for (String bid : bids) {
	            String[] bidValues = bid.split(","); // 쉼표로 구분된 값을 분리
	            for (String value : bidValues) {
	                int intBid = Integer.parseInt(value.trim()); // 공백 제거 후 int로 변환
	                ps.setInt(1, intBid);
	                ps.executeUpdate();
	            }
	        }
	        
	        result = true; 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}




	// 주문 
	public ArrayList<OrderDto> userOrder(String[] bids) {
		ArrayList<OrderDto> list = new ArrayList<OrderDto>();
	    Connection connection = null;
	    PreparedStatement ps = null;

	    ResultSet rs = null;

	    try {
	        connection = dataSource.getConnection(); // SQL 연결
	        
	        // 장바구니 상품 가져오기
	        String query = "SELECT b.b_cid, b.b_pid, b.bqty, p.pprice, p.pfilename, p.pname, p.pcontent FROM basket b, product p WHERE b.b_pid = p.pid AND b.bid = ?";
	        ps = connection.prepareStatement(query);
	        
	        for (String bid : bids) {
	            ps.setString(1, bid);
	            rs = ps.executeQuery(); // 쿼리 실행

	            // 결과 처리
	            while (rs.next()) {
	            	// 장바구니에서 데이터 가져오기
	                String customer_cid = rs.getString(1);
	                int product_pid = rs.getInt(2);
	                int oqty = rs.getInt(3);
	                int price = rs.getInt(4);
	                String filename = rs.getString(5);
	                String pname = rs.getString(6);
	                String pcontent = rs.getString(7);
	                String pfilename = "image/" + filename;
	                OrderDto dto = new OrderDto(customer_cid, product_pid, oqty, price, pfilename, pname, pcontent);
	                list.add(dto);
	            }

	        }


	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();

	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return list;
	}
	
	// 구매 회원 정보
	public ArrayList<MDto> orderUserList(String mcid){
		ArrayList<MDto> dtos = new ArrayList<MDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select cid, cname, cphone, cpostnum, caddress1, caddress2 from customer where cid = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, mcid);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String cid = rs.getString("cid");
				String cname = rs.getString("cname");
				String cphone = rs.getString("cphone");
				String cpostnum = rs.getString("cpostnum");
				String caddress1 = rs.getString("caddress1");
				String caddress2 = rs.getString("caddress2");

					
				
				MDto dto = new MDto(cid, cname, cphone, cpostnum, caddress1, caddress2);
				dtos.add(dto);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	
	// 구매
	public boolean order(String cid, String[] pid, String[] qty, String[] price, String postnum, String address1, String addess2) {
		boolean result = false;
		Connection connection = null;
	    PreparedStatement ps = null;

	    try {
	        connection = dataSource.getConnection(); // SQL 연결
	        
	        for (int i = 0; i < pid.length; i++) {
	            String query = "insert into ordering (customer_cid, product_pid, oqty, oprice, opostnum, oaddress1, oaddress2, odelivery, odate) values (?, ?, ?, ?, ?, ?, ?, ?, now())";
	            ps = connection.prepareStatement(query);
	            ps.setString(1, cid);
	            ps.setInt(2, Integer.parseInt(pid[i]));
	            ps.setInt(3,  Integer.parseInt(qty[i]));
	            ps.setInt(4,  Integer.parseInt(price[i]));
	            ps.setString(5, postnum);
	            ps.setString(6, address1);
	            ps.setString(7, addess2);
	            ps.setInt(8, 0);
	            ps.executeUpdate(); // 쿼리 실행
	        }

	        result = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
		
		
		return result;
	}
	
	
}// end
