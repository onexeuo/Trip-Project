package tot.domain;

import java.sql.Timestamp;

public class TReviewImageVO {

	private int trevImgId; // 여행후기 이미지 번호
	private int trevId; // 여행 후기 게시글 아이디
	private String memId; // 회원 아이디
	private String trevImgpath; // 이미지 경로
	private Timestamp trevImgRegdate; // 업로드 일시
	private Timestamp trevImgUpdate; // 수정 일시

	public TReviewImageVO() {
	}

	public TReviewImageVO(int trevImgId, int trevId, String memId, String trevImgpath, Timestamp trevImgRegdate,
			Timestamp trevImgUpdate) {
		this.trevImgId = trevImgId;
		this.trevId = trevId;
		this.memId = memId;
		this.trevImgpath = trevImgpath;
		this.trevImgRegdate = trevImgRegdate;
		this.trevImgUpdate = trevImgUpdate;
	}

	public static TReviewImageVO fromDTO(TReviewReqDTO dto) {
		return new TReviewImageVO(0, dto.getTrevId(), dto.getMemId(), dto.getTrevImgpath(),
				new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
	}

	public int getTrevImgId() {
		return trevImgId;
	}

	public int getTrevId() {
		return trevId;
	}

	public String getMemId() {
		return memId;
	}

	public String getTrevImgpath() {
		return trevImgpath;
	}

	public Timestamp getTrevImgRegdate() {
		return trevImgRegdate;
	}

	public Timestamp getTrevImgUpdate() {
		return trevImgUpdate;
	}

	@Override
	public String toString() {
		return "TReviewImageVO [trevImgId=" + trevImgId + ", trevId=" + trevId + ", memId=" + memId + ", trevImgpath="
				+ trevImgpath + ", trevImgRegdate=" + trevImgRegdate + ", trevImgUpdate=" + trevImgUpdate + "]";
	}

}
