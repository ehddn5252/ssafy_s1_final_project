package com.ssafy.vue.dto;

public class BaseAddressDto {
	int no;
	String sidoName;
	String gugunName;
	String dongName;
	String dongCode;
	String lat;
	String lng;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
	@Override
	public String toString() {
		return "BaseAddressDto [no=" + no + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName="
				+ dongName + ", dongCode=" + dongCode + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}