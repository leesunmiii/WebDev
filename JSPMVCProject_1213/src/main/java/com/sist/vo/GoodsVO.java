package com.sist.vo;
// 데이터베이스의 데이터를 모아서 한번에 전송할 목적 => ~bin :JSP, ~DTO : 마이바티스 ,~VO:spring
/*
 * 
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
public class GoodsVO {
	private int gno,selling_price,original_price,mileage;
	private String title,poster,brand,delivery_price,after_service;
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public int getSelling_price() {
		return selling_price;
	}
	public void setSelling_price(int selling_price) {
		this.selling_price = selling_price;
	}
	public int getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(int original_price) {
		this.original_price = original_price;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDelivery_price() {
		return delivery_price;
	}
	public void setDelivery_price(String delivery_price) {
		this.delivery_price = delivery_price;
	}
	public String getAfter_service() {
		return after_service;
	}
	public void setAfter_service(String after_service) {
		this.after_service = after_service;
	}
	
	
}
