package tot.domain;

public class LodgingDTO {
	private int lodid;                // 숙소 아이디
	private String lg001;             // 코드 (LG_001)
	private String areacode;          // 지역코드
	private String lodname;           // 숙소 이름
	private String lodaddress;        // 숙소 주소
	private String lodurl;            // 숙소 URL
	private String lodprice;          // 숙소 가격
	private String lodimgpath;        // 숙소 이미지 경로
	
	public LodgingDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public LodgingDTO(int lodid, String lg001, String areacode, String lodname, String lodaddress, String lodurl,
			String lodprice, String lodimgpath) {
		super();
		this.lodid = lodid;
		this.lg001 = lg001;
		this.areacode = areacode;
		this.lodname = lodname;
		this.lodaddress = lodaddress;
		this.lodurl = lodurl;
		this.lodprice = lodprice;
		this.lodimgpath = lodimgpath;
	}

	public int getLodid() {
		return lodid;
	}

	public String getLg001() {
		return lg001;
	}

	public String getAreacode() {
		return areacode;
	}

	public String getLodname() {
		return lodname;
	}

	public String getLodaddress() {
		return lodaddress;
	}

	public String getLodurl() {
		return lodurl;
	}

	public String getLodprice() {
		return lodprice;
	}

	public String getLodimgpath() {
		return lodimgpath;
	}

	public void setLodid(int lodid) {
		this.lodid = lodid;
	}

	public void setLg001(String lg001) {
		this.lg001 = lg001;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public void setLodname(String lodname) {
		this.lodname = lodname;
	}

	public void setLodaddress(String lodaddress) {
		this.lodaddress = lodaddress;
	}

	public void setLodurl(String lodurl) {
		this.lodurl = lodurl;
	}

	public void setLodprice(String lodprice) {
		this.lodprice = lodprice;
	}

	public void setLodimgpath(String lodimgpath) {
		this.lodimgpath = lodimgpath;
	}

	@Override
	public String toString() {
		return "LodgingDTO [lodid=" + lodid + ", lg001=" + lg001 + ", areacode=" + areacode + ", lodname=" + lodname
				+ ", lodaddress=" + lodaddress + ", lodurl=" + lodurl + ", lodprice=" + lodprice + ", lodimgpath="
				+ lodimgpath + "]";
	}
	
	
	
}
