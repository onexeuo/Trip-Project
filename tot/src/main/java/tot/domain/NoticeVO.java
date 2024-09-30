package tot.domain;

import java.sql.Timestamp;

public class NoticeVO {

	private int noId; // 공지사항 아이디
	private String noTitle; // 공지사항 제목
	private String noText; // 공지사항 내용
	private Timestamp noRegdate; // 등록일자
	private Timestamp noUpdate; // 수정일자

	public NoticeVO() {
	}

	public NoticeVO(int noId, String noTitle, String noText, Timestamp noRegdate, Timestamp noUpdate) {
		this.noId = noId;
		this.noTitle = noTitle;
		this.noText = noText;
		this.noRegdate = noRegdate;
		this.noUpdate = noUpdate;
	}

	public int getNoId() {
		return noId;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public String getNoText() {
		return noText;
	}

	public Timestamp getNoRegdate() {
		return noRegdate;
	}

	public Timestamp getNoUpdate() {
		return noUpdate;
	}

	@Override
	public String toString() {
		return "NoticeVO [noId=" + noId + ", noTitle=" + noTitle + ", noText=" + noText + ", noRegdate=" + noRegdate
				+ ", noUpdate=" + noUpdate + "]";
	}

} // class
