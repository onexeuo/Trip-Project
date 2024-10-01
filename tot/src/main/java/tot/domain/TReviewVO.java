package tot.domain;

import java.sql.Timestamp;

import tot.common.enums.Flag;

public class TReviewVO {

	private int trevId; // 여행후기 게시글 아이디
	private int tripId; // 여행 아이디
	private String memId; // 회원 아이디
	private Flag trevStatus; // 게시여부코드
	private String trevTitle; // 제목
	private String trevContent; // 내용
	private String trevRating; // 평점
	private Timestamp trevRegdate; // 작성일시
	private Timestamp trevUpdate; // 수정일시
	private String trevCount; // 조회수

	public TReviewVO() {
	}

	public TReviewVO(int trevId, int tripId, String memId, Flag trevStatus, String trevTitle, String trevContent,
			String trevRating, Timestamp trevRegdate, Timestamp trevUpdate, String trevCount) {
		this.trevId = trevId;
		this.tripId = tripId;
		this.memId = memId;
		this.trevStatus = trevStatus;
		this.trevTitle = trevTitle;
		this.trevContent = trevContent;
		this.trevRating = trevRating;
		this.trevRegdate = trevRegdate;
		this.trevUpdate = trevUpdate;
		this.trevCount = trevCount;
	}

	public static TReviewVO fromDTO(TReviewReqDTO dto) {
		return new TReviewVO(0, dto.getTripId(), dto.getMemId(), Flag.CMT001, dto.getTrevTitle(), dto.getTrevContent(),
				dto.getTrevRating(), null, null, "0");
	}

	public static TReviewVO fromDTO(TReviewReqDTO reqDto, TReviewResDTO resDto) {
		return new TReviewVO(reqDto.getTrevId(), reqDto.getTripId(), resDto.getMemId(), resDto.getTrevStatus(),
				reqDto.getTrevTitle(), reqDto.getTrevContent(), reqDto.getTrevRating(), resDto.getTrevRegdate(), null,
				resDto.getTrevCount());
	}

	public static TReviewVO fromDTO(TReviewReqDTO reqDto, TReviewResDTO resDto, Flag trevStatus) {
		return new TReviewVO(reqDto.getTrevId(), reqDto.getTripId(), resDto.getMemId(), trevStatus,
				reqDto.getTrevTitle(), reqDto.getTrevContent(), reqDto.getTrevRating(), resDto.getTrevRegdate(), null,
				resDto.getTrevCount());
	}

	public int getTrevId() {
		return trevId;
	}

	public int getTripId() {
		return tripId;
	}

	public String getMemId() {
		return memId;
	}

	public Flag getTrevStatus() {
		return trevStatus;
	}

	public String getTrevTitle() {
		return trevTitle;
	}

	public String getTrevContent() {
		return trevContent;
	}

	public String getTrevRating() {
		return trevRating;
	}

	public Timestamp getTrevRegdate() {
		return trevRegdate;
	}

	public Timestamp getTrevUpdate() {
		return trevUpdate;
	}

	public String getTrevCount() {
		return trevCount;
	}

	@Override
	public String toString() {
		return "TReviewVO [trevId=" + trevId + ", tripId=" + tripId + ", memId=" + memId + ", trevStatus=" + trevStatus
				+ ", trevTitle=" + trevTitle + ", trevContent=" + trevContent + ", trevRating=" + trevRating
				+ ", trevRegdate=" + trevRegdate + ", trevUpdate=" + trevUpdate + ", trevCount=" + trevCount + "]";
	}

}
