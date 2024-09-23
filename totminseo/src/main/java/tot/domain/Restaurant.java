package tot.domain;

public class Restaurant {

	private int restid;
	private String restname;
	private String restaurant_001;
	private String restaddress;
	private String areacode;
	private String region_001;
	
	public Restaurant() {
	}

	public Restaurant(int restid, String restname, String restaurant_001, String restaddress, String areacode,
			String region_001) {
		super();
		this.restid = restid;
		this.restname = restname;
		this.restaurant_001 = restaurant_001;
		this.restaddress = restaddress;
		this.areacode = areacode;
		this.region_001 = region_001;
	}

	public int getRestid() {
		return restid;
	}

	public void setRestid(int restid) {
		this.restid = restid;
	}

	public String getRestname() {
		return restname;
	}

	public void setRestname(String restname) {
		this.restname = restname;
	}

	public String getRestaurant_001() {
		return restaurant_001;
	}

	public void setRestaurant_001(String restaurant_001) {
		this.restaurant_001 = restaurant_001;
	}

	public String getRestaddress() {
		return restaddress;
	}

	public void setRestaddress(String restaddress) {
		this.restaddress = restaddress;
	}

	public String getAreacode() {
		return areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getRegion_001() {
		return region_001;
	}

	public void setRegion_001(String region_001) {
		this.region_001 = region_001;
	}

	@Override
	public String toString() {
		return "Restaurant [restid=" + restid + ", restname=" + restname + ", restaurant_001=" + restaurant_001
				+ ", restaddress=" + restaddress + ", areacode=" + areacode + ", region_001=" + region_001 + "]";
	}

	
	
	
	
	
}
