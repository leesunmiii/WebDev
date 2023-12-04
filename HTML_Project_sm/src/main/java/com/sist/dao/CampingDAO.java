package com.sist.dao;
// DBCP => 미리 Connection을 연결 = POOL안에 저장
// 사용 후에 다시 POOL안에 반환해야 재사용이 가능
// Connetion 생성을 제한 => 연결시간을 줄일 수 있음
// 웹 개발을 하는 업체는 DBCP를 사용한다
// Mybatis / JPA의 기본은 DBCP
import java.util.*;
import java.sql.*;
import javax.sql.*; // 데이터베이스 정보 => DataSource
import javax.naming.*; // Context => 이름으로 객체를 찾는다
public class CampingDAO {
	//	has-a(포함클래스)
	private Connection conn; // 오라클 연결 객체
							//	오라클 서버와 연결하는 Socket
	private PreparedStatement ps; // 송수신 => SQL을 보내고 결과값을 받아오는
								 //	OutputStream / BufferedReader
	private static CampingDAO dao; // 클라이언트 1명당 한개만 사용이 가능
								// 싱글턴 => 메모리 누수 방식 => DAO
								// 싱글턴은 무조건 static => 메모리공간을 하나만 사용하니까
	// 미리 연결된 주소값 얻기
	// 메소드는 한개의 기능 수헹 => 재사용, 반복기능을 제거
	// 메소드 여러개를 반복적으로 사용하는 경우 => 클래스화
	// 클래스가 여러개 => jar 파일로 만듦
	/*
	 * 	예약
	 * 	---	
	 * 		게스트하우스 1
	 * 		호텔	2
	 * 		맛집 3
	 * 		영화 4
	 * 		---------- 한개의 테이블 : 구분자 => 한 개의 메소드로 처리가 가능
	 */
	public void getConncetion()
	{
		try
		{
			Context init=new InitialContext(); // 탐색기 열기
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			
		}catch(Exception ex) {ex.printStackTrace();}
	}
	//	반환
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	// 싱글턴
	public static CampingDAO newInstance()
	{
		if(dao==null)
			dao=new CampingDAO();
		return dao;
	}
	//==================== 여기까지 필수사항 (dao 나올 때마다 꼭 해야하는 것) ===========================
	
	// 기능 설정
	public List<CampingVO> CampingListData(int page)
	{
		List<CampingVO> list=new ArrayList<CampingVO>();
		try
		{
			// 연결 (주소값 얻기)
			getConncetion();
			// 2. SQL 문장
			String sql="SELECT mno,title,Subtitle,poster,loc,num,numbb "
					+"FROM (SELECT mno,title,Subtitle,poster,loc,num,rownum as numbb "
					+"FROM (SELECT /*+ INDEX_ASC(GoCamping GoCamping_mno_pk)*/mno,title,Subtitle,poster,loc,num "
					+"FROM GoCamping)) "
					+"WHERE numbb BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql); // 오라클로 전송
			int rowSize=12; // 한페이지에 출력할 사진 갯수 // 1page=> 1~12, 13~24 
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			// 실행요청
			ResultSet rs=ps.executeQuery();
			while(rs.next()) // 첫번째 ROW부터 읽어서 마지막까지 읽는다
			{
				CampingVO vo=new CampingVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubtitle(rs.getString(3));
				vo.setPoster(rs.getString(4));
				vo.setLoc(rs.getString(5));
				vo.setNum(rs.getString(6));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			// 에러 확인
			ex.printStackTrace();
		}
		finally
		{
			// 반환해서 재사용 가능하게 => 다른 사람이 사용가능
			disConnection();
		}
		return list;
	}
	
	
	
	
	//	총페이지
	public int CampingTotalPage()
	{
		int total=0;
		try
		{
			// 주소 얻기
			getConncetion();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
						+"FROM GoCamping";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			// 반환
			disConnection();
		}
		return total;
	}
	
	// 상세보기
	public CampingVO CampingDetailData(int mno)
	{
		CampingVO vo=new CampingVO();
		try
		{
			getConncetion();
			String sql="UPDATE GoCamping SET "
					+"hit=hit+1 "
					+"WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate(); // commit을 포함 => INSERT/UPDATE
			// SELECT => 실행된 결과를 읽어온다 => executeQuery
			// 실제 데이터를 가지고 온다
			sql="SELECT mno,title,subtitle,poster,loc,num,envir,category,season,openclose,homepage,reserve,facility,pic1,pic2,pic3,explain "
					+"FROM GoCamping "
					+"WHERE mno="+mno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSubtitle(rs.getString(3));
			vo.setPoster(rs.getString(4));
			vo.setLoc(rs.getString(5));
			vo.setNum(rs.getString(6));
			vo.setEnvir(rs.getString(7));
			vo.setCategory(rs.getString(8));
			vo.setSeason(rs.getString(9));
			vo.setOpenclose(rs.getString(10));
			vo.setHomepage(rs.getString(11));
			vo.setReserve(rs.getString(12));
			vo.setFacility(rs.getString(13));
			vo.setPic1(rs.getString(14));
			vo.setPic2(rs.getString(15));
			vo.setPic3(rs.getString(16));
			vo.setExplain(rs.getString(17));
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	
}
