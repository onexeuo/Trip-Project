package tot.domain;

import java.sql.Timestamp;

public class TripVO {

	private int tripId; // 여행 아이디
	private String memId; // 회원 아이디
	private String areaCode; // 지역 코드
	private String tripName; // 여행 이름
	private int trAmt; // 여행 예상 비용
	private Timestamp trStaDate; // 여행 시작일
	private Timestamp trEndDate; // 여행 도착일
	private String trPeriod; // 여행 기간
	private int trPeople; // 인원수
	private String trImgpath; // 여행 이미지 경로

	public TripVO() {
	}

	public TripVO(int tripId, String memId, String areaCode, String tripName, int trAmt, Timestamp trStaDate,
			Timestamp trEndDate, String trPeriod, int trPeople, String trImgpath) {
		this.tripId = tripId;
		this.memId = memId;
		this.areaCode = areaCode;
		this.tripName = tripName;
		this.trAmt = trAmt;
		this.trStaDate = trStaDate;
		this.trEndDate = trEndDate;
		this.trPeriod = trPeriod;
		this.trPeople = trPeople;
		this.trImgpath = trImgpath;
	}

	public int getTripId() {
		return tripId;
	}

	public String getMemId() {
		return memId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getTripName() {
		return tripName;
	}

	public int getTrAmt() {
		return trAmt;
	}

	public Timestamp getTrStaDate() {
		return trStaDate;
	}

	public Timestamp getTrEndDate() {
		return trEndDate;
	}

	public String getTrPeriod() {
		return trPeriod;
	}

	public int getTrPeople() {
		return trPeople;
	}

	public String getTrImgpath() {
		return trImgpath;
	}

	@Override
	public String toString() {
		return "TripVO [tripId=" + tripId + ", memId=" + memId + ", areaCode=" + areaCode + ", tripName=" + tripName
				+ ", trAmt=" + trAmt + ", trStaDate=" + trStaDate + ", trEndDate=" + trEndDate + ", trPeriod="
				+ trPeriod + ", trPeople=" + trPeople + ", trImgpath=" + trImgpath + "]";
	}

}
