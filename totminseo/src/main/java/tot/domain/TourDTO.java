package tot.domain;

public class TourDTO {
    private String toid;              // 관광지 아이디
    private String areacode;          // 지역코드
    private String tourtype;          // 관광지 유형코드
    private String toname;            // 관광지명
    private String toaddress;         // 주소
    private String todetailaddress;   // 상세주소
    private String totime;            // 운영시간
    private String totel;             // 전화번호
    private String tohomepage;        // 홈페이지
    private String tooverview;        // 콘텐츠 개요 (CLOB이므로 String으로 처리)
    private String toimgpath;         // 이미지 경로
    private String tox;               // X좌표
    private String toy;               // Y좌표
    
    
    public TourDTO() {
		// TODO Auto-generated constructor stub
	}
    
	public TourDTO(String toid, String areacode, String tourtype, String toname, String toaddress,
			String todetailaddress, String totime, String totel, String tohomepage, String tooverview, String toimgpath,
			String tox, String toy) {
		super();
		this.toid = toid;
		this.areacode = areacode;
		this.tourtype = tourtype;
		this.toname = toname;
		this.toaddress = toaddress;
		this.todetailaddress = todetailaddress;
		this.totime = totime;
		this.totel = totel;
		this.tohomepage = tohomepage;
		this.tooverview = tooverview;
		this.toimgpath = toimgpath;
		this.tox = tox;
		this.toy = toy;
	}
	
	public String getToid() {
		return toid;
	}
	public String getAreacode() {
		return areacode;
	}
	public String getTourtype() {
		return tourtype;
	}
	public String getToname() {
		return toname;
	}
	public String getToaddress() {
		return toaddress;
	}
	public String getTodetailaddress() {
		return todetailaddress;
	}
	public String getTotime() {
		return totime;
	}
	public String getTotel() {
		return totel;
	}
	public String getTohomepage() {
		return tohomepage;
	}
	public String getTooverview() {
		return tooverview;
	}
	public String getToimgpath() {
		return toimgpath;
	}
	public String getTox() {
		return tox;
	}
	public String getToy() {
		return toy;
	}
	public void setToid(String toid) {
		this.toid = toid;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public void setTourtype(String tourtype) {
		this.tourtype = tourtype;
	}
	public void setToname(String toname) {
		this.toname = toname;
	}
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	public void setTodetailaddress(String todetailaddress) {
		this.todetailaddress = todetailaddress;
	}
	public void setTotime(String totime) {
		this.totime = totime;
	}
	public void setTotel(String totel) {
		this.totel = totel;
	}
	public void setTohomepage(String tohomepage) {
		this.tohomepage = tohomepage;
	}
	public void setTooverview(String tooverview) {
		this.tooverview = tooverview;
	}
	public void setToimgpath(String toimgpath) {
		this.toimgpath = toimgpath;
	}
	public void setTox(String tox) {
		this.tox = tox;
	}
	public void setToy(String toy) {
		this.toy = toy;
	}

	@Override
	public String toString() {
		return "TourDTO [toid=" + toid + ", areacode=" + areacode + ", tourtype=" + tourtype + ", toname=" + toname
				+ ", toaddress=" + toaddress + ", todetailaddress=" + todetailaddress + ", totime=" + totime
				+ ", totel=" + totel + ", tohomepage=" + tohomepage + ", tooverview=" + tooverview + ", toimgpath="
				+ toimgpath + ", tox=" + tox + ", toy=" + toy + "]";
	}

}

