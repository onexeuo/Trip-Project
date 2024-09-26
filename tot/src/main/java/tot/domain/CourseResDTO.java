package tot.domain;

public class CourseResDTO {

	private String dcourseIdType; // 하루 여행 코스별 아이디 종류
	private int dcourseId; // 하루 여행 코스별 아이디
	private String dname;
	private String daddress;

	public CourseResDTO() {
	}

	public CourseResDTO(String dcourseIdType, int dcourseId, String dname, String daddress) {
		this.dcourseIdType = dcourseIdType;
		this.dcourseId = dcourseId;
		this.dname = dname;
		this.daddress = daddress;
	}

	public String getDcourseIdType() {
		return dcourseIdType;
	}

	public int getDcourseId() {
		return dcourseId;
	}

	public String getDname() {
		return dname;
	}

	public String getDaddress() {
		return daddress;
	}

	@Override
	public String toString() {
		return "CourseResDTO [dcourseIdType=" + dcourseIdType + ", dcourseId=" + dcourseId + ", dname=" + dname
				+ ", daddress=" + daddress + "]";
	}

}
