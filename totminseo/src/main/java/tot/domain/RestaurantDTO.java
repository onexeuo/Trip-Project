package tot.domain;

public class RestaurantDTO {

	private int restid; // 레스토랑 아이디
	private String areacode; // 지역코드
	private String restaurant001; // 코드 (RESTAURANT_001)
	private String restname; // 레스토랑 이름
	private String restaddress; // 레스토랑 주소
	private String restx; // X 좌표
	private String resty; // Y 좌표

	public RestaurantDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public RestaurantDTO(int restid, String areacode, String restaurant001, String restname, String restaddress,
			String restx, String resty) {
		super();
		this.restid = restid;
		this.areacode = areacode;
		this.restaurant001 = restaurant001;
		this.restname = restname;
		this.restaddress = restaddress;
		this.restx = restx;
		this.resty = resty;
	}

	public int getRestid() {
		return restid;
	}

	public String getAreacode() {
		return areacode;
	}

	public String getRestaurant001() {
		return restaurant001;
	}

	public String getRestname() {
		return restname;
	}

	public String getRestaddress() {
		return restaddress;
	}

	public String getRestx() {
		return restx;
	}

	public String getResty() {
		return resty;
	}

	public void setRestid(int restid) {
		this.restid = restid;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public void setRestaurant001(String restaurant001) {
		this.restaurant001 = restaurant001;
	}

	public void setRestname(String restname) {
		this.restname = restname;
	}

	public void setRestaddress(String restaddress) {
		this.restaddress = restaddress;
	}

	public void setRestx(String restx) {
		this.restx = restx;
	}

	public void setResty(String resty) {
		this.resty = resty;
	}

	@Override
	public String toString() {
		return "RestaurantDTO [restid=" + restid + ", areacode=" + areacode + ", restaurant001=" + restaurant001
				+ ", restname=" + restname + ", restaddress=" + restaddress + ", restx=" + restx + ", resty=" + resty
				+ "]";
	}

}
