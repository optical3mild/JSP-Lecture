package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// WEB-INF/lib/에 파일 추가
import org.mindrot.jbcrypt.BCrypt;

public class MemberDAO {
	public static final int ID_PASSWORD_MATCH = 1;
	public static final int ID_DOES_NOT_EXIST = 2;
	public static final int PASSWORD_IS_WRONG = 3;
	public static final int DATABASE_ERROR = -1;	//값 주의.
	
	private Connection conn;						//[1]Connection 인터페이스의 참조변수
	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	//[2]JDBC URL : jdbc:mysql:// IP주소:PORT(옵션)/스키마(=데이터베이스 이름)
	private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
	
	
	//[3]동적로딩 
	//: 불특정 클래스 로딩을 위해 Class클래스의 forName함수를 이용하여
	//	해당 클래스를 메모리로 로드 (어떤 객체를 생성해서 처리를 해야될지 모르는 경우)
	
	//[4]DB연결
	//java.sql.DriverManager The basic service for managing a set of JDBC drivers.
	//The use of a DataSource object is the preferred means of connecting to a data source.
	public MemberDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //[3]동적로딩 : jdbc 드라이버 로드
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); //[4]DB연결 : Connection 클래스의 인스턴스 필요, method 이용
		} catch (Exception ex) { //ClassNotFoundException, SQLException
			ex.printStackTrace();
		}
	}
	
	//페이지 카운터 관련-----------------------------------------------------------
	public int getCount() {
		String query = "select count(*) from member;";
		PreparedStatement pStmt = null;
		int count = 0;
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {				
				count = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return count;
	}
	
	public List<MemberDTO> makeMemList(int page) {
		int offset = 0;
		String query = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			query = "select id, name, birthday, address from member";
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			query = "select id, name, birthday, address from member limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			pStmt = conn.prepareStatement(query);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {	
				MemberDTO mDto = new MemberDTO();
				mDto.setId(rs.getInt(1));
				mDto.setName(rs.getString(2));
				mDto.setBirthday(rs.getString(3));
				mDto.setAddress(rs.getString(4));
				memberList.add(mDto);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return memberList;
	}
	
	
	//password확인 : plain --> hashed password
	public int verifyIdPassword(int id, String password) {
		System.out.println("verifyIdPassword(): " +id + ", " +password);
		String query = "select hashed from member where id=?"; //[5]DB에서 검색하는 쿼리문
		
		PreparedStatement pStmt = null;
		ResultSet rs =null;
//		String dbPassword ="";
		String hashedPassword ="";
	
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			rs= pStmt.executeQuery();
			while(rs.next()) {
//				dbPassword = rs.getString(1);
//				if(dbPassword.equals(password))
				hashedPassword = rs.getString(1);
				if(BCrypt.checkpw(password, hashedPassword))
					return ID_PASSWORD_MATCH;
				else
					return PASSWORD_IS_WRONG;
			}
			return ID_DOES_NOT_EXIST;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return DATABASE_ERROR;
	}
	
	
	//Insert
	public void insertMember(MemberDTO member) {
		//sql구문 : 모든 column에 입력하지 않을 시, 입력할 column을 명시하여야 한다.
		String query = "insert into member(password, name, birthday, address, hashed) values(?,?,?,?,?);";
		PreparedStatement pStmt = null;
		
		try {
			String hashedPassword = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, "*");
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getBirthday());
			pStmt.setString(4, member.getAddress());
			pStmt.setString(5, hashedPassword); //member객체에 없음
			
			//update, delete문 등을 수행할 때 사용. (select: executeQuery();)
			//반환값: int='처리된 데이터의 수'
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	//update
	public void updateMember(MemberDTO member) {
		String query = "update member set password=?, name=?, birthday=?, address=? where id=?;";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(query); //prepareStatement객체 생성 (Connection 클래스의 method로 생성)
			pStmt.setString(1, member.getPassword()); //쿼리문의 wildcard에 값을 넣어줌
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getBirthday());
			pStmt.setString(4, member.getAddress());
			pStmt.setInt(5, member.getId());
			
			//update, delete문 등을 수행할 때 사용. (select: executeQuery();)
			//반환값: int='처리된 데이터의 수'
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//delete
//	public void deleteMember(MemberDTO member) {
	public void deleteMember(int id) {
		//매개변수: DTO로 받아도 되고, 정수값으로 받아도 됨. (int num) 
		String query = "delete from member where id=?;";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(query);
//			pStmt.setInt(1, member.getId());
			pStmt.setInt(1, id);
			
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//최근 추가된 멤버
	public MemberDTO recentId() {
		String sql = "select * from member order by id desc limit 1;";
		MemberDTO mDto = selectOne(sql);
		return mDto;
	}
	
	//close()
	public void close() {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//select * from member where id
	public MemberDTO selectMemberById(int id) {
		String query = "select * from member where id="+ id +";";
		MemberDTO member = selectOne(query);
		return member;
	}
	
	//select *
	public List<MemberDTO> selectAll() {
//		String query = "select * from member order by id desc;";
		String query = "select * from member order by id asc;";
		List<MemberDTO> memberList = selectCondition(query);
		return memberList;
	}

	//select * from member where name like ...
	public List<MemberDTO> selectMembersByName(String name) {
		String query = "select * from member where name like '"+ name + "' order by id asc;";
		List<MemberDTO> memberList = selectCondition(query);
		return memberList;
	}

	
//Selection(2EA)-------------------------------------------------------------------------
	//(1)쿼리문에 따라 한종류만 선택
	public MemberDTO selectOne(String query) {
		MemberDTO member = new MemberDTO();
    	PreparedStatement pStmt = null;
    	
    	try {
			pStmt = conn.prepareStatement(query);
			
			
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				member.setId(rs.getInt(1));
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setBirthday(rs.getString(4));
				member.setAddress(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null && !pStmt.isClosed()) 
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
    	return member;
    }
			
	//(2)쿼리문에 따른 리스트 반환
	public List<MemberDTO> selectCondition(String query) { //selectAll변경
		PreparedStatement pStmt = null;
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) { //DB에서 data수신
				MemberDTO member = new MemberDTO();
				member.setId(rs.getInt(1));
				member.setPassword(rs.getString(2));
				member.setName(rs.getString(3));
				member.setBirthday(rs.getString(4));
				member.setAddress(rs.getString(5));
				memberList.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return memberList;
	}
//end selection-------------------------------------------------------------------------------
	

//password 암호화(BCrypt, 2EA)-----------------------------------------------------------------------
	//(1) 암호화
	public void initPassword() {
		List<MemberDTO> mList = selectAll(); //리스트를 전부 가져옴
		for(MemberDTO member: mList) {
			int id = member.getId();
			String plainPassword = member.getPassword();
			String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
			updatePassword(id, hashedPassword);
		}
	}
	
	//(2)DB에 update
	public void updatePassword(int id, String hashed) {
		String query = "update member set hashed=? where id=?;";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, hashed);
			pStmt.setInt(2, id);
			
			pStmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
//end password암호화---------------------------------------------------------------------


//Create Table(1EA)---------------------------------------------------------------------------
	public void createTable() { //col항목을 받아 생성하는 메소드로 수정할 것.
		String query = "create table if not exists member (" + 
						"id int(6) unsigned not null auto_increment," + 
						"password varchar(10) not null," + 
						"name varchar(10) not null," + 
						"birthday date not null default 0," + 
						"address varchar(50)," + 
						"primary key(id)" + 
						") auto_increment=10001 default charset=utf8;";
		
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null && !pStmt.isClosed())
					pStmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
//-----------------------------------------------------------------------------------------
}
