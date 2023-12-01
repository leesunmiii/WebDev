package com.sist.dao;
// NCS(취업우선) / K-digital(취업률 상관안함)-우리꺼
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	private static GoodsDAO dao;
	private String[] tables= {"","goods_all","goods_special","goods_best","goods_new"};
	private final int ROW=12;
	
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext(); // 탐색기 열기
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
			
		}catch(Exception ex) {}
	}
	
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	public static GoodsDAO newInstance()
	{
		if(dao==null)
			dao=new GoodsDAO();
		return dao;
	}
	
	//목록
	public List<GoodsVO> goodsListData(int page, int type)
	{
		List<GoodsVO> list=new ArrayList<GoodsVO>();
		try
		{
			getConnection();
			String sql="SELECT no,goods_name,goods_poster,num "
					+"FROM (SELECT no,goods_name,goods_poster,rownum as num "
					+"FROM (SELECT no,goods_name,goods_poster "
					+"FROM "+tables[type]+" ORDER BY no ASC)) "// 테이블 4개를 써야해서 order by 써야함
					+"WHERE num BETWEEN ? AND ?";
					// setString(1,"goods_all") 사용하면 자동으로 ''가 붙음 ('goods_all')
			// 실제 값이 들어갈 때만 ? 사용
			// 컬럼과 테이블명은 ? 안함
			
			ps=conn.prepareStatement(sql);
			int start=(page*ROW)-(ROW-1);
			int end=page*ROW;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPoster(rs.getString(3));
				list.add(vo);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	
	public int goodsTotalPage(int type)
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/"+ROW+") FROM "+tables[type];
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
			disConnection();
		}
		return total;
	}
	
	// 상세보기
	/*
 NO                                        NOT NULL NUMBER
 GOODS_NAME                                NOT NULL VARCHAR2(1000)
 GOODS_SUB                                          VARCHAR2(1000)
 GOODS_PRICE                               NOT NULL VARCHAR2(50)
 GOODS_DISCOUNT                                     NUMBER
 GOODS_FIRST_PRICE                                  VARCHAR2(20)
 GOODS_DELIVERY                            NOT NULL VARCHAR2(20)
 GOODS_POSTER                                       VARCHAR2(260)
 HIT                                                NUMBER
	 */
	public GoodsVO goodsDetailData(int no, int type)
	{
		GoodsVO vo=new GoodsVO();
		try
		{
			getConnection();
			String sql="SELECT no,goods_name,goods_sub,goods_price,goods_discount,goods_first_price"
					+",goods_delivery,goods_poster "
					+"FROM "+tables[type]+" "
					+"WHERE no="+no;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSub(rs.getString(3));
			vo.setPrice(rs.getString(4));
			vo.setDiscount(rs.getInt(5));
			vo.setFprice(rs.getString(6));
			vo.setDelivery(rs.getString(7));
			vo.setPoster(rs.getString(8));
			rs.close();
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
