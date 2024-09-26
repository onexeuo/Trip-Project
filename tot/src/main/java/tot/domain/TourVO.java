package tot.domain;

public class TourVO {

	private String toId; // 관광지 아이디
	private String areaCode; // 지역코드
	private String tourType; // 관광지 유형코드
	private String toName; // 관광지명
	private String toAddress; // 주소
	private String toDetailAddress; // 상세주소
	private String toTime; // 운영시간
	private String toTel; // 전화번호
	private String toHomepage; // 홈페이지
	private String toOverview; // 콘텐츠 개요
	private String toImgpath; // 이미지 경로
	private String tox; // x좌표
	private String toy; // y좌표

	public TourVO() {
	}

	public TourVO(String toId, String areaCode, String tourType, String toName, String toAddress,
			String toDetailAddress, String toTime, String toTel, String toHomepage, String toOverview, String toImgpath,
			String tox, String toy) {
		this.toId = toId;
		this.areaCode = areaCode;
		this.tourType = tourType;
		this.toName = toName;
		this.toAddress = toAddress;
		this.toDetailAddress = toDetailAddress;
		this.toTime = toTime;
		this.toTel = toTel;
		this.toHomepage = toHomepage;
		this.toOverview = toOverview;
		this.toImgpath = toImgpath;
		this.tox = tox;
		this.toy = toy;
	}

	public String getToId() {
		return toId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getTourType() {
		return tourType;
	}

	public String getToName() {
		return toName;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getToDetailAddress() {
		return toDetailAddress;
	}

	public String getToTime() {
		return toTime;
	}

	public String getToTel() {
		return toTel;
	}

	public String getToHomepage() {
		return toHomepage;
	}

	public String getToOverview() {
		return toOverview;
	}

	public String getToImgpath() {
		return toImgpath;
	}

	public String getTox() {
		return tox;
	}

	public String getToy() {
		return toy;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public void setToDetailAddress(String toDetailAddress) {
		this.toDetailAddress = toDetailAddress;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public void setToTel(String toTel) {
		this.toTel = toTel;
	}

	public void setToHomepage(String toHomepage) {
		this.toHomepage = toHomepage;
	}

	public void setToOverview(String toOverview) {
		this.toOverview = toOverview;
	}

	public void setToImgpath(String toImgpath) {
		this.toImgpath = toImgpath;
	}

	public void setTox(String tox) {
		this.tox = tox;
	}

	public void setToy(String toy) {
		this.toy = toy;
	}

	@Override
	public String toString() {
		return "TourResDTO [toId=" + toId + ", areaCode=" + areaCode + ", tourType=" + tourType + ", toName=" + toName
				+ ", toAddress=" + toAddress + ", toDetailAddress=" + toDetailAddress + ", toTime=" + toTime
				+ ", toTel=" + toTel + ", toHomepage=" + toHomepage + ", toOverview=" + toOverview + ", toImgpath="
				+ toImgpath + ", tox=" + tox + ", toy=" + toy + "]";
	}

}
