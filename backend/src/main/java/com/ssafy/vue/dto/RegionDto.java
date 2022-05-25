package com.ssafy.vue.dto;

public class RegionDto {
	

	int interestId;
	String userId;
	String sidoCode;
	String sigugunCode;
	String dongCode;
	String areaName;
	int interestCount;
	public int getInterestId() {
		return interestId;
	}
	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSigugunCode() {
		return sigugunCode;
	}
	public void setSigugunCode(String sigugunCode) {
		this.sigugunCode = sigugunCode;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getInterestCount() {
		return interestCount;
	}
	public void setInterestCount(int interestCount) {
		this.interestCount = interestCount;
	}
	@Override
	public String toString() {
		return "RegionDto [interestId=" + interestId + ", userId=" + userId + ", sidoCode=" + sidoCode
				+ ", sigugunCode=" + sigugunCode + ", dongCode=" + dongCode + ", areaName=" + areaName
				+ ", interestCount=" + interestCount + "]";
	}

}

