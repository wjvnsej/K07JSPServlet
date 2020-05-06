package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.sun.org.apache.regexp.internal.recompile;

public class BbsDAO {
	
	//멤버변수 (클래스 전체 멤버메소드에서 접근가능)
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	//인자생성자 : DB연결
	public BbsDAO(String driver, String url) {
		try {
			Class.forName(driver);
			String id = "kosmo";
			String pw = "1234";
			con =DriverManager.getConnection(url, id, pw);
			System.out.println("DB연결성공");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//DB자원해제
	public void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(con != null) con.close();
		} 
		catch (Exception e) {
			System.out.println("자원반납시 예외발생");
			e.printStackTrace();
		}
	}
	
	
	/*
	게시판 리스트에서 게시물의 갯수를 count()함수를 통해 구해서 반환함.
	가상번호, 페이지번호 처리를 위해 사용됨.
	*/
	public int getTotalRecordCount(Map<String, Object> map) {
		//게시물의 수는 0으로 초기화
		int totalCount = 0;
		
		//기본쿼리문(전체레코드를 대상으로 함)
		String query = "SELECT COUNT(*) FROM board";
		//JSP페이지에서 검색어를 입력한 경우 where절이 동적으로 추가됨.
		if(map.get("Word") != null) {
			query += " WHERE " + map.get("Column") + " " 
					+ " LIKE '%" + map.get("Word") + "%'";
		}
		System.out.println("query = " + query);
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			rs.next();
			//반환한 결과값(레코드 수)을 저장
			totalCount = rs.getInt(1);
		}
		catch (Exception e) { }
		return totalCount;
	}
	
	/*
	게시판 리스트에서 조건에 맞는 레코드를 select하여 ResultSet(결과셋)을
	List컬렉션에 저장 후 반환하는 메소드
	*/
	public List<BbsDTO> selectList(Map<String, Object> map) {
		
		List<BbsDTO> bbs = new Vector<BbsDTO>();
		//기본쿼리문
		String query = "SELECT * FROM board ";
		
		//검색어가 있는 경우 조건절 동적 추가
		if(map.get("Word") != null) {
			query += " WHERE " + map.get("Column") + " " 
					+ " LIKE '%" + map.get("Word") + "%'";
		}
		
		//최근게시물이  항상 위로 노출되야 하므로 작성된 순서의 역순으로 정렬한다.
		query += " ORDER BY num DESC";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			//오라클이 반환해준 ResultSet의 갯수만큼 반복한다.
			while(rs.next()) {
				//하나의 레코드를 DTO객체에 저장하기 위해 새로운 객체 생성
				BbsDTO dto = new BbsDTO();
				
				//setter()메소드를 사용하여 컬럼에 데이터 저장
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString(3));
				dto.setPostDate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				
				//저장된 DTO객체를 List컬렉션에 추가
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("SELECT시 예외발생");
			e.printStackTrace();
		}
		return bbs;
	}

}
















