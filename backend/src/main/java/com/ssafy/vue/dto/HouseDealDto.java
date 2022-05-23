package com.ssafy.vue.dto;

public class HouseDealDto {
	String no;
	String dealAmount;
	int dealYear;
	int dealMonth;
	int dealDay;
	String area;
	String floor;
	String cancelDealType;
	String aptCode;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getCancelDealType() {
		return cancelDealType;
	}
	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType;
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	@Override
	public String toString() {
		return "HouseDealDto [no=" + no + ", dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth="
				+ dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", cancelDealType="
				+ cancelDealType + ", aptCode=" + aptCode + "]";
	}
	
}
