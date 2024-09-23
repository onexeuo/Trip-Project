package tot.domain;

import java.sql.Timestamp;

public class CourseDTO {
    
    private int courid;         // 코스 아이디
    private int tripid;         // 여행 아이디
    private String areacode;    // 지역 코드
    private String dcourse;     // 하루 코스 설명 문자열
    private Timestamp courregdate; // 등록일시
    private Timestamp courupdate;  // 수정일시

    // 기본 생성자
    public CourseDTO() {
    }

    // 매개변수가 있는 생성자
    public CourseDTO(int courid, int tripid, String areacode, String dcourse, Timestamp courregdate, Timestamp courupdate) {
        this.courid = courid;
        this.tripid = tripid;
        this.areacode = areacode;
        this.dcourse = dcourse;
        this.courregdate = courregdate;
        this.courupdate = courupdate;
    }

    // Getter and Setter methods
    public int getCourid() {
        return courid;
    }

    public void setCourid(int courid) {
        this.courid = courid;
    }

    public int getTripid() {
        return tripid;
    }

    public void setTripid(int tripid) {
        this.tripid = tripid;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getDcourse() {
        return dcourse;
    }

    public void setDcourse(String dcourse) {
        this.dcourse = dcourse;
    }

    public Timestamp getCourregdate() {
        return courregdate;
    }

    public void setCourregdate(Timestamp courregdate) {
        this.courregdate = courregdate;
    }

    public Timestamp getCourupdate() {
        return courupdate;
    }

    public void setCourupdate(Timestamp courupdate) {
        this.courupdate = courupdate;
    }

    @Override
    public String toString() {
        return "CourseDTO [courid=" + courid + ", tripid=" + tripid + ", areacode=" + areacode + ", dcourse=" + dcourse
                + ", courregdate=" + courregdate + ", courupdate=" + courupdate + "]";
    }
}
