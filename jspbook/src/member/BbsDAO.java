package member;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BbsDAO {
	private static final Logger LOG = LoggerFactory.getLogger(BbsDAO.class);

	private Connection conn;
	private static final String USERNAME = "javauser";
	private static final String PASSWORD = "javapass";
	private static final String URL = "jdbc:mysql://localhost:3306/world?verifyServerCertificate=false&useSSL=false";
	
	
	//[1]동적로딩 
	//: 불특정 클래스 로딩을 위해 Class클래스의 forName함수를 이용하여
	//	해당 클래스를 메모리로 로드 (어떤 객체를 생성해서 처리 해야될지 모르는 경우)
	public BbsDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");	//[1]동적로딩
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//목록 다운로더
	public String prepareDownload(String fileName) {
		LOG.trace("");
		StringBuffer sb = new StringBuffer();
		List<BbsMember> bmList = selectJoinAll(0);
		
//		try {
//			FileWriter fw = new FileWriter("c:/tmp/"+fileName+".csv");
			String head = "글번호,제목,작성자,최종수정시각\r\n";
			sb.append(head);
//			fw.write(head);
			LOG.debug(head);
			for(BbsMember bMem : bmList) {
				String line = bMem.getId() + "," +bMem.getTitle() + "," +bMem.getName() + "," +bMem.getDate() +"\r\n";
				sb.append(line);
//				fw.write(line);
				LOG.debug(line);
			}
//			fw.flush();
//			fw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
			//확인결과 sb는 넘어감 --> 서블릿에서 문제있음
			//System.out.println("FileDownloader() : \n" + sb.toString());
		return sb.toString();
		
	}
	
	//페이지 카운터 관련-----------------------------------------------------------
	public int getCount() {
		String query = "select count(*) from bbs_table;";
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
	
	public List<BbsMember> selectJoinAll(int page) {
		int offset = 0;
		String query = null;
		if (page == 0) {	// page가 0이면 모든 데이터를 보냄
			query = "select b.id, b.title, m.name, b.date from bbs_table as b " + 
					"inner join member as m on b.memberId=m.id order by b.id desc;";
		} else {			// page가 0이 아니면 해당 페이지 데이터만 보냄
			query = "select b.id, b.title, m.name, b.date from bbs_table as b " + 
					"inner join member as m on b.memberId=m.id order by b.id desc limit ?, 10;";
			offset = (page - 1) * 10;
		}
		PreparedStatement pStmt = null;
		List<BbsMember> bmList = new ArrayList<BbsMember>();
		try {
			pStmt = conn.prepareStatement(query);
			if (page != 0)
				pStmt.setInt(1, offset);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {	
				BbsMember bmDto = new BbsMember();
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4));
				bmList.add(bmDto);
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
		return bmList;
	}
	//------------------------------------------------------------------------------------------------
		
	
	//Insert
	public void insertText(BbsDTO insText) {
		String query = "insert into bbs_table(memberId, title, content) values(?,?,?);";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, insText.getMemberId());
			pStmt.setString(2, insText.getTitle());
			pStmt.setString(3, insText.getContent());
			
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
	
	//BbsMain화면에 보여줄 목록검색 : number는 한페이지당 보여줄 데이터의 수.
	public List<BbsMember> selectContentsAll(int number) {
		String query = "select b.id, b.title, m.name, b.date from bbs_table as b "+
						"join member as m on b.memberId=m.id order by b.date desc limit ?;";
		PreparedStatement pStmt = null;
		List<BbsMember> bbsMemList = new ArrayList<BbsMember>();
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, number);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) { //DB에서 data수신
				BbsMember bmDto = new BbsMember();
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4));
				bbsMemList.add(bmDto);
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
		return bbsMemList;
	}
	
	//04.24 추가
	public List<BbsMember> selectContentsByMemberId(int memberId, int number) {
		String query = "select b.id, b.title, m.name, b.date from bbs_table as b "+
						"join member as m on b.memberId=m.id where b.memberId=? order by b.date desc limit ?;";
		PreparedStatement pStmt = null;
		List<BbsMember> bbsMemList = new ArrayList<BbsMember>();
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, memberId);
			pStmt.setInt(2, number);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) { //DB에서 data수신
				BbsMember bmDto = new BbsMember();
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4));
				bbsMemList.add(bmDto);
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
		return bbsMemList;
	}
	
	//update
	public void updateText(BbsDTO updateText, int memberId) {
		String query = "update bbs_table as b inner join member as m "+
						"on b.memberId = m.id "+
						"set b.title=?, b.content=? where b.id=? and m.id="+memberId+";";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, updateText.getTitle());
			pStmt.setString(2, updateText.getContent());
			pStmt.setInt(3, updateText.getId());
			
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
	public void deleteText(int id) { //매개변수: DTO로 받아도 되고, 정수값으로 받아도 됨. (int num) 
		String query = "delete from bbs_table where id=?;";
		PreparedStatement pStmt = null;
		
		try {
			pStmt = conn.prepareStatement(query);
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
	
	//detail search
	public BbsMember detailsearch(int id) {
		String query = "select b.id, b.title, m.name, b.date, b.content from bbs_table as b " + 
				"join member as m on b.memberId=m.id where b.id=?;";
		PreparedStatement pStmt = null;
		BbsMember bmDto = new BbsMember();
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {				
				bmDto.setId(rs.getInt(1));
				bmDto.setTitle(rs.getString(2));
				bmDto.setName(rs.getString(3));
				bmDto.setDate(rs.getString(4).substring(0,16));
				bmDto.setContent(rs.getString(5));
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
		return bmDto;
	}
	
	
	//select * from bbs_table where id --> join으로 memberId로 연동하여 하나를 선택, 다른메소드에 전달.
	public BbsDTO selectContentById(int id) {
		String query = "select * from bbs_table where id="+ id +";";
		BbsDTO content = selectOne(query);
		return content;
	}
	
	//select * from bbs_table where memberId
	public List<BbsDTO> selectListByMemberId(int memberId) {
		String query = "select * from bbs_table where memberId="+ memberId +" order by date asc;";
		List<BbsDTO> contentsList = selectCondition(query);
		return contentsList;
	}

		
	//---[Selection]---------------------------------------------------------	
	//쿼리문에 따라 하나만 선택
	public BbsDTO selectOne(String query) {
		BbsDTO bDto = new BbsDTO();
    	PreparedStatement pStmt = null;
    	
    	try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while (rs.next()) {
				bDto.setId(rs.getInt(1));
				bDto.setMemberId(rs.getInt(2));
				bDto.setTitle(rs.getString(3));
				bDto.setDate(rs.getString(4));
				bDto.setContent(rs.getString(5));
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
    	return bDto;
    }
			
	//쿼리문에 따른 리스트 반환
	public List<BbsDTO> selectCondition(String query) { //selectAll변경
		PreparedStatement pStmt = null;
		List<BbsDTO> bbsList = new ArrayList<BbsDTO>();
		
		try {
			pStmt = conn.prepareStatement(query);
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) { //DB에서 data수신
				BbsDTO bDto = new BbsDTO();
				bDto.setId(rs.getInt(1));
				bDto.setMemberId(rs.getInt(2));
				bDto.setTitle(rs.getString(3));
				bDto.setDate(rs.getString(4));
				bDto.setContent(rs.getString(5));
				bbsList.add(bDto);
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
		return bbsList;
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
			
	//Create Table
	public void createTable() { //col항목을 받아 생성하는 메소드로 수정할 것.
		String query = "create table if not exists bbs_table (" + 
						"id int(6) unsigned not null auto_increment," + 
						"memberId int(6) unsigned not null," + 
						"title varchar(50) not null," + 
						"date datetime not null default current_timestamp "+ //default now()도 가능
						"ON UPDATE CURRENT_TIMESTAMP," + 
						"content varchar(400)," + 
						"primary key(id)," + 
						"foreign key(memberId) references member(id)"+
						")default charset=utf8;";
		
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
}
