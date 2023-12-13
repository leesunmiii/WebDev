package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dbcp.*;
// 구매
public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static GoodsDAO dao;

	private final int ROW_SIZE=12;
	
	// 싱글턴
		public static GoodsDAO newInstance()
		{
			if(dao==null)
				dao=new GoodsDAO();
			return dao;
		}
		
	// 기능
	public List<GoodsVO> goodsListData(int page)
	{
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT gno,poster,title,original_price,num "
					+"FROM (SELECT gno,poster,title,original_price,rownum as num "
					+"FROM (SELECT gno,poster,title,original_price "
					+"FROM goods1 ORDER BY gno ASC)) "
					+"WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int start=(ROW_SIZE*page)-(ROW_SIZE-1);
			int end=ROW_SIZE*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			// setInt() 			setString() setDate...
			// ======= '' 없다	======================	'' 있다
			/*
			 * 	WHERE name='홍길동'  
			 */
			
			/*
			 * 	"WHERE name="+name => 오류
			 * 	"WHERE name='"+name"'" => 이렇게 해야함
			 */
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				GoodsVO vo=new GoodsVO();
				vo.setGno(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setOriginal_price(rs.getInt(4));
				list.add(vo);
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	// 총페이지
	public int goodsTotalPage()
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/"+ROW_SIZE+") "
					+"FROM goods1";
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
			dbconn.disConnection(conn, ps);
		}
		return total;
	}

	public GoodsVO goodsDetailData(int no)
	{
		GoodsVO vo=new GoodsVO();
		try 
		{
			conn=dbconn.getConnection();
			String sql="SELECT gno,title,poster,selling_price,original_price,mileage,brand,delivery_price,after_service FROM goods1 "
					+"WHERE gno="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setGno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setPoster(rs.getString(3));
			vo.setSelling_price(rs.getInt(4));
			vo.setOriginal_price(rs.getInt(5));
			vo.setMileage(rs.getInt(6));
			vo.setBrand(rs.getString(7));
			vo.setDelivery_price(rs.getString(8));
			vo.setAfter_service(rs.getString(9));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	
	
}
