package tot.domain;

import java.sql.Timestamp;
import java.util.List;

public class Trip {
    private int tripid;
    private String memid;
    private String areaCode;
    private String tripname;
    private int tramt;
    private Timestamp trstadate;
    private Timestamp trenddate;
    private int trpeople;
    private String trimgpath;
    private List<CourseDTO> courses;
    
    public Trip() {
	}
    
    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

	public int getTripid() {
		return tripid;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getTripname() {
		return tripname;
	}

	public void setTripname(String tripname) {
		this.tripname = tripname;
	}

	public int getTramt() {
		return tramt;
	}

	public void setTramt(int tramt) {
		this.tramt = tramt;
	}

	public Timestamp getTrstadate() {
		return trstadate;
	}

	public void setTrstadate(Timestamp trstadate) {
		this.trstadate = trstadate;
	}

	public Timestamp getTrenddate() {
		return trenddate;
	}

	public void setTrenddate(Timestamp trenddate) {
		this.trenddate = trenddate;
	}

	public int getTrpeople() {
		return trpeople;
	}

	public void setTrpeople(int trpeople) {
		this.trpeople = trpeople;
	}

	public String getTrimgpath() {
		return trimgpath;
	}

	public void setTrimgpath(String trimgpath) {
		this.trimgpath = trimgpath;
	}

	public void setTripid(int tripid) {
		this.tripid = tripid;
	}

	public Trip(int tripid, String memid, String areaCode, String tripname, int tramt, Timestamp trstadate,
			Timestamp trenddate, int trpeople, String trimgpath, List<CourseDTO> courses) {
		super();
		this.tripid = tripid;
		this.memid = memid;
		this.areaCode = areaCode;
		this.tripname = tripname;
		this.tramt = tramt;
		this.trstadate = trstadate;
		this.trenddate = trenddate;
		this.trpeople = trpeople;
		this.trimgpath = trimgpath;
		this.courses = courses;
	}

	
    
	
    

}
